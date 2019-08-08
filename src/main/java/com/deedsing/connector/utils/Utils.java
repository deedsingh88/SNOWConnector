package com.deedsing.connector.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.yaml.snakeyaml.Yaml;

public class Utils {

	public static Logger log = Logger.getLogger(Utils.class);
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
	public static Document toXMLDOM(String xmlString) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder;  
		try {  
		    builder = factory.newDocumentBuilder();  
		    Document document = builder.parse(new InputSource(new StringReader(xmlString)));
		    return document;
		} catch (Exception e) {  
			System.out.println(e);
		    e.printStackTrace();  
		}
		return null; 		
		
	}
	
	public static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	    transformer.transform(new DOMSource(doc), 
	         new StreamResult(new OutputStreamWriter(out, "UTF-8")));
	}
	
	public static Map<String, Object> loadProperties (String configyaml) throws FileNotFoundException {
		
		Yaml yaml = new Yaml();
		 InputStream ios = new FileInputStream(new File(configyaml));
		
		Map<String, Object> result = (Map<String, Object>) yaml.load(ios);
		// using for-each loop for iteration over Map.entrySet() 
        for (Map.Entry<String,Object> entry : result.entrySet())  
            log.debug(entry.getKey() +":" + entry.getValue() ); 
		
		return result;
	}


	public static void dumpYaml(Map<String, Object> map, String yamlFile) throws FileNotFoundException {
		Yaml yaml = new Yaml();
	     yaml.dump(map, new PrintWriter(new File(yamlFile)));
		log.debug(yamlFile+" file has been updated\n");
		for (Map.Entry<String,Object> entry : map.entrySet())  
            log.debug(entry.getKey() +":" + entry.getValue() ); 
		
	}
	
	
	public static Date addSeconds(Date date, Integer seconds) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.SECOND, seconds);
	    return cal.getTime();
	  }
	
	public static String getDateAsString(Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String dateString = simpleDateFormat.format(date);
		return dateString;
		
	}
	
	public static String prettyPrint(Document document)
	        throws TransformerException {
	    TransformerFactory transformerFactory = TransformerFactory
	            .newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(
	            "{http://xml.apache.org/xslt}indent-amount", "2");
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	    DOMSource source = new DOMSource(document);
	    StringWriter strWriter = new StringWriter();
	    StreamResult result = new StreamResult(strWriter);
	 
	    transformer.transform(source, result);
	 
	    return strWriter.getBuffer().toString();
	 
	}

	public static void writeToFile(Document xml1, String file) {
		// put xml into files.
		
		  try{    
	           FileWriter fw=new FileWriter(file);    
	           fw.write(prettyPrint(xml1));    
	           fw.close();    
	          }catch(Exception e){System.out.println(e);}    
	          System.out.println("Success...");    
	     }    
	}
	
	
	

