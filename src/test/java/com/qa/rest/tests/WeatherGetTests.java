package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherGetTests {
	
	@Test
	public void getWeatherDetailsWithCorrectCityNameTest(){
		
		//1. define the base url
		//http://restapi.demoqa.com/utilities/weather/city
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
		//2. define the http request:
		RequestSpecification httpRequest = RestAssured.given();
		
		//3. make a request/execute the request:
		Response response = httpRequest.request(Method.GET, "/Pune");
		
		//4. get the response body:
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
		//validate city name or validate the key or value
		Assert.assertEquals(responseBody.contains("Pune"), true);
		
		//5. get the status code and validate it:
		int statusCode = response.getStatusCode();
		System.out.println("the status code is: "+ statusCode);
		
		Assert.assertEquals(statusCode, 200);
		
		System.out.println("the status line is: "+ response.getStatusLine());
		
		//6. get the headers:
		Headers headers = response.getHeaders();
		System.out.println(headers);
		
		String contentType = response.getHeader("Content-Type");
		System.out.println("the value of content-type header is: "+ contentType);
		
		String contentLength = response.getHeader("Content-Length");
		System.out.println("the value of Content-Length header is: "+ contentLength);

		//get the key value by using JsonPath:
		JsonPath jsonPathValue = response.jsonPath();
		String city = jsonPathValue.get("City");
		System.out.println("the value of city is: "+ city);
		
		String temp = jsonPathValue.get("Temperature");
		System.out.println("the value of Temperature is: "+ temp);

		String Humidity = jsonPathValue.get("Humidity");
		System.out.println("the value of Humidity is: "+ Humidity);

		String WeatherDescription = jsonPathValue.get("WeatherDescription");
		System.out.println("the value of WeatherDescription is: "+ WeatherDescription);

		String WindSpeed = jsonPathValue.get("WindSpeed");
		System.out.println("the value of WindSpeed is: "+ WindSpeed);

		String WindDirectionDegree = jsonPathValue.get("WindDirectionDegree");
		System.out.println("the value of WindDirectionDegree is: "+ WindDirectionDegree);

		
		
	}
	
	
	@Test
	public void getWeatherDetailsWithInCorrectCityNameTest(){
		
		//1. define the base url
		//http://restapi.demoqa.com/utilities/weather/city
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
		//2. define the http request:
		RequestSpecification httpRequest = RestAssured.given();
		
		//3. make a request/execute the request:
		Response response = httpRequest.request(Method.GET, "/test123");
		
		//4. get the response body:
		String responseBody = response.getBody().asString();
		System.out.println("Respjnse Body is: "+ responseBody);
		//validate city name or validate the key or value
		Assert.assertEquals(responseBody.contains("internal error"), true);
		
		//5. get the status code and validate it:
		int statusCode = response.getStatusCode();
		System.out.println("the status code is: "+ statusCode);
		
		Assert.assertEquals(statusCode, 400);
	
	}
	

}
