package com.deedsing.connector.transform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.json.JSONException;

public class JavaScriptFilterOnJsonObject {

	
	public static String processFilter(String json, String javaScriptFileName) throws IOException, ScriptException, JSONException {
		
	
		String contents = new String(Files.readAllBytes(Paths.get(javaScriptFileName)));
	    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
	    
	
	    Bindings bindings = engine.createBindings();
	    bindings.put("obj",json);
	    
	    Object bindingsResult = engine.eval(contents, bindings);
	    
	    String out = bindingsResult.toString();
		return out;
	}
	
	public static void main(String[] args) throws Exception {
		
		String s = "{\"nestedStates\":true,\"send_Topic\":\"Change\",\"cb_Topic\":{},\"delta\":true,\"sdfType\":\"CHANGE\",\"mand\":{\"summary\":\"Please ignore\",\"sourceStatus\":1,\"sourceId\":\"SCTASK0101190\",\"assignedGroup\":\"SCTASK0101190\",\"normalizedStatus\":\"New\",\"urgency\":3,\"sourceTicketId\":\"SCTASK0101190\",\"impact\":3,\"description\":\"Please ignore\",\"priority\":4},\"free\":{\"State\":\"New\",\"u_hiro_variables\":\"984c8f72dbca77c0292850d3dc961998$AAD3 Test3$Password reset$\",\"affectedCI\":\"984c8f72dbca77c0292850d3dc961998$AAD3 Test3$Password reset$\",\"assigned_to\":{}},\"sdfVersion\":1,\"prod_id\":\"SRConnector\",\"call_id\":{}}";
		String result = JavaScriptFilterOnJsonObject.processFilter(s, "C:/Users/dsingh/work/custom-connector/config/filter.js");
		System.out.println(result);
	}
	
}
