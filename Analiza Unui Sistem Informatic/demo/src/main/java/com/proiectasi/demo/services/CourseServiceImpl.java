package com.proiectasi.demo.services;

import com.proiectasi.demo.entities.Course;
import com.proiectasi.demo.repositories.CourseRepository;
import com.proiectasi.demo.repositories.MaterialRepository;
import com.proiectasi.demo.services.iface.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final MaterialRepository materialRepository;

    @Override
    public Course createCourse(final Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(final String id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public Course updateCourse(final Course course) {
        courseRepository.findById(course.getId()).orElseThrow(() -> new RuntimeException("Course not found"));
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(final String id) {
        courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        final var materials = materialRepository.findByCourseId(id);
        materialRepository.deleteAll(materials);
        courseRepository.deleteById(id);
    }
}
