package com.deedsing.connector.transform;

import javax.xml.transform.TransformerException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;

import com.deedsing.connector.utils.Utils;

public class XMLToMarsJSON {

	
	
	// Test 
	public static void main(String[] args) throws TransformerException, JSONException {
		
		String xml = "<root>\r\n" + 
				"    <_id>mpshasis.com:bently:Machine:ffa96c0d3790200044e0bfc8bcbe5d78</_id>\r\n" + 
				"    <name>mpshasis.com:bently:Machine:MacBook Pro 15\"</name>\r\n" + 
				"    <_owner>bently</_owner>\r\n" + 
				"    <id>ffa96c0d3790200044e0bfc8bcbe5d78</id>\r\n" + 
				"    <Linux CustomerID=\"Bently\" CustomerName=\"Bently \" ID=\"mpshasis.com:bently:Machine:ffa96c0d3790200044e0bfc8bcbe5d78\" NodeName=\"MacBook Pro 15&quot;\" OSMajorVersion=\"Bently \" OSMinorVersion=\"Bently \" OSName=\"Bently \">\r\n" + 
				"      <sys_updated_on>2019-08-01 05:35:22</sys_updated_on>\r\n" + 
				"      <sys_updated_by>system</sys_updated_by>\r\n" + 
				"      <sys_created_on>2012-02-18 08:14:19</sys_created_on>\r\n" + 
				"      <sys_domain>global</sys_domain>\r\n" + 
				"      <install_date>2016-08-30 08:00:00</install_date>\r\n" + 
				"      <sys_created_by>admin</sys_created_by>\r\n" + 
				"      <warranty_expiration>2019-08-30</warranty_expiration>\r\n" + 
				"      <asset_tag>P1000474</asset_tag>\r\n" + 
				"      <fqdn/>\r\n" + 
				"      <sys_class_name>cmdb_ci_computer</sys_class_name>\r\n" + 
				"      <po_number>PO100002</po_number>\r\n" + 
				"      <serial_number>GBF-257-V35963-MC</serial_number>\r\n" + 
				"      <ip_address/>\r\n" + 
				"      <category>Hardware</category>\r\n" + 
				"    </Linux>\r\n" + 
				"  </root>";
		
		Document xml1 = Utils.toXMLDOM(xml);

		
		System.out.println(Utils.prettyPrint((xml1)));
		
		JSONObject xmlJSONObj = new JSONObject();
		xmlJSONObj.append("ogit/id", xml1.getElementsByTagName("id").item(0).getTextContent());
		xmlJSONObj.append("ogit/_id", xml1.getElementsByTagName("_id").item(0).getTextContent());
		xmlJSONObj.append("ogit/_owner", xml1.getElementsByTagName("_owner").item(0).getTextContent());
		xmlJSONObj.append("ogit/name", xml1.getElementsByTagName("name").item(0).getTextContent());
		xmlJSONObj.append("ogit/Automation/marsNodeType", "Machine");
		xmlJSONObj.append("ogit/Automation/formalNode", xml1.getElementsByTagName("Linux"));
		
		System.out.println(xmlJSONObj.toString());
		
	}
	
	
}
