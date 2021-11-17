package restassure;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class New_Rest_Demo {

	@Test

	public void testDemo() {

		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method
		// URL.
		// This will return the Response from the server. Store the response in a
		// variable.
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
	}

	@Test

	public void testDemo1() {
		String url = "https://demoqa.com/utilities/weather/city/Hyderabad";

		Response response = given().get(url).then().extract().response();

		System.out.println(response.asString());
		System.out.println(response.getContentType());
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeaders());
	
		JsonPath jsonPathEvaluator = response.jsonPath();
	}

}
