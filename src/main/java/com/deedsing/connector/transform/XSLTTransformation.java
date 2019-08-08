package com.deedsing.connector.transform;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTTransformation {

	
	public String transform (String xml, String xslFilePath) throws TransformerFactoryConfigurationError, TransformerException {

			    File stylesheet = new File(xslFilePath);
			    StreamSource xslSource = new StreamSource(stylesheet);
		        StreamSource xmlInSource = new StreamSource(new StringReader(xml));
		        Transformer tf = TransformerFactory.newInstance().newTransformer(xslSource);
		        StringWriter xmlOutWriter = new StringWriter();
		        tf.transform(xmlInSource, new StreamResult(xmlOutWriter));
		        return xmlOutWriter.toString();  

	}
	
	
	
	
	// Main class for testing
	public static void main(String[] args) {
		try {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			
		       File stylesheet = new File(classLoader.getResource("sdf-change.xsl").getFile());
		       File xmlfile = new File(classLoader.getResource("data.xml").getFile());
		       StreamSource stylesource = new StreamSource(stylesheet);
		       StreamSource xmlsource = new StreamSource(xmlfile);
		       Transformer transformer = TransformerFactory.newInstance()
		                                 .newTransformer(stylesource);
		       
		       // Transform the document and store it in a file
		       transformer.transform(xmlsource, new StreamResult(new File(classLoader.getResource("out.xml").getFile())));
		       
		       StreamResult consoleOut = new StreamResult(System.out);
		       // Transform the document and print the content to console
		       transformer.transform(xmlsource, consoleOut);
		     } catch (TransformerException e) {
		              e.printStackTrace();
		     }
	}
}
