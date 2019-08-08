package com.deedsing.connector;

import javax.xml.transform.TransformerFactoryConfigurationError;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;


public class App {

	public static Logger log = Logger.getLogger(App.class);
	// This is the main class which will start the java component as a daemon
	/**
	 * @param args
	 * @throws TransformerFactoryConfigurationError
	 * @throws ConnectorException
	 */
	public static void main(String[] args) throws TransformerFactoryConfigurationError, ConnectorException {
		// create Options object
				Options options = new Options();
				options.addOption("c", true, "The configuration file");
				
				CommandLineParser parser = new DefaultParser();
				CommandLine cmd = null;
				try {
					 cmd = parser.parse( options, args);
				} catch (ParseException e) {
					log.error(e.getMessage());
					System.exit(1);
					
				}
				
				if(cmd.hasOption("c")) {
					Connector.start(cmd.getOptionValue('c'));
				
				}
					
				
				
				
				
				else {
					log.error("Mising mandatory parameters");
					HelpFormatter formatter = new HelpFormatter();
					formatter.printHelp("Custom Connector", options);
					System.exit(2);
				}
			}
			
		
		
	}	
	
	
	

