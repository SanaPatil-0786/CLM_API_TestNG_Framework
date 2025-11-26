package testdata;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import org.testng.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import resources.APIUtilsCommon;
import resources.ConfigReader;
import serializationAgreementDocument.SaveAgreementDocumentDetails;

import stepDefinationCLMAPI.*;

public class AgreementDocument {
	private static final String Base_Url = ConfigReader.get("base_url");
	public static SaveAgreementDocumentDetails agreementobject;

	public static SaveAgreementDocumentDetails createAgreement() {
		Random rand = new Random();
		 agreementobject = new SaveAgreementDocumentDetails();
	     agreementobject = new SaveAgreementDocumentDetails();
		agreementobject.setAgreementDocumentID(0);
		agreementobject.setContractId(ContractHeaderStepDefination.contractId);
		//agreementobject.setContractId(880);

		agreementobject.setDocumentTypeId(26);
		agreementobject.setDocumentTitle("AgreementDocumentTitle_" + UUID.randomUUID().toString());
		agreementobject.setStageId(ContractHeaderStepDefination.contractstageid);
		
		//agreementobject.setStageId(1);
		agreementobject.setDocumentRevision("1.0");
		agreementobject.setComment("agreement document comments " + UUID.randomUUID().toString());
		agreementobject.setTriggeredbyId(3);
		return agreementobject;
	}

	public static RequestSpecification attachmentBody() {
		File attachment = new File("C:\\Users\\sana.patil\\Downloads\\file-example_PDF_1MB.pdf");
		return new RequestSpecBuilder()
				.setBaseUri(Base_Url)
				//.addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjIiLCJyb2xlIjoiIiwibmJmIjoxNzYzNDY5MTA0LCJleHAiOjE3NjQwNzM5MDQsImlhdCI6MTc2MzQ2OTEwNH0.APc-pcHn8ORTKwFVCXFC8zA5iZIkvNDpMPJEPDG0KQw")
				.addHeader("Authorization", "Bearer " + ContractHeaderStepDefination.token)
				.addMultiPart("Files", attachment)
				.addMultiPart("chunkNumber", "1")
				.addMultiPart("totalChunks", "1")
				.addMultiPart("Attachmentid", "0")
				//.addMultiPart("Contractid", "880")
				.addMultiPart("Contractid", String.valueOf(ContractHeaderStepDefination.contractId))

				.addMultiPart("Documentid", String.valueOf(AgreementDocumentStepDefination.agreementId))
				
				.addMultiPart("Attachmentkeywords", "contract condition verification with 25 mb pdf file")
				.addMultiPart("Isactive", "0")
				.addMultiPart("Modifiedby", "2")
				.addMultiPart("Processname", "Agreement Documents")
				.setContentType("multipart/form-data").
				build();
	}

	public static RequestSpecification getlistrequest() throws IOException {
		return APIUtilsCommon.getPayloadbody().
				queryParam("ContractId", ContractHeaderStepDefination.contractId)
				//queryParam("ContractId", 880)
				.queryParam("DocumenttypeId", AgreementDocumentStepDefination.agreementId);
	}

	public static RequestSpecification getDocumentDetailRequest() throws IOException {
		return APIUtilsCommon.getPayloadbody().queryParam("agreementDocumentId",AgreementDocumentStepDefination.agreementId);
	}

	public static void validateDocumentDetails(JsonPath json) {
		
		
		int actualtypeid = json.getInt("data.agreementDocumentDtoList[0].documenttypeid");
		int expectedtypeid  = agreementobject.getDocumentTypeId();   //26
		
		int actaulstageid = json.getInt("data.agreementDocumentDtoList[0].stageid");
		int expectedstageid = agreementobject.getStageId();
		
		String actualDocTitle = json.getString("data.agreementDocumentDtoList[0].documenttitle");
		String expecteddoctitle = agreementobject.getDocumentTitle();
		
		String actualcomments = json.getString("data.agreementDocumentDtoList[0].documentcomment");
		String expectedcoments  = agreementobject.getComment();
		
		int actualattachmentid = json.getInt("data.attachmentDocumentDtoList[0].attachmentid");
		int expectedattachmentid = AgreementDocumentStepDefination.attachmentId;
		
		int actualcontractid = json.getInt("data.attachmentDocumentDtoList[0].contractid");
	     int expectedcontractid = ContractHeaderStepDefination.contractId;
		//int expectedcontractid = 880;
		
		int actualdocumentid = json.getInt("data.attachmentDocumentDtoList[0].documentid");
		int expectedagreementid = AgreementDocumentStepDefination.agreementId;
		
		Assert.assertEquals(actualtypeid,expectedtypeid,"Document Type ID mismatch!");
		Assert.assertEquals(actaulstageid,expectedstageid,"document stage id mismatch");
		Assert.assertEquals(actualDocTitle,expecteddoctitle,"document title is not match");
		Assert.assertEquals(actualcomments,expectedcoments,"document comments is not match");
		Assert.assertEquals(actualattachmentid,expectedattachmentid,"attachment id is not not match");
		Assert.assertEquals(actualcontractid,expectedcontractid,"contract id is not match");
		Assert.assertEquals(actualdocumentid,expectedagreementid, "agreement id is not match");
	}

	
	public static RequestSpecification getattachmentrequest() throws IOException
	{
		return APIUtilsCommon.getPayloadbody().queryParam("Documentid",AgreementDocumentStepDefination.agreementId);
	}
	
	
	public static SaveAgreementDocumentDetails updateAgreementDocumentBody() {
		Random rand1 = new Random();
		SaveAgreementDocumentDetails agreementobject1 = new SaveAgreementDocumentDetails();
		agreementobject1.setAgreementDocumentID(AgreementDocumentStepDefination.agreementId);
		agreementobject1.setContractId(ContractHeaderStepDefination.contractId);
		//agreementobject1.setContractId(880);

		agreementobject1.setDocumentTypeId(27);
		agreementobject1.setDocumentTitle("UpdateAgreementDocumentTitle_" + UUID.randomUUID().toString());
		agreementobject1.setStageId(ContractHeaderStepDefination.contractstageid);
		//agreementobject1.setStageId(880);
		
		agreementobject1.setDocumentRevision("1.01");
		agreementobject1.setComment("update agreement document comments " + UUID.randomUUID().toString());
		agreementobject1.setTriggeredbyId(3);
		return agreementobject1;
	}
		
}
