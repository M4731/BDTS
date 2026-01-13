package com.proiectasi.demo.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courses")
@Data
public class Course {

    @Id
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private LocalDateTime creationDate;
}

