package com.ilm.lms.course.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ilm.lms.course.exception.CourseNotFoundException;
import com.ilm.lms.course.model.Course;
import com.ilm.lms.course.repository.CourseRepository;

@Service
@Transactional
public class CourseService {

	@Autowired
	private CourseRepository repository;
	
	public List<Course> findAllCourse() {
		return repository.findAll();
	}

	public Course findCourse(@PathVariable Long courseId) {
		return repository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course with id [" + courseId + "] not found."));
	}

}
