package com.deedsing.connector.rest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.transform.TransformerException;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.w3c.dom.Document;

import com.deedsing.connector.utils.Utils;

public class RestClient {
	private String BASE_URI;
	private String username;
    private String password;
	
    /** end point for read queries */
	private Client client;
	
	
	
	//This method is used to set additional filters and define the path which will be queried 
	public WebTarget getWebTargetURL(String url) {
		return client.target(BASE_URI + url);
		
		
	}

	// The constructor builds the base client object.
	public RestClient(String baseUri, String username, String password) throws RestClientException {
		
		if(baseUri==null || baseUri.isEmpty())
			throw new RestClientException("BaseURI is not available"+baseUri);
		
		this.BASE_URI=baseUri;
		this.username = username;
		this.password=password;
		
		 try
		    {
		        // Create a trust manager that does not validate certificate chains
		        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
		            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		                return null;
		            }
		            public void checkClientTrusted(X509Certificate[] certs, String authType) {
		            }
		            public void checkServerTrusted(X509Certificate[] certs, String authType) {
		            }
		        }
		        };

		        // Install the all-trusting trust manager
		        SSLContext sc = SSLContext.getInstance("SSL");
		        sc.init(null, trustAllCerts, new java.security.SecureRandom());
		        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		        // Create all-trusting host name verifier
		        HostnameVerifier allHostsValid = new HostnameVerifier() {
		            public boolean verify(String hostname, SSLSession session) {
		                return true;
		            }
		        };

		        // Install the all-trusting host verifier
		        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		        
		        this.client = ClientBuilder.newBuilder()
		                .sslContext(sc)
		                .hostnameVerifier(allHostsValid)
		                .build();
		    } catch (NoSuchAlgorithmException e) {
		        e.printStackTrace();
		    } catch (KeyManagementException e) {
		        e.printStackTrace();
		    }
		
		//this.client = ClientBuilder.newClient( new ClientConfig());
		
		
		
		if(username!=null && password!=null && !username.isEmpty() && !password.isEmpty()) {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(username, password);
		this.client.register(feature);
		
	
		}
	}

	
	
	// Test method 
	public static void main(String[] args) throws IOException, TransformerException, RestClientException {	 
		
		
		  Map <String,Object> map = Utils.loadProperties("C:/Users/dsingh/work/custom-connector/config/config.yaml");
		  
		  java.util.Date date = (Date) map.get("input.from.time");
		  
		  date = Utils.addSeconds(date, 30);
		  System.out.println(Utils.getDateAsString(date));
		
		 RestClient client = new RestClient("https://dev87167.service-now.com/api/now/table","admin","Jp6PQMnQbYho");
		 WebTarget webTarget = client.getWebTargetURL("/incident");
		 webTarget= webTarget.queryParam("sysparm_query", map.get("input.url.web.append")+Utils.getDateAsString(date));
		 
		 System.out.println(webTarget.getUri());
		 Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML); 
		 Response response = invocationBuilder.get();
		 String out = response.readEntity(String.class);
		 Document xml = Utils.toXMLDOM(out);
		 Utils.printDocument(xml, System.out);
		 
	}
	
	
	
}
	

