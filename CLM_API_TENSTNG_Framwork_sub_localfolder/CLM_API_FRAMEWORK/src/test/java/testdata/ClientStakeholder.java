package testdata;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import clientstakeholder_serialization.*;
import io.restassured.specification.RequestSpecification;
import resources.APIUtilsCommon;
import stepDefinationCLMAPI.ClientStakeholderStepDefination;

public class ClientStakeholder {
	
	public static SaveClientInformation_POJO objpojo;
	public static SaveAddressPOJO obj1;
	public static SaveContactInformation_POJO obj2;
	
	public static SaveClientInformation_POJO saveClientBody () {
		
		try {
			objpojo = new SaveClientInformation_POJO();
			objpojo.setClientinformationid(0);
			objpojo.setClientname("Added New CLIENT change"+UUID.randomUUID().toString());
			objpojo.setClientcode("null");
			objpojo.setCreatedby(3);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception log "+e);
		}
		return objpojo;
	}
	
	public static SaveAddressPOJO saveAddress() {
		obj1 = new SaveAddressPOJO();
		obj1.setClientInformationId(ClientStakeholderStepDefination.clientid);
		obj1.setClientaddress("Added new address "+ UUID.randomUUID().toString());
		obj1.setCreatedby(3);
		return obj1;
	}
	public static RequestSpecification method() throws IOException {
		return APIUtilsCommon.postHeaderRequest().body(ClientStakeholder.saveAddress());
	}
	
	public static SaveContactInformation_POJO savecontact() {
		Random ran = new Random();
		String num = "9";
		StringBuffer sb = new StringBuffer(num);
		obj2 = new SaveContactInformation_POJO();
		
		obj2.setClientInformationid(ClientStakeholderStepDefination.clientid);
		obj2.setContactName("Added new contact "+ UUID.randomUUID().toString());
		for (int i = 1; i<10; i++) {
			sb.append(ran.nextInt(10));
		}
		obj2.setContactNumber(sb.toString());
		obj2.setEmailid("emailid"+ran.nextInt(10)+"@gmail.com");
		obj2.setDesignation("designation"+UUID.randomUUID().toString());
		return obj2;
	}
	public static RequestSpecification method2() throws IOException {
		return APIUtilsCommon.postHeaderRequest().body(ClientStakeholder.savecontact());
	}
	

}
