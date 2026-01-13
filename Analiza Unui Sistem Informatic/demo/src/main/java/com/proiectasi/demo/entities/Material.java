package com.proiectasi.demo.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "materials")
@Data
public class Material {

    @Id
    private String id;

    @NotBlank
    private String courseId;

    @NotBlank
    private String name;

    private String fileName;

    private byte[] data;
}
