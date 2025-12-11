package stepDefinationCLMAPI;

import java.io.IOException;

import io.cucumber.core.resource.Resource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.APIUtilsCommon;
import io.cucumber.java.en.Then;

public class ServiceProviderStepDefination {
	public static Response res;
	public static RequestSpecification req;
	public static APIResources apres;
	public static int companyid;
	public static int compaddressid;

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
	}

	@When("sent get request to {string} API")
	public void sent_get_request_to_api(String resources) throws IOException {
		apres = APIResources.valueOf(resources);
		if ("GetStakeHolderServiceProvideList".equalsIgnoreCase(apres.name().trim())) {
			res = req.when().get(apres.getResources());
		}
		else if ("GetStakeHolderServiceProvAddress".equalsIgnoreCase(apres.name().trim())) {
			res = req.when().get(apres.getResources());
		}
		else if ("GetStakeHolderSigningAuthorityList".equalsIgnoreCase(apres.name().trim())) {
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
		companyid = json.getInt("data.serviceProviderListDto[0].companyid");
		System.out.println("total service provider count : " + spcount);
	}

	@Then("check total count of {string} arrayobject against companyid and take {string} keyValue")
	public void check_total_count_of_arrayobject_against_companyid_and_take_keyValue(String arrayKey, String idKey) {
		
		//GetStakeHolderSigningAuthorityList
		//GetStakeHolderServiceProvAddress
		//need to create seperate for both statement 
		
		apres = APIResources.valueOf("GetStakeHolderServiceProvAddress");
		
		if ("GetStakeHolderServiceProvAddress".equalsIgnoreCase(apres.name().trim())) {
			String body = res.asString();
			JsonPath json = new JsonPath(body);
			int totaladdress = json.getInt("data."+arrayKey + ".size()");
			System.out.println("total count of address inside selected compayid :"+totaladdress );
			compaddressid= json.getInt("data.serviceProvAddressListDto[0].companyaddressid");
			System.out.println("company first address name : "+ json.getString("data.serviceProvAddressListDto[0].address"));
		}
		
		
	}

	@When("sent post request to {string} API")
	public void sent_post_request_to_api(String apiName) {
	}

	@Then("check {string} and {string} key value")
	public void check_and_key_value(String key1, String key2) {
	}

	@Then("check newly created contactID is present inside {string} arrayobject")
	public void check_newly_created_contact_id_is_present_inside_arrayobject(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("check response body contain same contactuser details of provided contactid")
	public void check_response_body_contain_same_contactuser_details_of_provided_contactid() {
	}

	@Then("check response body contain same saved service provider details")
	public void check_response_body_contain_same_saved_service_provider_details() {
	}

}
