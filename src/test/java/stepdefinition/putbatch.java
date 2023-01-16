package stepdefinition;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class putbatch {

	
	private static final String prefixName = "Jan23-Mighty5Testers-";
	private String path;
	RequestSpecification request;
	Map<String,Object> requestParams;
	Response response;
@Given("A update Service with URL and path {string}")
public void a_service_with_url_at_path(String batchId) {
	RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
	path = "/batches/"+batchId;
}

@When("{string},{string},{string},{string},{string},{string},creationTime,lastModTime are updated")
public void ID_creation_time_last_mod_time_are_modified(String batchId, String batchDesc,String batchStatus,String batchNoOfClasses, String programId,String programName) {
	ZonedDateTime dateTime = ZonedDateTime.now();
//	String time = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	requestParams = new HashMap<String, Object>();
//	requestParams.put("batchId", batchId);
//	requestParams.put("batchDescription", batchDesc);
//	requestParams.put("batchStatus", batchStatus);
//	requestParams.put("creationTime", time);
//	requestParams.put("lastModTime", time);
//    | BatchID | batchDescription      | batchStatus | programId | programName |
//String batchName="bluetooth834";
	requestParams.put("batchName",batchId );
	requestParams.put("batchDescription", batchDesc);
	requestParams.put("batchStatus", batchStatus);
	requestParams.put("batchNoOfClasses", batchNoOfClasses);
	requestParams.put("programId", programId);
	requestParams.put("programName", programName);
}

//@When("{string} Called")
//public void called(String string) {
    

@When("PUT request is made")
public void put_request_is_made() {
	RequestSpecification requestSpec = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
			.addHeader("Accept", "application/json").build();
	request = RestAssured.given().log().all().spec(requestSpec).body(requestParams);
}


@Then("Save batchId")
public void save_batch_id() {
	response = request.put(path);
}

@Then("Validate status code")
public void validate_status_code() {
	Assert.assertEquals(200, response.getStatusCode());
}

@Then("Validate required fields {string},{string},{string}")
public void  id_api_training_2023_Active(String batchId, String batchDescription, String batchStatus) {
	JsonPath jsonPathEvaluator = response.jsonPath();
	String responseId = jsonPathEvaluator.get("batchId").toString();
	String responseDesc = jsonPathEvaluator.get("batchDescription");
	String responseStatus = jsonPathEvaluator.get("batchStatus");
	
	Assert.assertEquals(responseId, batchId);
	Assert.assertEquals(responseDesc, batchDescription);
	Assert.assertEquals(responseStatus, batchStatus);
}



}
