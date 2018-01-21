package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateCustomerPostCall {

	@Test
	public void createCustomerTest() {
		// 1. define the base url:
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		// 2. define the http request:
		RequestSpecification httpRequest = RestAssured.given();

		// 3. create a json object with all the fields:
		org.json.simple.JSONObject requestJson = new org.json.simple.JSONObject();
		requestJson.put("FirstName", "Michael1");
		requestJson.put("LastName", "Peter1");
		requestJson.put("UserName", "micpeter1");
		requestJson.put("Password", "mich1231");
		requestJson.put("Email", "mich@gmail1.com");

		// 4. add header:
		httpRequest.header("Content-Type", "application/json");

		// 5. add the json payload to the body of the request:
		httpRequest.body(requestJson.toJSONString());

		// 6. post the request and get the response:
		Response response = httpRequest.post("/register");

		// 7. get the response body:
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);

		// 8. get the status code and validate it:
		int statusCode = response.getStatusCode();
		System.out.println("the status code is: " + statusCode);

		Assert.assertEquals(statusCode, 201);

		System.out.println("the status line is: " + response.getStatusLine());

		// 9. get the headers:
		Headers headers = response.getHeaders();
		System.out.println(headers);

		String contentType = response.getHeader("Content-Type");
		System.out.println("the value of content-type header is: " + contentType);

		String contentLength = response.getHeader("Content-Length");
		System.out.println("the value of Content-Length header is: " + contentLength);
		
		

	}

}
