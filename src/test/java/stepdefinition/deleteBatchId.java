package stepdefinition;

import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class deleteBatchId {

	private String path;
	RequestSpecification request;
	Map<String, Object> requestParams;
	Response response;

	//https://lms-backend-service.herokuapp.com/lms/batches/119
	
	@Given("The delete service with URL and path")
	public void the_delete_service_with_url_and_path() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		path = "/batches";
	}

	@When("Delete request by {string} is made")
	public void delete_request_by_is_made(String batchid) {
		response = RestAssured.given().log().all().delete(path + "/" + batchid);
	}

	@Then("Validate the status code")
	public void validate_the_status_code() {
		Assert.assertEquals(200, response.getStatusCode());
	}
}
