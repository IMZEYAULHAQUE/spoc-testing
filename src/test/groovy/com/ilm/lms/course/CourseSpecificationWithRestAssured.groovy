package com.ilm.lms.course

import groovy.json.JsonSlurper
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import static io.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.equalTo

@SpringBootTest/*(
	classes = CourseApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)*/
@ActiveProfiles(value = "test")
@ContextConfiguration
class CourseSpecificationWithRestAssured extends Specification {
	
	def setup() {
		RestAssured.useRelaxedHTTPSValidation()
		RestAssured.baseURI = "http://localhost"
		RestAssured.port = 8050
		RestAssured.basePath = "/courses"
	}
	
	@Unroll
	def "GET | Positive | for course = #courseId courseName shoule be #expectedCourseName"() {
		
		when:
		Response response = given().get("${courseId}")
		
		then:
		response.statusCode == 200
		response.then().body("courseName", equalTo(expectedCourseName), "version", equalTo(expectedVersion))
		
		where:
		courseId		|		expectedCourseName		|		expectedVersion
		1				|		"Core Java"				| 		"Version 6"
		2				|		"Core Java"				| 		"Version 8"
		
		
	}
	
	def "GET | Negative | invalid course id should return status code 404"() {
		
		when:
		Response response = given().get("${courseId}")
		
		then:
		response.statusCode == 404
		def errorMap = new JsonSlurper().parseText(response.then().body().contentType(ContentType.JSON).extract().response().asString())

		println errorMap
		errorMap.status == 404
		errorMap.message == "Course with id [${courseId}] not found."
		
		where:
		courseId 	| _
		4			| _
		
	}

}
