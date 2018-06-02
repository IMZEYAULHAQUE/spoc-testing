package com.ilm.lms.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilm.lms.course.model.Course;
import com.ilm.lms.course.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Course> findAllCourse() {
		return courseService.findAllCourse();
	}

	@GetMapping(value = "/{courseId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Course> findCourse(@PathVariable Long courseId) {
		return ResponseEntity.<Course>ok(courseService.findCourse(courseId));
	}
}
