package restassure;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
//import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class Test_Get {
	
	@Test
	public void get() {
//		Response response = get("https://reqres.in/api/users?page=2");
//	    System.out.println(response.getStatusCode());
//	    System.out.println(response.getBody());
//	    System.out.println(response.asString());
	
	}
	
	@Test
	public void newGet() {
		given().get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200);
		
	}

	
	@Test
	public void newPost() {
		
		String req = "{\r\n"
				+ "   \"coverpage\":{\r\n"
				+ "      \"trxSequence\":637,\r\n"
				+ "      \"uuid\":\"f5bdde66-c19e-4bd4-89ab-564086af175b\",\r\n"
				+ "      \"objectBusinessId\":\"12021_REG1TEST-1---\",\r\n"
				+ "      \"applicationTimestamp\":\"2020-12-10T12:27:31\",\r\n"
				+ "      \"action\":\"POST\",\r\n"
				+ "      \"encoding\":\"UTF-8\",\r\n"
				+ "      \"destinationApplication\":\"ELONLINE\",\r\n"
				+ "      \"routingFilters\":[\r\n"
				+ "         {\r\n"
				+ "            \"name\":\"Brand\",\r\n"
				+ "            \"value\":\"MAC\"\r\n"
				+ "         },\r\n"
				+ "         {\"name\":\"Region\",\"value\":\"UKDC\"}\r\n"
				+ "      ]\r\n"
				+ "   },\r\n"
				+ "   \"customerLookupInput\":{\r\n"
				+ "      \"brand\":\"1\",\r\n"
				+ "      \"region\":\"2\",\r\n"
				+ "      \"view\":\"full\",\r\n"
				+ "      \"email\":\"maneesh.upadhyay@skillnetinc.com\"\r\n"
				+ "   }\r\n"
				+ "}";
		String url = "http://wmtnqaa.am.elcompanies.net:58611/rest/elc/ws/loyalty/na/customers/lookup";
		Response response =  given()
				            .auth()
				            .basic("xstorePOS", "POS@123")
				            .contentType(ContentType.JSON)
				            .body(req)
				            .post(url)
				            .then().extract().response();
		
		System.out.println(response.getStatusLine());
		
		if (response.statusCode()==200) {
			 String json = response.asString();
			 JSONObject obj = new JSONObject(json);
		    
			 String customerTier = obj.getJSONObject("customerLookupOutput").getJSONObject("loyaltyLevelCurrent").getString("rbTierName");
		     System.out.println("Tier: " +customerTier);
		     
		     int currentAvailable = obj.getJSONObject("customerLookupOutput").getJSONObject("loyaltyLevelCurrent").getJSONObject("points").getInt("currentAvailable");
		     System.out.println("Points: " +currentAvailable);
		} else {
			System.out.println("Unable to get data. Error code: "+response.getStatusCode() );
		}
			
	     
	}
	
	@Test
	public static String getMembershipToken(String email) {
		String req = "{\r\n"
				+ "	\"coverpage\": {\r\n"
				+ "		\"trxSequence\": 1706,\r\n"
				+ "		\"uuid\": \"81314912-272f-4623-adbd-3413335d8162\",\r\n"
				+ "		\"objectBusinessId\": \"12117REG1LAB-1--1869-10:32:47\",\r\n"
				+ "		\"applicationTimestamp\": \"2021-01-25T10:57:28\",\r\n"
				+ "		\"action\": \"POST\",\r\n"
				+ "		\"encoding\": \"UTF-8\",\r\n"
				+ "		\"destinationApplication\": \"ELONLINE\",\r\n"
				+ "		\"routingFilters\": [\r\n"
				+ "			{\r\n"
				+ "				\"name\": \"Brand\",\r\n"
				+ "				\"value\": \"MAC\"\r\n"
				+ "			},\r\n"
				+ "			{\r\n"
				+ "				\"name\": \"Region\",\r\n"
				+ "				\"value\": \"NCSA\"\r\n"
				+ "			}\r\n"
				+ "		]\r\n"
				+ "	},\r\n"
				+ "	\"customerLookupInput\": {\r\n"
				+ "		\"brand\": \"1\",\r\n"
				+ "		\"region\": \"0\",\r\n"
				+ "		\"view\": \"full\",\r\n"
				+ "		\"email\": \""
				+ email
				+ "\"\r\n"
				+ "	}\r\n"
				+ "}";
		String url = "http://wmtnqaa.am.elcompanies.net:58611/rest/elc/ws/loyalty/na/customers/lookup";
		Response response =  given()
				            .auth()
				            .basic("xstorePOS", "POS@123")
				            .contentType(ContentType.JSON)
				            .body(req)
				            .post(url)
				            .then().extract().response();
		
		System.out.println(response.getStatusLine());
		
		if (response.statusCode()==200) {
			 String json = response.asString();
			 JSONObject obj = new JSONObject(json);
		    
			 String customerToken = obj.getJSONObject("customerLookupOutput").getJSONObject("customer").getString("membershipToken");
		    
		     return customerToken;
		    
		} else {
			System.out.println("Unable to get data. Error code: "+response.getStatusLine() );
			 return null;
		}
		
	}
	
	
	@Test
	public void offerLookup() {
		
		String memToken = getMembershipToken("adarsh@adarsh.com");
		
		String req = "{\r\n"
				+ "	\"coverpage\": {\r\n"
				+ "		\"trxSequence\": 1413,\r\n"
				+ "		\"uuid\": \"88deb463-2a2c-46a3-8b78-c4b525ef6f94\",\r\n"
				+ "		\"objectBusinessId\": \"12117REG1LAB-1---\",\r\n"
				+ "		\"applicationTimestamp\": \"2021-01-14T11:05:42\",\r\n"
				+ "		\"action\": \"POST\",\r\n"
				+ "		\"encoding\": \"UTF-8\",\r\n"
				+ "		\"destinationApplication\": \"ELONLINE\",\r\n"
				+ "		\"routingFilters\": [\r\n"
				+ "			{\r\n"
				+ "				\"name\": \"Brand\",\r\n"
				+ "				\"value\": \"MAC\"\r\n"
				+ "			},\r\n"
				+ "			{\r\n"
				+ "				\"name\": \"Region\",\r\n"
				+ "				\"value\": \"NCSA\"\r\n"
				+ "			}\r\n"
				+ "		]\r\n"
				+ "	},\r\n"
				+ "	\"offersInput\": {\r\n"
				+ "		\"brand\": \"1\",\r\n"
				+ "		\"region\": \"0\",\r\n"
				+ "		\"membershipToken\": \""
				+ memToken
				+ "\"\r\n"
				+ "	}\r\n"
				+ "}";
		
		String url = "http://wmtnqaa.am.elcompanies.net:58611/rest/elc/ws/loyalty/na/offers";
		Response response =  given()
				            .auth()
				            .basic("xstorePOS", "POS@123")
				            .contentType(ContentType.JSON)
				            .body(req)
				            .post(url)
				            .then().extract().response();
	
		System.out.println(response.getStatusLine());
		System.out.println(response.asString());
		String json = response.asString();
		JSONObject obj = new JSONObject(json);
		System.out.println("# of Offers: " +obj.getJSONObject("offersOutput").getJSONObject("offers").length());
		JsonPath jsonPathEvaluator = response.jsonPath();
//		List<String> offerList = jsonPathEvaluator.getList("$.offersOutput.offers.*");
//		
//		
//		for (String offer: offerList) {
//			
//			System.out.println(offer.toString());
//		}
	}

	
}
