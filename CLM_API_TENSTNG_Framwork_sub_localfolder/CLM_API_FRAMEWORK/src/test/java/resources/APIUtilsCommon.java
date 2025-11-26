package resources;

import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import stepDefinationCLMAPI.ContractHeaderStepDefination;

public class APIUtilsCommon {
	private static final String Base_Url = ConfigReader.get("base_url");
	private static final String CONTENT_TYPE = ConfigReader.get("Content-Type");


	// token post API

	public static RequestSpecification tokenRequestPayload() throws IOException {
		String jsonBody = new String(Files.readAllBytes(Paths.get("src/main/java/tokenbody/tokenrequestbody.json")));
		return given().baseUri(Base_Url).header("Content-Type", CONTENT_TYPE).body(jsonBody);
	}

	// Common method to post request ---post header information

	public static RequestSpecification postHeaderRequest() throws IOException {
		return given().log().all().
				baseUri(Base_Url).
				header("Content-Type", CONTENT_TYPE).
				header("Authorization", "Bearer " + ContractHeaderStepDefination.token);
				//header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjIiLCJyb2xlIjoiIiwibmJmIjoxNzYzNDY5MTA0LCJleHAiOjE3NjQwNzM5MDQsImlhdCI6MTc2MzQ2OTEwNH0.APc-pcHn8ORTKwFVCXFC8zA5iZIkvNDpMPJEPDG0KQw");
	}

	// common method for GET API request

	public static RequestSpecification getPayloadbody() throws IOException {
		return given()
				.baseUri(Base_Url).
				//header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjIiLCJyb2xlIjoiIiwibmJmIjoxNzYzNDY5MTA0LCJleHAiOjE3NjQwNzM5MDQsImlhdCI6MTc2MzQ2OTEwNH0.APc-pcHn8ORTKwFVCXFC8zA5iZIkvNDpMPJEPDG0KQw");
			header("Authorization", "Bearer " + ContractHeaderStepDefination.token);			
	}

	// verify post request body data available in response of get api or not
	{

	}

	public static RequestSpecification loggingReports() throws FileNotFoundException {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		// System.out.println("File will be created at: " + new
		// File("login.txt").getAbsolutePath());
		return given().filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log));
	}

}
