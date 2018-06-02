package com.ilm.lms.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6000559675356496621L;

	public CourseNotFoundException() {
		super();
	}

	public CourseNotFoundException(String message) {
		super(message);
	}
}
