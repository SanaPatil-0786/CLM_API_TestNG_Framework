package stepDefinationCLMAPI;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.APIUtilsCommon;
import testdata.ClientStakeholder;

public class ClientStakeholderStepDefination {
	public static Response resp;
	public static RequestSpecification req;
	public static APIResources res;
	public static int clientid;
	public static String clientcode;
	public static int clientaddressid;
	public static int clientcontactid;
	public static int clientcontactid2;
	public static int savestakeholderid;

	@Given("a request payload is prepared for the {string} API")
	public void a_request_payload_is_prepared_for_the_api(String resources) throws IOException {
		res = APIResources.valueOf(resources);
		if ("SaveClientInformation".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.postHeaderRequest().body(ClientStakeholder.saveClientBody());
		} else if ("SaveClientAddress".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.postHeaderRequest().body(ClientStakeholder.saveAddress());
		} else if ("SaveClientContactInformation".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.postHeaderRequest().body(ClientStakeholder.savecontact());
		} else if ("DeleteClientAddress".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all().queryParam("ClientaddressinformationId", clientaddressid);
		} else if ("DeleteClientContactInformation".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all().queryParam("clientContactInformationId", clientcontactid);
		} else if ("GetAllClientList".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all();
		} else if ("GetClientContactWithAddressList".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all().queryParam("ClientId", clientid);
		} else if ("GetKeyStakeHolderInfo".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all().queryParam("ContactId",
					ContractHeaderStepDefination.contractId);
		}
		else if("SaveStakeHoldersClientDetails".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.postHeaderRequest().body(ClientStakeholder.saveClientStakeholder());
		}
		else if ("GetClientStakeHolderDetails".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all().queryParam("contractId", ContractHeaderStepDefination.contractId);
		}
	}

	@When("a POST request is sent to the {string} API")
	public void a_post_request_is_sent_to_the_api(String resources) {
		res = APIResources.valueOf(resources);
		if ("SaveClientInformation".equalsIgnoreCase(res.name().trim())) {
			resp = req.when().post(res.getResources());
		}
		else if("SaveStakeHoldersClientDetails".equalsIgnoreCase(res.name().trim())) {
			resp = req.when().post(res.getResources());
		}
	}

	@Then("the response status code should {string}")
	public void the_response_status_code_should(String statuscode) {
		resp.then().log().all();
		int code = resp.getStatusCode();
		int expectedstatus = Integer.valueOf(statuscode);
		Assert.assertEquals(code, expectedstatus);
	}

	@Then("take {string} and {string} keyvalue from response body")
	public void take_and_keyvalue_from_response_body(String primaryKey, String newclientcode) {
		String resbody = resp.asString();
		JsonPath json = new JsonPath(resbody);
		// data is an array; take the first object
		clientid = json.getInt("data[0]." + primaryKey); // e.g., data[0].primaryKey
		String clientcode = json.getString("data[0]." + newclientcode); // e.g., data[0].newclientcode
		System.out.println("new client added and its clientID  is: " + clientid);
		System.out.println("client code for new client : " + clientcode);
		System.out.println("after create client then dispalyed message:  " + json.getString("message"));
	}

	@When("a POST request is send to {string} API and add {string} address aginst new created client")
	public void a_post_request_is_send_to_api_and_add_address_aginst_new_created_client(String resources, String count)
			throws IOException {

		// SaveClientAddress
		res = APIResources.valueOf(resources);
		int count1 = Integer.valueOf(count);
		if ("SaveClientAddress".equalsIgnoreCase(res.name().trim())) {
			for (int i = 0; i < count1; i++) {
				resp = ClientStakeholder.method().when().post(res.getResources());
			}
		}
	}

	@Then("check total count of address inside {string} array object should {string}")
	public void check_total_count_of_address_inside_array_object_should(String key, String count1) {
		// SaveClientAddress
		// resp.then().log().all();
		String body = resp.asString();
		JsonPath json = new JsonPath(body);
		clientaddressid = json.getInt("data.clientAddressListDtoList[0].clientAddressId");
		List<Map<String, Object>> list1 = json.getList("data." + key);
		int count = list1.size();
		int expectcout = Integer.valueOf(count1);
		Assert.assertEquals(count, expectcout);
		System.out.println("//SaveClientAddress api actual total count of address is :" + count + "\n"
				+ "//SaveClientAddress api expected address count is : " + expectcout);
	}

	@When("a POST request is send to {string} API and add {string} contact aginst new created client")
	public void a_POST_request_is_send_to_API_and_add_contact_aginst_new_created_client(String resources, String count)
			throws IOException {
		// SaveClientContactInformation
		res = APIResources.valueOf(resources);
		int count1 = Integer.valueOf(count);
		
		if ("SaveClientContactInformation".equalsIgnoreCase(res.name().trim())) {
			for (int i = 0; i < count1; i++) {
				resp = ClientStakeholder.method2().when().post(res.getResources());
				if(resp.jsonPath().getBoolean("wasSuccessful") == false) {
					i--;
				}
			}
		}	
	}

	@Then("check total count of contact inside {string} array object should {string}")
	public void check_total_count_of_contact_inside_array_object_should(String key, String count1) {
		// SaveClientContactInformation
		resp.then().log().all();
		String body = resp.asString();
		JsonPath json = new JsonPath(body);
		List<Map<String, Object>> list1 = json.getList("data." + key);
		Assert.assertNotNull(list1, "Key not found: " + key);
		if (!list1.isEmpty()) {
			clientcontactid = json.get("data.clientContactListSP[0].clientcontactinformationid");
			clientcontactid2 = json.get("data.clientContactListSP[1].clientcontactinformationid");
			Assert.assertNotNull(clientcontactid, "First element missing clientcontactinformationid");
		}
		Assert.assertNotNull(list1, "Key not found: " + key);
		int count = list1.size();
		int expectcout = Integer.valueOf(count1);
		Assert.assertEquals(count, expectcout);
		System.out.println("//SaveClientAddress api actual total count of address is :" + count + "\n"
				+ "//SaveClientAddress api expected address count is : " + expectcout);
	}

