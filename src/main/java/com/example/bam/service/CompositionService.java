package com.example.bam.service;

import com.example.bam.entity.Composition;
import com.example.bam.entity.User;
import com.example.bam.repository.CompositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompositionService {

    private final CompositionRepository compositionRepository;

    public Composition save(Composition composition) {
        return compositionRepository.save(composition);
    }

    public Composition findByTitle(String title) {
        return compositionRepository.findByTitle(title).orElseThrow();
    }
    public Optional<Composition> findById(Long id) {
        return compositionRepository.findById(id);
    }
    public Composition findByGenre(String genre) {
        return compositionRepository.findByGenre(genre).orElseThrow();
    }
    public Composition findByAuthor(User user) {
        return compositionRepository.findByAuthor(user).orElseThrow();
    }
    public void delete (Long id) {
        compositionRepository.deleteById(id);
    }

    public List<Composition> findAll() {
        return compositionRepository.findAll();
    }
}



