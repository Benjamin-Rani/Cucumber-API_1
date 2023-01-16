package stepdefinition;




import java.util.Map;

//import org.json.JSONArray;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getAllProgram {
	
	private String path;
	RequestSpecification request;
	Map<String, Object> requestParams;
	Response response;
	
	@Given("get all programs URL and path")
	public void the_batch_by_program_id_service_with_url_and_path() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		path = "/allPrograms";
	}

	@When("Get request for all programs")
	public void get_request_by_is_made() {
		RequestSpecification requestSpec = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json").build();
		response = RestAssured.given().log().all().spec(requestSpec).get(path);
	}
	
	@Then("Validate the Get status code")
	public void validate_the_get_status_code() {
	  Assert.assertEquals(200, response.getStatusCode());
	}


}