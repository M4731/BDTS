package com.proiectasi.demo.controllers;

import com.proiectasi.demo.entities.Material;
import com.proiectasi.demo.services.iface.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/materials")
@RequiredArgsConstructor
@CrossOrigin
public class MaterialController {
    private final MaterialService materialService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Material createMaterial(@Valid @RequestBody final Material material) {
        return materialService.createMaterial(material);
    }

    @GetMapping("getByCourse/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Material> findMaterialsByCourseId(@PathVariable final String courseId) {
        return materialService.findMaterialsByCourseId(courseId);
    }

    @GetMapping("{materialId}")
    @ResponseStatus(HttpStatus.OK)
    public Material findMaterialById(@PathVariable final String materialId) {
        return materialService.findMaterialById(materialId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Material updateMaterial(@Valid @RequestBody final Material material) {
        return materialService.updateMaterial(material);
    }

    @DeleteMapping("{materialId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteMaterialById(@PathVariable final String materialId) {
        materialService.deleteMaterialById(materialId);
    }
}
