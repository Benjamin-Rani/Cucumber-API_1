package stepdefinition;




import java.util.Map;

import org.json.JSONArray;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getByProgramId {
	
	private String path;
	RequestSpecification request;
	Map<String, Object> requestParams;
	Response response;
	
	@Given("Get by programId service with URL and path")
	public void the_batch_by_program_id_service_with_url_and_path() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		path = "/programs";
	}

	@When("Get request by {int} is made")
	public void get_request_by_is_made(int programId) {
		RequestSpecification requestSpec = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json").build();
		response = RestAssured.given().log().all().spec(requestSpec).get(path + "/" + programId);
	}
	
	@Then("Validate the Get status code for get allPgm")
	public void validate_the_get_status_code() {
	  Assert.assertEquals(200, response.getStatusCode());
	}

//	@Then("Validate the total batch {int}")
//	public void validate_the_total_batch(Integer count) {
//		JSONArray jsonArray = new JSONArray(response.getBody().asString());
//		Assert.assertFalse(jsonArray.isEmpty());
//		Assert.assertEquals(count.intValue(),jsonArray.length());
//	}
}