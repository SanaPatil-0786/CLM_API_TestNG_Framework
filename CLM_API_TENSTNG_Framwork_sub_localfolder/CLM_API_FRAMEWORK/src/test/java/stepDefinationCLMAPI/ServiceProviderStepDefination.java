package stepDefinationCLMAPI;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.cucumber.core.resource.Resource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.APIUtilsCommon;
import testdata.ServiceProviderStakeholder;
import io.cucumber.java.en.Then;

public class ServiceProviderStepDefination {
	public static Response res;
	public static RequestSpecification req;
	public static APIResources apres;
	public static int companyid;
	public static int compaddressid;
	public static int contactid;
	public static int newcontactid;
	public static int saveserviceproviderid;

	@Given("prepare request payload to {string} API")
	public void prepare_request_payload_to_API(String resources) throws IOException {
		apres = APIResources.valueOf(resources);
		if ("GetStakeHolderServiceProvideList".equalsIgnoreCase(apres.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all().queryParam("userId", 2);
		}
		else if("GetStakeHolderServiceProvAddress".equalsIgnoreCase(apres.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all().queryParam("CompanyId", companyid);
		}
		else if("GetStakeHolderSigningAuthorityList".equalsIgnoreCase(apres.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all().queryParam("CompanyId", companyid);
		}
		else if ("SaveServiceProvContactInfo".equalsIgnoreCase(apres.name().trim())) {
			req = APIUtilsCommon.postHeaderRequest().body(ServiceProviderStakeholder.reqBodyContactInfo());
		}
		else if ("GetStkeholderContactList".equalsIgnoreCase(apres.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all().queryParam("CompanyId", companyid);
		}
		else if("GetStakeHolderContactInfromation".equalsIgnoreCase(apres.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().log().all().queryParam("companyContactId", newcontactid);
		}
		else if ("SaveStakeholderServiceProvider".equalsIgnoreCase(apres.name().trim())) {
			req = APIUtilsCommon.postHeaderRequest().body(ServiceProviderStakeholder.saveServiceProviderBody());
		}
	}

	@When("sent get request to {string} API")
	public void sent_get_request_to_api(String resources) throws IOException {
		apres = APIResources.valueOf(resources);
		if ("GetStakeHolderServiceProvideList".equalsIgnoreCase(apres.name().trim())) {
			res = req.when().get(apres.getResources());
		} else if ("GetStakeHolderServiceProvAddress".equalsIgnoreCase(apres.name().trim())) {
			res = req.when().get(apres.getResources());
		} else if ("GetStakeHolderSigningAuthorityList".equalsIgnoreCase(apres.name().trim())) {
			res = req.when().get(apres.getResources());
		} else if ("GetStkeholderContactList".equalsIgnoreCase(apres.name().trim())) {
			res = req.when().get(apres.getResources());
		} else if ("GetStakeHolderContactInfromation".equalsIgnoreCase(apres.name().trim())) {
			res = req.when().get(apres.getResources());
		}
	}

	@Then("check response statuscode is {string}")
	public void check_response_statuscode_is(String code) {
		int statuscode = Integer.valueOf(code);
		res.then().assertThat().statusCode(statuscode);
	}

	@Then("check total count of service provider in system")
	public void check_total_count_of_service_provider_in_system() {
		String body = res.asString();
		JsonPath json = new JsonPath(body);
		int spcount = json.getInt("data.serviceProviderListDto.size()");

		System.out.println("total service provider count : " + spcount);
		List<Map<String, Object>> list = json.getList("data.serviceProviderListDto");

		for (Map<String, Object> item : list) {
			String companyname = String.valueOf(item.get("companyname"));
			if ("ItCube Solutions Inc.(USA)".equals(companyname)) { // or equalsIgnoreCase
				Object cid = item.get("companyid"); // could be Integer or String
				companyid = Integer.parseInt(String.valueOf(cid));
				System.out.println("Matched companyid: " + companyid);
				break; // optional if you only need the first match
			}
		}
	}

	@Then("check total count of address {string} arrayobject against companyid and take {string} keyValue")
	public void check_total_count_of_address_arrayobject_against_companyid_and_take_keyValue(String arrayKey,
			String idKey) {

		String body = res.asString();
		JsonPath json = new JsonPath(body);
		int totaladdress = json.getInt("data." + arrayKey + ".size()");
		System.out.println("total count of address inside selected compayid :" + totaladdress);
		compaddressid = json.getInt("data.serviceProvAddressListDto[0].companyaddressid");
		System.out
				.println("company first address name : " + json.getString("data.serviceProvAddressListDto[0].address"));

	}

	@Then("check total count of contact user {string} arrayobject against companyid and take {string} keyValue")
	public void check_total_count_of_contact_user_arrayobject_against_companyid_and_take_keyValue(String arrayobj,
			String key) {
		String body = res.asString();
		JsonPath json = new JsonPath(body);
		int totalcontacts = json.getInt("data." + arrayobj + "size()");
		System.out.println("total count of contact is : " + totalcontacts);
		contactid = json.getInt("data." + arrayobj + key);
		System.out.println("company contact first id :" + contactid);
	}

	@When("sent post request to {string} API")
	public void sent_post_request_to_api(String apiName) {
		apres = APIResources.valueOf(apiName);
		if("SaveServiceProvContactInfo".equalsIgnoreCase(apres.name().trim())) {
			res = req.when().post(apres.getResources());
		}
		else if ("SaveStakeholderServiceProvider".equalsIgnoreCase(apres.name().trim())) {
			res = req.when().post(apres.getResources());
		}
	}

	@Then("check {string} and {string} key value of {string} API")
	public void check_and_key_value_of_api(String key1, String key2, String resourcename) {
		// SaveServiceProvContactInfo
		apres = APIResources.valueOf(resourcename);
		if ("SaveServiceProvContactInfo".equalsIgnoreCase(apres.name().trim())) {
			String body = res.asString();
			JsonPath json = new JsonPath(body);
			String message = json.getString(key1);
			newcontactid = json.getInt(key2);
			System.out.println("newly created contact :" + newcontactid + "\n"
					+ "SaveServiceProvContactInfo api response message :" + message);
		}
		else if("SaveStakeholderServiceProvider".equalsIgnoreCase(apres.name().trim())) {
			String body = res.asString();
			JsonPath json = new JsonPath(body);
			String message = json.getString(key1);
			saveserviceproviderid = json.getInt(key2);
			System.out.println("newly added service provider details  :" + saveserviceproviderid + "\n"
					+ "SaveStakeholderServiceProvider api response message :" + message);
		}

	}

	@Then("check newly created contactID is present inside {string} arrayobject")
	public void check_newly_created_contact_id_is_present_inside_arrayobject(String arrayobj) {
		String body = res.asString();
		JsonPath json = new JsonPath(body);
		List<Map<String, Object>> list = json.getList("data." + arrayobj);
		for (int i = 0; i < list.size(); i++) {
			Object val = list.get(i).get("companycontactid");
			int actualcontactID = Integer.parseInt(val.toString());
			if (newcontactid == actualcontactID) {
				System.out.println("newlycreated contact is present insdie contact list : actualcontactid :"
						+ actualcontactID + "and newly createdcontactid : " + newcontactid);
			}
		}
	}

	@Then("check response body contain same contactuser details of provided contactid")
	public void check_response_body_contain_same_contactuser_details_of_provided_contactid() {
		ServiceProviderStakeholder.assertionmethod();

	}

	@Then("check response body contain same saved service provider details")
	public void check_response_body_contain_same_saved_service_provider_details() {
	}

}
