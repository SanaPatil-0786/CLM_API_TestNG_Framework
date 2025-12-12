package testdata;

import java.util.Random;
import java.util.UUID;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import serviceprovider_serialization.SaveStakeholderServiceProvider_POJO;
import serviceprovider_serialization.ServiceProvider_POJO;
import stepDefinationCLMAPI.ContractHeaderStepDefination;
import stepDefinationCLMAPI.ServiceProviderStepDefination;

public class ServiceProviderStakeholder {

	public static ServiceProvider_POJO obj;
	public static SaveStakeholderServiceProvider_POJO obj2;
	
	public static ServiceProvider_POJO reqBodyContactInfo() {
		Random ran = new Random();
		String num = "9";
		StringBuffer sb = new StringBuffer(num);
		obj= new ServiceProvider_POJO();
		obj.setCompanyId(ServiceProviderStepDefination.companyid);
		obj.setContactDesignation("contactDesignation"+UUID.randomUUID().toString());
		obj.setContactFullName("contactpersonname"+UUID.randomUUID().toString());	
		for (int i = 1; i<10; i++) {
			sb.append(ran.nextInt(10));
		}
		obj.setContactNumber(sb.toString());
		obj.setEmailId(UUID.randomUUID().toString()+"@gmail.com");
		obj.setUserId(2);
		return obj;
	}
	public static void assertionmethod() {
		String responsebody = ServiceProviderStepDefination.res.asString();
		JsonPath json = new JsonPath(responsebody);
		int contactid = json.getInt("data.stakeHolderContactInfromationListDtoList[0].companycontactid");
		int companyid = json.getInt("data.stakeHolderContactInfromationListDtoList[0].companyid");
		String fullname = json.getString("data.stakeHolderContactInfromationListDtoList[0].contactfullname");
		String number = json.getString("data.stakeHolderContactInfromationListDtoList[0].contactnumber");
		String email = json.getString("data.stakeHolderContactInfromationListDtoList[0].emailid");
		String designation = json.getString("data.stakeHolderContactInfromationListDtoList[0].contactdesignation");
		Assert.assertEquals(contactid, ServiceProviderStepDefination.newcontactid);
		Assert.assertEquals(companyid, ServiceProviderStepDefination.companyid);
		Assert.assertEquals(fullname, obj.getContactFullName());
		Assert.assertEquals(number, obj.getContactNumber());
		Assert.assertEquals(email, obj.getEmailId());
		Assert.assertEquals(designation, obj.getContactDesignation());
	}
	
	public static SaveStakeholderServiceProvider_POJO saveServiceProviderBody() {
		obj2 = new SaveStakeholderServiceProvider_POJO();
		obj2.setContractstakeholdersserviceproviderid(0);
		obj2.setCompanyid(ServiceProviderStepDefination.companyid);
		obj2.setCompanyaddressid(ServiceProviderStepDefination.compaddressid);
		obj2.setSigningauthorityid(ServiceProviderStepDefination.newcontactid);
		obj2.setCompanycontactid(String.valueOf(ServiceProviderStepDefination.contactid));
		obj2.setTriggerid(5);
		obj2.setContractid(ContractHeaderStepDefination.contractId);
		return obj2;
	}
}
