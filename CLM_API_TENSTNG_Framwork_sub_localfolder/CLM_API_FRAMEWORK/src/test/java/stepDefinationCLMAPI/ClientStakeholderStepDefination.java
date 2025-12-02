package stepDefinationCLMAPI;

import io.cucumber.java.en.*;

public class ClientStakeholderStepDefination {

	@Given("a prequest payload is prepared for the {string} API")
	public void a_prequest_payload_is_prepared_for_the_api(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("a POST request is sent to the {string} API")
	public void a_post_request_is_sent_to_the_api(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the response status code should {string}")
	public void the_response_status_code_should(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("take {string} and {string} keyvalue from response body")
	public void take_and_keyvalue_from_response_body(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("a POST request is send to {string} API and add {string} address aginst new created client")
	public void a_post_request_is_send_to_api_and_add_address_aginst_new_created_client(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("check total count of address inside {string} array object should {string}")
	public void check_total_count_of_address_inside_array_object_should(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("a POST request is send to {string} API and add {string} contact aginst new created client")
	public void a_POST_request_is_send_to_API_and_add_contact_aginst_new_created_client(String key1, String key2) {
		// SaveClientContactInformation
	}

	@Then("check total count of contact inside {string} array object should {string}")
	public void check_total_count_of_contact_inside_array_object_should(String key1, String key2) {
		// SaveClientContactInformation
	}
	
	@When("a GET request is send to {string} API")
	public void a_get_request_is_send_to_api(String string) {
		//DeleteClientAddress
		//DeleteClientContactInformation
		//GetAllClientList
	}

	@Then("check out count after delete address")
	public void check_out_count_after_delete_address() {
          //DeleteClientAddress
	}
	@Then("check out count after delete contact")
	public void check_out_count_after_delete_contact()
	{
		//DeleteClientContactInformation
	}
	@Then ("check newly created client is listed in all client list")
	public void check_newly_created_client_is_listed_in_all_client_list() {
		//GetAllClientList
	}

	@Then("check all contacts and address list is available in response body")
	public void check_all_contacts_and_address_list_is_available_in_response_body() {
		//GetClientContactWithAddressList
		
	}
	@Then("check contact details are correct for the provided contact ID")
	public void check_contact_details_are_correct_for_the_provided_contact_ID() {
		//GetKeyStakeHolderInfo
	}
	@Then("check message correctly display and take data keyvalue")
	public void check_message_correctly_display_and_take_data_keyvalue() {
		
	}
}
