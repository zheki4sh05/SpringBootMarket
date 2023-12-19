package com.example.bam.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CompositionDto {
    private String title;
    private MultipartFile notes;
    private MultipartFile song;
    private String description;
    private String genre;
}

