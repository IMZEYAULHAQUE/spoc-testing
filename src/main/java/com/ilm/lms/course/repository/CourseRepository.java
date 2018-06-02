package com.ilm.lms.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilm.lms.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
