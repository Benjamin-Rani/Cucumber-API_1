package stepdefinition2;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Programname {
	private String path;
	RequestSpecification request;
	Map<String, Object> requestParams;
	Response response;
	
	@Given("V1 A update Service with URL and path {string}")
	public void a_update_service_with_url_and_path(String programName) 
	{

		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		path = "/program/"+programName;
	}

	@When("{string},{string},{string},{string},creationTime,lastModTime are updated")
	public void creation_time_last_mod_time_are_updated(String programId,String programName, String programDescription, String programStatus) 
	{
	    
		ZonedDateTime dateTime = ZonedDateTime.now();
		String time = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		requestParams = new HashMap<>();
		requestParams.put("programId", programId);
		requestParams.put("programName", programName);
		requestParams.put("programStatus", programStatus);
		requestParams.put("programDescription", programDescription);
		requestParams.put("creationTime", time);
		requestParams.put("lastModTime", time);

	}

	@When("V1 PUT request is made")
//	public void put_request_is_made_by_(String programName) 
	public void put_request_is_made_by_()
	{
		RequestSpecification requestSpec = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json").build();
//		response = RestAssured.given().spec(requestSpec).put(path + "/" + programName);	
		request = RestAssured.given().log().all().spec(requestSpec).body(requestParams);
		//request = RestAssured.given().log().all().spec(requestSpec).body(requestParams);

	}

	@Then("V1 Save programName")
	public void save_program_name() 
	{
		response = request.put(path);
		
	}

	@Then("V1 Validate status code")
	public void validate_status_code() 
	{
		Assert.assertEquals(200, response.getStatusCode());
	}

	@Then("V1 validate required fields {string},{string},{string},{string}")
	public void validate_required_fields(String programId,String programName, String programDescription, String programStatus) 
	{
		JsonPath jsonPathEvaluator = response.jsonPath();
		String responseId = jsonPathEvaluator.get("programId").toString();
		//String responseName = jsonPathEvaluator.get("programName");
		String responseDesc = jsonPathEvaluator.get("programDescription");
		String responseStatus = jsonPathEvaluator.get("programStatus");
		
		Assert.assertEquals(responseId, programId);
		Assert.assertEquals(responseDesc, programDescription);
		Assert.assertEquals(responseStatus, programStatus);
		
		response.getBody().prettyPrint();

	    
	}

	
}
