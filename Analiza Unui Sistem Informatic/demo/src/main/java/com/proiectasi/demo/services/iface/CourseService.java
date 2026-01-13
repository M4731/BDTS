package com.proiectasi.demo.services.iface;

import com.proiectasi.demo.entities.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(final Course course);

    List<Course> getAllCourses();

    Course getCourseById(final String id);

    Course updateCourse(final Course course);

    void deleteCourseById(final String id);

}
