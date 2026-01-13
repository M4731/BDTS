package com.proiectasi.demo.repositories;

import com.proiectasi.demo.entities.Material;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MaterialRepository extends MongoRepository<Material, String> {
    List<Material> findByCourseId(String courseId);
}
