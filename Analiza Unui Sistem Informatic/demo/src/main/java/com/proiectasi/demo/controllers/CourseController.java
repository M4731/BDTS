package com.proiectasi.demo.controllers;

import com.proiectasi.demo.entities.Course;
import com.proiectasi.demo.services.iface.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@Valid @RequestBody final Course course) {
        return courseService.createCourse(course);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course getCourseById(@PathVariable final String id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Course updateCourse(@RequestBody final Course course) {
        return courseService.updateCourse(course);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCourse(@PathVariable final String id) {
        courseService.deleteCourseById(id);
    }
}