	@When("a GET request is send to {string} API")
	public void a_get_request_is_send_to_api(String resources) {
		// DeleteClientAddress
		res = APIResources.valueOf(resources);
		if ("DeleteClientAddress".equalsIgnoreCase(res.name().trim())) {
			resp = req.when().get(res.getResources());
		}
		// DeleteClientContactInformation
		else if ("DeleteClientContactInformation".equalsIgnoreCase(res.name().trim())) {
			resp = req.when().get(res.getResources());
		}
		// GetAllClientList
		else if ("GetAllClientList".equalsIgnoreCase(res.name().trim())) {
			resp = req.when().get(res.getResources());
		} else if ("GetClientContactWithAddressList".equalsIgnoreCase(res.name().trim())) {
			resp = req.when().get(res.getResources());
		} else if ("GetKeyStakeHolderInfo".equalsIgnoreCase(res.name().trim())) {
			resp = req.when().get(res.getResources());
		}
		else if ("GetClientStakeHolderDetails".equalsIgnoreCase(res.name().trim())){
			resp = req.when().get(res.getResources());
		}
	}

	@Then("check out count after delete address")
	public void check_out_count_after_delete_address() {
		// DeleteClientAddress
		String body = resp.then().log().all().extract().response().asString();
		JsonPath json = new JsonPath(body);
		int count = json.getInt("data.clientAddressListDtoList.size()");
		System.out.println("after delete totak count of address : " + count);
	}

	@Then("check out count after delete contact")
	public void check_out_count_after_delete_contact() {
		// DeleteClientContactInformation
		String body = resp.then().log().all().extract().response().asString();
		JsonPath json = new JsonPath(body);
		int count = json.getInt("data.clientContactListSP.size()");
		System.out.println("after delete totak count of address : " + count);
	}

	@Then("check newly created client is listed in all client list")
	public void check_newly_created_client_is_listed_in_all_client_list() {
		// GetAllClientList
		String body = resp.asString();
		JsonPath json = new JsonPath(body);
		List<Map<String, Object>> list = json.getList("data.stakeHoldersClientList");
		for (int i = 0; i < list.size(); i++) {
			Object val = list.get(i).get("clientinformationid");
			int actualclientid = Integer.parseInt(val.toString()); // convert object to string then convert string to
																	// integer
			if (actualclientid == clientid) {
				System.out.println("newly created client id is present inside list of client :" + actualclientid + "=="
						+ clientid);
			}
		}
	}

	@Then("check count contacts and address list is same in response body")
	public void check_count_contacts_and_address_list_is_same_in_response_body() {
		// GetClientContactWithAddressList

		JsonPath json = new JsonPath(resp.asString());
		List<Map<String, Object>> contactlist = json.getList("data.clientContactList");
		int totalcontact = contactlist.size();
		List<Map<String, Object>> addresslist = json.getList("data.clientAddressListDtoList");
		int totaladdress = addresslist.size();
		System.out.println("totalcontactlist agianst newly created client :" + totalcontact + "\n"
				+ "total address list agianst newly created client : " + totaladdress);

	}
	@Then("check message correctly display and take data keyvalue")
	public void check_message_correctly_display_and_take_data_keyvalue() {
       String resbody = resp.asString();
       String expectedmessage = "SaveStakeHoldersClientDetails API are successfully loaded";
       JsonPath json = new JsonPath(resbody);
       String actualmessage = json.getString("message");
       Assert.assertEquals(actualmessage, expectedmessage);
       System.out.println("output message of api :" +actualmessage);
       savestakeholderid = json.getInt("data");
	}
	@Then("check contact details are correct for the provided contact ID")
	public void check_contact_details_are_correct_for_the_provided_contact_ID() {
		// GetKeyStakeHolderInfo
		String body = resp.asString();
		JsonPath json = new JsonPath(body);
		int actualclientid  = json.getInt("data.clientstakeholderDetailsDto[0].clientinformationid");
		int actualclientaddressid  = json.getInt("data.clientstakeholderDetailsDto[0].clientaddressinformationid");
		int actualclientsignauthoid  = json.getInt("data.clientstakeholderDetailsDto[0].clientsigningauthorityid");
		int actualcontractid  = json.getInt("data.clientstakeholderDetailsDto[0].contractid");
		int actualsaveclientid  = json.getInt("data.clientstakeholderDetailsDto[0].contractstakeholdersclientid");
		int actualcontactid  = json.getInt("data.clientContactList[0].clientcontactinformationid");
		System.out.println("client id actual : "+ actualclientid  + "equals id : "+ clientid);
		Assert.assertEquals(actualclientid, clientid);
		System.out.println("client id actual : "+ actualclientaddressid  + "equals id : "+ clientaddressid);

		Assert.assertEquals(actualclientaddressid, clientaddressid);
		Assert.assertEquals(actualclientsignauthoid, clientcontactid2);
		Assert.assertEquals(actualcontractid, clientcontactid);
		Assert.assertEquals(actualsaveclientid, savestakeholderid);
		Assert.assertEquals(actualcontactid, ContractHeaderStepDefination.contractId);
	}
}
