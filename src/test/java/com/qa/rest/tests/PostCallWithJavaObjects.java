package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.rest.objects.CustomerResponseFailure;
import com.qa.rest.objects.CustomerResponseSuccess;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCallWithJavaObjects {
//Types of authentication:
	//1. user name & password
	//2. Auth Token
	//3. Secret Keys/Token/Sessions ID/Customer ID/Consumer Key
	//4. Security based questions
	
	
	
	@Test
	public void createCustomerTest() {
		// 1. define the base url:
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		// 2. define the http request:
		RequestSpecification httpRequest = RestAssured.given();

		// 3. create a json object with all the fields:
		org.json.simple.JSONObject requestJson = new org.json.simple.JSONObject();
		requestJson.put("FirstName", "Michael13");
		requestJson.put("LastName", "Peter1312");
		requestJson.put("UserName", "micpeter1132");
		requestJson.put("Password", "mich1231132");
		requestJson.put("Email", "mich@gmail2311.com");

		// 4. add header:
		httpRequest.header("Content-Type", "application/json");

		// 5. add the json payload to the body of the request:
		httpRequest.body(requestJson.toJSONString());

		// 6. post the request and get the response:
		Response response = httpRequest.post("/register");

		// 7. get the response body:
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);

		// Deserialization the response into CustomerResponse class:
		if (response.statusCode() == 201) {
			CustomerResponseSuccess customerResponse = response.as(CustomerResponseSuccess.class);

			System.out.println(customerResponse.SuccessCode);
			System.out.println(customerResponse.Message);

			Assert.assertEquals("OPERATION_SUCCESS", customerResponse.SuccessCode);
			Assert.assertEquals("Operation completed successfully", customerResponse.Message);
		} 
		else if (response.statusCode() == 200) {
			CustomerResponseFailure customerResponse = response.as(CustomerResponseFailure.class);

			System.out.println(customerResponse.FaultId);
			System.out.println(customerResponse.fault);

			Assert.assertEquals("User already exists", customerResponse.FaultId);
			Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", customerResponse.fault);
		}

	}

}
