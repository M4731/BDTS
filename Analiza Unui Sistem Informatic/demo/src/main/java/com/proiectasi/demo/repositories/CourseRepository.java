package com.proiectasi.demo.repositories;

import com.proiectasi.demo.entities.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> { }
