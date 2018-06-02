package com.ilm.lms.course.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ilm.lms.course.model.ExceptionData;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = CourseNotFoundException.class)
	protected ResponseEntity<Object> handleCourseNotFoundException(CourseNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionData e = new ExceptionData(status.name(), ex.getMessage(), LocalDateTime.now());

		return handleExceptionInternal(ex, e, headers, status, request);

	}

}
