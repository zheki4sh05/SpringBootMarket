package com.example.bam.mapper;

import com.example.bam.dto.CompositionDto;
import com.example.bam.entity.Composition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CompositionMapper {

    public Composition composDtoToComposition(CompositionDto compositionDto) throws IOException {
        Composition composition = new Composition();
        composition.setTitle(compositionDto.getTitle());
        composition.setNotes(compositionDto.getNotes().getBytes());
        composition.setSong(compositionDto.getSong().getBytes());
        composition.setDescription(compositionDto.getDescription());
        return composition;
    }
}
