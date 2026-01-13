package com.proiectasi.demo.services;

import com.proiectasi.demo.entities.Material;
import com.proiectasi.demo.repositories.CourseRepository;
import com.proiectasi.demo.repositories.MaterialRepository;
import com.proiectasi.demo.services.iface.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final CourseRepository courseRepository;

    @Override
    public Material createMaterial(final Material material){
        courseRepository.findById(material.getCourseId()).orElseThrow(()->new RuntimeException("Course not found"));
        return materialRepository.save(material);
    }

    @Override
    public List<Material> findMaterialsByCourseId(String courseId) {
        courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Course not found"));
        return materialRepository.findByCourseId(courseId);
    }

    @Override
    public Material findMaterialById(String id) {
        return materialRepository.findById(id).orElseThrow(()->new RuntimeException("Material not found"));
    }

    @Override
    public Material updateMaterial(Material material) {
        materialRepository.findById(material.getId()).orElseThrow(()->new RuntimeException("Material not found"));
        return materialRepository.save(material);
    }

    @Override
    public void deleteMaterialById(String id) {
        materialRepository.findById(id).orElseThrow(()->new RuntimeException("Material not found"));
        materialRepository.deleteById(id);
    }
}
