package com.deedsing.connector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;

import com.deedsing.connector.utils.Utils;

public class Test {

	
	public static void main(String[] args) throws FileNotFoundException {
		
	   
	   // File stylesheet = new File("C:/Users/dsingh/work/custom-connector/config/config.yaml");
	
	  Map <String,Object> map = Utils.loadProperties("C:/Users/dsingh/work/custom-connector/config/config.yaml");
	  
	  java.util.Date date = (Date) map.get("input.from.time");
	  
	  date = Utils.addSeconds(date, 30);
	  System.out.println(Utils.getDateAsString(date));
	  
	  java.util.Date currDate = new java.util.Date();
		map.put("input.from.time", currDate);
		Utils.dumpYaml(map, "C:/Users/dsingh/work/custom-connector/config/config.yaml");
	  
	}
}
