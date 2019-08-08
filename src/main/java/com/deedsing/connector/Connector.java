package com.deedsing.connector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.script.ScriptException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;

import com.deedsing.connector.rest.RestClient;
import com.deedsing.connector.rest.RestClientException;
import com.deedsing.connector.transform.JavaScriptFilterOnJsonObject;
import com.deedsing.connector.transform.XSLTTransformation;
import com.deedsing.connector.utils.Utils; 
public class Connector {

	public static Logger log = Logger.getLogger(Connector.class);
	private static ArrayList<String> errors = new ArrayList<String>();

	/**
	 * @throws TransformerFactoryConfigurationError
	 * @throws ConnectorException
	 */
	public static void start(String configurationFile) throws TransformerFactoryConfigurationError, ConnectorException {

		try {

			log.info("Loading the configurations ...");
			// load the configuration file from input.
			HashMap<String, Object> config = (HashMap<String, Object>) Utils.loadProperties(configurationFile);
			log.info("Configurations successfully loaded");

			// get the current date on the system
			java.util.Date date = (Date) config.get("input.from.time");
			Integer pollingInterval= (Integer) config.get("input.polling.interval");
		

			// Retrieve payload
			RestClient inTarget = new RestClient((String) config.get("input.url.base"),
					(String) config.get("input.user"), (String) config.get("input.password"));
			WebTarget webTarget = inTarget.getWebTargetURL((String) config.get("input.url.web.target"));
			webTarget = webTarget.queryParam("sysparm_query",
					config.get("input.url.web.append") + Utils.getDateAsString(date));
			log.info("Invoking web target url: "+webTarget.getUri());
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
			Response response = invocationBuilder.get();
			String out = response.readEntity(String.class); // read payload as String xml
			int statusCode = response.getStatus();
			Document xml = Utils.toXMLDOM(out);
			log.info("http status code: " + statusCode);
			log.debug("Response: \n" + Utils.prettyPrint(xml));
			log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			//check for hibernating instance or any error response
			if (statusCode != 200 || out.contains("Hibernating Instance"))
				throw new ConnectorException(
						"Unsuccessful response recieved from the server " + webTarget.getUri());
			
			
			Integer numNodesToProcess = -1;
			
			if (xml.getElementsByTagName("result")!=null)
				numNodesToProcess = xml.getElementsByTagName("result").getLength();
			
			
			if (numNodesToProcess>0) {

				
				xml.getElementsByTagName("result").getLength();
				log.info("Number of elements to process: "+numNodesToProcess);

				// Transform the xml using xslt and create the sdf response
				
				log.info("Starting transformation ...");
				XSLTTransformation transform = new XSLTTransformation();
				String result = transform.transform(out, (String) config.get("input.xsl.mapping")); // transform the
																									// string xml with
				Document xml1 = Utils.toXMLDOM(result);																					// xsl
				log.debug("XML Transformation result: \n"+ Utils.prettyPrint((xml1)));
				
				
			
				Utils.writeToFile(xml1,"/tmp/out.xml");
				
				
				// convert to json

				JSONObject xmlJSONObj = XML.toJSONObject(result);
				String jsonPrettyPrintString = xmlJSONObj.toString();
				log.info("Converting to JSON String");
				log.debug("JSON Transformation result: \n" + jsonPrettyPrintString);
				log.info("Transformation complete");
				log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				// get all the sdf's one by one

				Object obj = xmlJSONObj.getJSONObject("result").get("root");
				log.debug("Received object instance is "+ obj.getClass().toGenericString());
				
				if(obj instanceof JSONArray) {
				log.debug("JSONArray received as response");	
				
				  JSONArray root = (JSONArray) obj;
				
				  if ( root != null && root.length() > 0) {
						for (int j = 0; j < root.length(); j++) {
							
							String sdfString = root.optString(j);
							processSDF(config, sdfString);
							
						}

					} 
				
				} else if (obj instanceof JSONObject) {
					
					JSONObject root = (JSONObject) obj;
					
					log.debug("Received JSONObject");
					processSDF(config, root.toString());
					
					
				}else 
				{
					log.debug("Received Unsupported object...");
					log.error("Only JSONObject and JSONArray are supported responses");
					
					
				}
				
				
				
				
				

				
				
			}
			else {

					log.info("Nothing to process");
				}
				// if no errors reset timestamp to current

				if (errors.isEmpty()) {
					java.util.Date currDate = new java.util.Date();
					config.put("input.from.time", currDate);
					Utils.dumpYaml(config, configurationFile);
				}else {
					log.info("Unsuccessful SDF count: "+ errors.size());
					log.error("SDF posted resulted in error: \n" + errors);
					log.debug("Polling timestamp has not been reset because of negative response from the target endpoint.");
					
					log.info("SDF will be reprocessed on next trigger");
					log.info("Resetting errors ..");
					errors.clear();
				}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ConnectorException(e.getMessage());
		}
	}

	/**
	 * @param config
	 * @param sdfString
	 * @throws IOException
	 * @throws ScriptException
	 * @throws JSONException
	 * @throws RestClientException
	 */
	private static void processSDF(HashMap<String, Object> config, String sdfString)
			throws IOException, ScriptException, JSONException, RestClientException {
		log.info("Starting processing for sdf");
		log.debug("SDF before javascript filter : "+ sdfString);
		String sdf = JavaScriptFilterOnJsonObject.processFilter(sdfString, (String) config.get("filter.javascript.main"));
		log.info("SDF after javascript filter : "+ sdf);
		
		// post sdf's
		RestClient outTarget = new RestClient((String) config.get("output.url.base"), null, null);
		WebTarget outwebTarget = outTarget
				.getWebTargetURL((String) config.get("output.url.web.target"));
		
		log.info("URL for the target api: "+ outwebTarget.getUri());

		try {
						
			log.info("Posting sdf to the endpoint ...");
			Response responseSend = outwebTarget.request()
					.post(Entity.entity(sdf, "application/json"));
			String entity = responseSend.readEntity(String.class);

			log.info("http status code: " + responseSend.getStatus());
			log.debug("Response: \n" + entity);
		
			
			if (responseSend.getStatus() != 200) {
				log.error("Unsuccessfull --> " + sdfString);
				errors.add("Unsuccessfull --> " + sdfString);
			}

		} catch (Exception e) {
			log.error(e.toString());
			errors.add(e + ":" +sdfString);
		}

		log.info("SDF processed.");
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}

}
