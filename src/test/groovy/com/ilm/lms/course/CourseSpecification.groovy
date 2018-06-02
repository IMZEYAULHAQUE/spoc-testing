package com.ilm.lms.course

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

import com.ilm.lms.course.service.CourseService

import groovyx.net.http.RESTClient
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(
	classes = CourseApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
//@ScanScopedBeans
//@ActiveProfiles(value = "test")
@ContextConfiguration
class CourseSpecification extends Specification {

	def "no course exist"() {

		given:
		CourseService courseService = Mock()
		courseService.findAllCourse() >> []


		when: "find all course is called"
		courseService.findAllCourse()

		then: "should not be null and size should be zero"
		1 * courseService.findAllCourse()
		courseService.findAllCourse() != null
		courseService.findAllCourse().size() == 0
		
	}
	
	def "find all course api"() {
		RESTClient restClient = new RESTClient("http://localhost:8050")
		
		when:
			def response = restClient.get(path: '/courses')
			
		then:
			response.status == 200
			response.responseData[0].courseId == 1
			
	}
	
	@Unroll("Check courseId = #course_id matches [#expectedCourseName #expectedVersion]")
	def "Check courseId = #course_id matches [#expectedCourseName #expectedVersion]"() {
		RESTClient restClient = new RESTClient("http://localhost:8050")
		
		when:
			def response = restClient.get(path: '/courses/' + course_id)
			
		then:
			response.status == 200
			response.responseData.courseId == expectedCourseId
			response.responseData.courseName == expectedCourseName
			response.responseData.version == expectedVersion
			
		where:
		course_id		|		expectedCourseId		| expectedCourseName	| expectedVersion
		1				|		1						| "Core Java"			| 'Version 6'
		2				|		2						| "Core Java"			| 'Version 8'
	}
}
