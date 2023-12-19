package com.example.bam.repository;

import com.example.bam.entity.Composition;
import com.example.bam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompositionRepository extends JpaRepository<Composition, Long> {
    Optional<Composition> findByTitle(String title);
    Optional<Composition> findByGenre(String genre);
    Optional<Composition> findByAuthor(User author);
    Optional<Composition> findById(Long id);
    List<Composition> findAll();

}
