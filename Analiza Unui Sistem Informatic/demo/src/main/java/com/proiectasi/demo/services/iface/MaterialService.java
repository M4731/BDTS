package com.proiectasi.demo.services.iface;

import com.proiectasi.demo.entities.Material;

import java.util.List;

public interface MaterialService {
    Material createMaterial(final Material material);

    List<Material> findMaterialsByCourseId(final String courseId);

    Material findMaterialById(final String id);

    Material updateMaterial(final Material material);

    void deleteMaterialById(final String id);
}
