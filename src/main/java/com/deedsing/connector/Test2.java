package com.deedsing.connector;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test2 {

	
	public static void main(String[] args) throws JSONException {
		
		
		String s ="{\r\n" + 
				"  \"result\": [\r\n" + 
				"    {\r\n" + 
				"      \"operational_status\": \"1\",\r\n" + 
				"      \"sys_updated_on\": \"2019-05-01 21:40:56\",\r\n" + 
				"      \"type\": \"\",\r\n" + 
				"      \"u_back_up\": \"\",\r\n" + 
				"      \"u_priority_notes\": \"\",\r\n" + 
				"      \"u_urgency\": \"\",\r\n" + 
				"      \"discovery_source\": \"ServiceNow\",\r\n" + 
				"      \"first_discovered\": \"2018-09-06 02:36:36\",\r\n" + 
				"      \"due_in\": \"\",\r\n" + 
				"      \"u_product_name\": \"\",\r\n" + 
				"      \"u_total_physical_memory\": \"\",\r\n" + 
				"      \"gl_account\": \"\",\r\n" + 
				"      \"invoice_number\": \"\",\r\n" + 
				"      \"sys_created_by\": \"MIDserverHE\",\r\n" + 
				"      \"warranty_expiration\": \"\",\r\n" + 
				"      \"u_application_classification\": \"\",\r\n" + 
				"      \"u_compliance_and_regulatory_requirements\": \"\",\r\n" + 
				"      \"u_others_priority_type\": \"\",\r\n" + 
				"      \"owned_by\": \"\",\r\n" + 
				"      \"checked_out\": \"\",\r\n" + 
				"      \"sys_domain_path\": \"!!!/!!)/!!#/!!&/\",\r\n" + 
				"      \"u_zone\": \"\",\r\n" + 
				"      \"u_business_unit_subdivision\": \"\",\r\n" + 
				"      \"maintenance_schedule\": \"\",\r\n" + 
				"      \"approval_group_list\": \"\",\r\n" + 
				"      \"u_application_owner_3\": \"\",\r\n" + 
				"      \"cost_center\": \"\",\r\n" + 
				"      \"u_application_owner_1\": \"\",\r\n" + 
				"      \"u_application_owner_2\": \"\",\r\n" + 
				"      \"dns_domain\": \"headquarters.healthedge.com\",\r\n" + 
				"      \"assigned\": \"\",\r\n" + 
				"      \"purchase_date\": \"\",\r\n" + 
				"      \"short_description\": \"\",\r\n" + 
				"      \"managed_by\": \"\",\r\n" + 
				"      \"can_print\": \"false\",\r\n" + 
				"      \"last_discovered\": \"2019-05-01 21:40:56\",\r\n" + 
				"      \"sys_class_name\": \"cmdb_ci_win_server\",\r\n" + 
				"      \"manufacturer\": {\r\n" + 
				"        \"link\": \"https://infragenieuat1.service-now.com/api/now/table/core_company/a4257ecedb48eb00115303c3ca961930\",\r\n" + 
				"        \"value\": \"a4257ecedb48eb00115303c3ca961930\"\r\n" + 
				"      },\r\n" + 
				"      \"u_priority\": \"\",\r\n" + 
				"      \"vendor\": \"\",\r\n" + 
				"      \"model_number\": \"\",\r\n" + 
				"      \"assigned_to\": \"\",\r\n" + 
				"      \"start_date\": \"\",\r\n" + 
				"      \"serial_number\": \"VMware-42 11 8a fc 00 1b 65 21-49 6b 17 d6 fd 52 ae 27\",\r\n" + 
				"      \"u_cabinet_no\": \"\",\r\n" + 
				"      \"support_group\": \"\",\r\n" + 
				"      \"u_room\": \"\",\r\n" + 
				"      \"correlation_id\": \"\",\r\n" + 
				"      \"unverified\": \"false\",\r\n" + 
				"      \"attributes\": \"\",\r\n" + 
				"      \"asset\": {\r\n" + 
				"        \"link\": \"https://infragenieuat1.service-now.com/api/now/table/alm_asset/5d314236db54afc873f6fba6689619af\",\r\n" + 
				"        \"value\": \"5d314236db54afc873f6fba6689619af\"\r\n" + 
				"      },\r\n" + 
				"      \"u_business_unit\": \"\",\r\n" + 
				"      \"u_customer\": {\r\n" + 
				"        \"link\": \"https://infragenieuat1.service-now.com/api/now/table/core_company/5e6462ccdb30bb4c39722539489619c5\",\r\n" + 
				"        \"value\": \"5e6462ccdb30bb4c39722539489619c5\"\r\n" + 
				"      },\r\n" + 
				"      \"skip_sync\": \"false\",\r\n" + 
				"      \"criticality\": \"\",\r\n" + 
				"      \"u_scc_supported\": \"\",\r\n" + 
				"      \"u_cover\": \"\",\r\n" + 
				"      \"managed_domain\": \"false\",\r\n" + 
				"      \"sys_updated_by\": \"MIDserverHE\",\r\n" + 
				"      \"u_for_automation\": \"false\",\r\n" + 
				"      \"sys_created_on\": \"2018-09-06 02:36:36\",\r\n" + 
				"      \"sys_domain\": {\r\n" + 
				"        \"link\": \"https://infragenieuat1.service-now.com/api/now/table/domain/bb2494464f6453c4f9dc44f18110c744\",\r\n" + 
				"        \"value\": \"bb2494464f6453c4f9dc44f18110c744\"\r\n" + 
				"      },\r\n" + 
				"      \"u_in_scope\": \"\",\r\n" + 
				"      \"install_date\": \"\",\r\n" + 
				"      \"u_ci_contact\": \"\",\r\n" + 
				"      \"u_additional_information\": \"\",\r\n" + 
				"      \"u_support_level\": \"\",\r\n" + 
				"      \"asset_tag\": \"\",\r\n" + 
				"      \"fqdn\": \"AHA-ANS-PER01.headquarters.healthedge.com\",\r\n" + 
				"      \"u_application_hosted\": \"\",\r\n" + 
				"      \"u_decommission_date\": \"\",\r\n" + 
				"      \"change_control\": \"\",\r\n" + 
				"      \"u_os_version\": \"\",\r\n" + 
				"      \"delivery_date\": \"\",\r\n" + 
				"      \"install_status\": \"1\",\r\n" + 
				"      \"supported_by\": \"\",\r\n" + 
				"      \"name\": \"AHA-ANS-PER01\",\r\n" + 
				"      \"u_application_hosted_1\": \"\",\r\n" + 
				"      \"u_application_hosted_2\": \"\",\r\n" + 
				"      \"subcategory\": \"Computer\",\r\n" + 
				"      \"u_application_hosted_3\": \"\",\r\n" + 
				"      \"u_down_priority\": \"\",\r\n" + 
				"      \"u_related_assets\": \"\",\r\n" + 
				"      \"assignment_group\": \"\",\r\n" + 
				"      \"u_complexity\": \"\",\r\n" + 
				"      \"u_nagios_threshold\": \"\",\r\n" + 
				"      \"metal_class\": \"\",\r\n" + 
				"      \"sys_id\": \"62577936dbd0afc873f6fba6689619e6\",\r\n" + 
				"      \"po_number\": \"\",\r\n" + 
				"      \"checked_in\": \"\",\r\n" + 
				"      \"sys_class_path\": \"/!!/!R/!!/!$/!3\",\r\n" + 
				"      \"mac_address\": \"\",\r\n" + 
				"      \"u_reboot_allowed\": \"false\",\r\n" + 
				"      \"company\": {\r\n" + 
				"        \"link\": \"https://infragenieuat1.service-now.com/api/now/table/core_company/41f458864f6453c4f9dc44f18110c7e1\",\r\n" + 
				"        \"value\": \"41f458864f6453c4f9dc44f18110c7e1\"\r\n" + 
				"      },\r\n" + 
				"      \"u_dmz\": \"\",\r\n" + 
				"      \"justification\": \"\",\r\n" + 
				"      \"department\": \"\",\r\n" + 
				"      \"u_virtual\": \"false\",\r\n" + 
				"      \"comments\": \"\",\r\n" + 
				"      \"cost\": \"\",\r\n" + 
				"      \"u_floor\": \"\",\r\n" + 
				"      \"sys_mod_count\": \"0\",\r\n" + 
				"      \"monitor\": \"false\",\r\n" + 
				"      \"u_impact\": \"\",\r\n" + 
				"      \"ip_address\": \"172.31.12.180\",\r\n" + 
				"      \"model_id\": {\r\n" + 
				"        \"link\": \"https://infragenieuat1.service-now.com/api/now/table/cmdb_model/23253acedbc4e30473f6fba6689619b6\",\r\n" + 
				"        \"value\": \"23253acedbc4e30473f6fba6689619b6\"\r\n" + 
				"      },\r\n" + 
				"      \"sys_tags\": \"\",\r\n" + 
				"      \"u_environment_instance\": \"100\",\r\n" + 
				"      \"cost_cc\": \"USD\",\r\n" + 
				"      \"u_compliance_in_scope\": \"\",\r\n" + 
				"      \"order_date\": \"\",\r\n" + 
				"      \"schedule\": \"\",\r\n" + 
				"      \"u_system_role\": \"Answers\",\r\n" + 
				"      \"environment\": \"Performance\",\r\n" + 
				"      \"u_application\": \"\",\r\n" + 
				"      \"due\": \"\",\r\n" + 
				"      \"u_incident_escalation_group\": \"\",\r\n" + 
				"      \"location\": \"\",\r\n" + 
				"      \"category\": \"Hardware\",\r\n" + 
				"      \"fault_count\": \"0\",\r\n" + 
				"      \"lease_id\": \"\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}\r\n" + 
				"";
		
		
		JSONObject obj = new JSONObject(s);
		
		JSONObject root = (JSONObject) obj.getJSONArray("result").getJSONObject(0);
		
		//System.out.println(root);
		
		Iterator<String> keys = root.keys();
		
		
		for(int i = 0; i<root.names().length(); i++){
			
			if( !root.getString((String) root.names().get(i)).isEmpty())
		    System.out.println(root.names().getString(i) + ": " +  root.get((String) root.names().get(i)));
		}

		
	}
		
	}
	
