package attachmentAPI;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
//import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
//import stepDefinationCLMAPI.AgreementDocumentStepDefination;
import testdata.AgreementDocument;

public class SaveAttachmentAPI {
	public static RequestSpecification req;
	public static APIResources res;
	public static Response resp;
		public static void main(String[] args) throws IOException {
			
		req = given().spec(attachmentBody());
		//req= getPayloadbody();
		resp = req.when().post("/api/Attachment/SaveAttachmentDetails");
		int statuscode = resp.getStatusCode();
		String responsebody = resp.getBody().asString();
		System.out.println("status code is :" +statuscode);
		System.out.println("response body of attachment api: " +responsebody);
		
//			File attachment = new File("C:\\Users\\sana.patil\\Downloads\\CLM_API_Collection farmwork Design Estimation. (1)(1).xlsx");
//			Response resp = given().baseUri("http://192.168.10.64:2025")
//				.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjIiLCJyb2xlIjoiIiwibmJmIjoxNzY0MTM0NjgzLCJleHAiOjE3NjQ3Mzk0ODMsImlhdCI6MTc2NDEzNDY4M30.EMDMc4tRbPpFIa-8B2KKSP9KTP6jm-cWy86Ece0Z05Y")
//					.multiPart("Files", attachment)
//					.multiPart("chunkNumber", String.valueOf(1))
//					.multiPart("totalChunks", String.valueOf(1))
//					.multiPart("Attachmentid", String.valueOf(0))
//					.multiPart("Contractid", String.valueOf(845))
//					.multiPart("Documentid", String.valueOf(647))				
//					.multiPart("Attachmentkeywords", "contract condition verification with 25 mb pdf file")
//					.multiPart("Isactive", String.valueOf(0))
//					.multiPart("Modifiedby", String.valueOf(2))
//					.multiPart("Processname", "Agreement Documents")
//					.when().post("/api/Attachment/SaveAttachmentDetails")
//					.then().log().all().extract().response();
//		      System.out.println("api statuc code is : " +resp.getStatusCode());
//		      System.out.println("BODY OF API :"+ resp.asString());
//			System.out.println(attachment.exists());
			
			
			
			
		
		
	}
	public static RequestSpecification attachmentBody() {
		File attachment = new File("C:\\Users\\sana.patil\\Downloads\\CLM_API_Collection farmwork Design Estimation. (1)(1).xlsx");
		return new RequestSpecBuilder()
				.setBaseUri("http://192.168.10.64:2025")
				.addHeader("Authorization",  "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjIiLCJyb2xlIjoiIiwibmJmIjoxNzY0MTM0NjgzLCJleHAiOjE3NjQ3Mzk0ODMsImlhdCI6MTc2NDEzNDY4M30.EMDMc4tRbPpFIa-8B2KKSP9KTP6jm-cWy86Ece0Z05Y")
				.addMultiPart("Files", attachment)
				.addMultiPart("chunkNumber", String.valueOf(1))
				.addMultiPart("totalChunks", String.valueOf(1))
				.addMultiPart("Attachmentid", String.valueOf(0))
				.addMultiPart("Contractid", String.valueOf(845))
				.addMultiPart("Documentid", String.valueOf(647))				
				.addMultiPart("Attachmentkeywords", "contract condition verification with 25 mb pdf file")
				.addMultiPart("Isactive", String.valueOf(0))
				.addMultiPart("Modifiedby", String.valueOf(2))
				.addMultiPart("Processname", "Agreement Documents")
				//.setContentType("multipart/form-data") 
				.build();
	}
	public static  RequestSpecification getPayloadbody()  {
		return given().spec(attachmentBody()).contentType("multipart/form-data") 
				//.baseUri("http://192.168.10.64:2025")
				;
	}

}
