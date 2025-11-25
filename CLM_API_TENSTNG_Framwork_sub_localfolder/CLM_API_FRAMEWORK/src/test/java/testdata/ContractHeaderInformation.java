package testdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import io.restassured.path.json.JsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import serializationContractHeader.SaveHeaderInformation;

import stepDefinationCLMAPI.ContractHeaderStepDefination;

public class ContractHeaderInformation {
	
	public static  SaveHeaderInformation contractreqdata;
	
	public static SaveHeaderInformation createRandomContract() {
		
		
		Random rand = new Random();
		SaveHeaderInformation contract = new SaveHeaderInformation();

		contract.setContractid(0); // rand.nextInt(1000
		contract.setContracttitle("Title_" + UUID.randomUUID().toString());
		contract.setContractstageid(1);
		contract.setContractdescription("Description_" + UUID.randomUUID().toString());
		contract.setContractkeywords("Keyword_" + rand.nextInt(100) + ",Keyword_" + rand.nextInt(100));
		contract.setContractexecutedat("Location_" + rand.nextInt(100));
		contract.setAnnualcontractvalue(rand.nextInt(10000));
		contract.setCurrencyId(53);
		contract.setTypeid(13);
		contract.setCategoryid(18);
		contract.setClassificationid(28);
		contract.setProcessid(31);
		contract.setUserID(3);
		contract.setTenantid(null); // or rand.nextInt(10)

		return contract;

	}

	public static void requestBodyPrint() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		SaveHeaderInformation contract1 = createRandomContract();
		String json = mapper.writeValueAsString(contract1);
		System.out.println("json body of save header information api : \n" + json);
	}
	
	public static void assertionResponse() {
		contractreqdata  = createRandomContract(); // stored setter data
		//JsonPath jsonPath = new JsonPath(StepDefination.res.toString());
		JsonPath jsonPath = new JsonPath(ContractHeaderStepDefination.res.asString());

		Map<String, Object> responseMap = jsonPath.getMap("data.contractheaderinformationList[0]"); // response of
																									// GetContractHeaderDetails
																									// API
		// response of get api stored in responseMap

		// Convert request object to map
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> requestMap = mapper.convertValue(contractreqdata, Map.class);

		// Compare maps
		List<String> matched = new ArrayList<>();
		List<String> mismatched = new ArrayList<>();

		for (String reqKey : requestMap.keySet()) {
			Object reqVal = requestMap.get(reqKey);
			Object resVal = responseMap.getOrDefault(reqKey.toLowerCase(), responseMap.get(reqKey));

			if (resVal != null && reqVal != null && reqVal.toString().equalsIgnoreCase(resVal.toString())) {
				matched.add(reqKey);
			} else {
				mismatched.add(reqKey + " → Expected: " + reqVal + ", Actual: " + resVal);
			}
		}

		System.out.println("Matched Keys: " + matched);
		System.out.println("Mismatched Keys: " + mismatched);

	}
}
