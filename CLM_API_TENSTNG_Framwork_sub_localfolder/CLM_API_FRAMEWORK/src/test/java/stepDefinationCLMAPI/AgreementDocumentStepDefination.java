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
import io.restassured.specification.ResponseSpecification;
//import resources.APIResources;
import resources.*;
import testdata.AgreementDocument;

public class AgreementDocumentStepDefination {

	public static APIResources res;
	public static RequestSpecification req;
	public static Response resp;
	public static int agreementId;
	public static int attachmentId;

	@Given("prepare request payload {string} API")
	public void prepare_request_payload_api(String resources) throws IOException {

		res = APIResources.valueOf(resources);

		if ("SaveAgreementDocumentDetails".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.postHeaderRequest().body(AgreementDocument.createAgreement());
		} else if ("SaveAttachmentDetails".equalsIgnoreCase(res.name().trim())) {
			req = APIUtilsCommon.getPayloadbody().header("Content-Type", "multipart/form-data")
					.spec(AgreementDocument.attachmentBody());
		} else if ("GetAgreementTypeListDetails".equalsIgnoreCase(res.name().trim())) {
			req = AgreementDocument.getlistrequest();
		}
		else if ("GetAgreementDocumentDetails".equalsIgnoreCase(res.name().trim()))
		{
			req = AgreementDocument.getDocumentDetailRequest();
		}
		else if ("GetAttachmentDetails".equalsIgnoreCase(res.name().trim()))
		{
			req = AgreementDocument.getattachmentrequest();
		}

	}

	@When("send post request to {string} API")
	public void send_post_request_to_api(String resources) {
		// common for post method
		res = APIResources.valueOf(resources);
		if ("SaveAgreementDocumentDetails".equalsIgnoreCase(res.name().trim())) {
			resp = req.when().post(res.getResources());
		} else if ("SaveAttachmentDetails".equalsIgnoreCase(res.name().trim())) {
			resp = req.when().post(res.getResources());
		}
	}

	@Then("check response status is {string}")
	public void check_response_status_is(String statuscode) {
		// common for all
		Integer code = Integer.parseInt(statuscode);
		resp.then().assertThat().statusCode(code);
	}

	@Then("take {string} key value from response body")
	public void take_key_value_from_response_body(String data) {
		// SaveAgreementDocumentDetails
		String respbody = resp.asString();
		JsonPath json = new JsonPath(respbody);
		agreementId = json.getInt(data);
	}

	@Then("take {string} key value from response body and check response contain correct contractid, documentid")
	public void take_key_value_from_response_body_and_check_response_contain_correct_contractid_documentid(
			String attachmentid) {
		// SaveAttachmentDetails
		String body = resp.asString();
		JsonPath jsonbody = new JsonPath(body);
		attachmentId = jsonbody.getInt("data[0]." + attachmentid);
		int actualcontractid = jsonbody.getInt("data[0].contractid");
		int actualdocumentid = jsonbody.getInt("data[0].documentid");
		String actualattachmentname = jsonbody.getString("data[0].attachmentname");
		Assert.assertEquals(actualcontractid, ContractHeaderStepDefination.contractId);
		Assert.assertEquals(actualdocumentid, agreementId);
	}

	@When("send get request to {string} API")
	public void send_get_request_to_api(String resources) {
		// GetAgreementTypeListDetails
		res = APIResources.valueOf(resources);
		if ("GetAgreementTypeListDetails".equalsIgnoreCase(res.name().trim())) {
			resp = req.when().get(res.getResources());
		}

		// GetAgreementDocumentDetails
		else if ("GetAgreementDocumentDetails".equalsIgnoreCase(res.name().trim()))
		{
			resp = req.when().get(res.getResources());
		}
		
		// GetAttachmentDetails
		else if ("GetAttachmentDetails".equalsIgnoreCase(res.name().trim()))
		{
			 resp= req.when().get(res.getResources());
		}

	}

	@Then("check count of document against specific document type and check created document contain in response")
	public void check_count_of_document_against_specific_document_type_and_check_created_document_contain_in_response() {
		// GetAgreementTypeListDetails
		String body = resp.asString();
		JsonPath json = new JsonPath(body);

		// Extract array of objects
		List<Map<String, Object>> documents = json.getList("data.agreementTypeListDetailsDtoList");

		// Check if documentId exists
		int expectedId = agreementId; // your documentId

		for (Map<String, Object> doc : documents) {
			if (doc.get("documentId") != null && (int) doc.get("documentId") == expectedId) {
				System.out.println("Found agreement id in list of agreement !");
				break;
			}

		}
	}

	@Then("Verify that the response body contains information related to the given document ID")
	public void verify_that_the_response_body_contains_information_related_to_the_given_document_id() {
		// GetAgreementDocumentDetails
		String body1 = resp.asString();
		JsonPath json = new JsonPath(body1);
		AgreementDocument.validateDocumentDetails(json); //
	}

	@Then("check total attachment count against specific documentid")
	public void check_total_attachment_count_against_specific_documentid() {
     //GetAttachmentDetails
		String body = resp.then().log().all().extract().response().asString();
		JsonPath jsonobj = new JsonPath(body);
		int size  = jsonobj.getList("data.attachmenttDto").size();
		System.out.println("Number of Attchment inside:  " +agreementId + "agreementid and its count is :" + size);
		
	}

	@Given("prepare request payload for update {string} API")
	public void prepare_request_payload_for_update_API(String resources) throws IOException {
		// update "SaveAgreementDocumentDetails API
		res = APIResources.valueOf(resources);
		if("SaveAgreementDocumentDetails".equalsIgnoreCase(res.name().trim()))
		{
			req = APIUtilsCommon.postHeaderRequest().body(AgreementDocument.updateAgreementDocumentBody());
		}
	}

	@When("send post request for SaveAgreementDocumentDetails update API")
	public void send_post_request_for_save_agreement_document_details_update_api() {
		// update "SaveAgreementDocumentDetails API
		
		resp = req.when().post(res.getResources());

	}

	@Then("check same documentis contain api response")
	public void check_same_documentis_contain_api_response() {
		// update "SaveAgreementDocumentDetails API
    String responsebody = resp.then().log().all().extract().response().asString();
    JsonPath json = new JsonPath(responsebody);
    int agreementid = json.getInt("data");
    Assert.assertEquals(agreementid, agreementId);
    
	}

}
