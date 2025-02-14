package com.example.bam.repository;

import com.example.bam.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
    Book findByGenre(String genre);
    Book findByAuthor(String author);

    //unused method
    Optional<Book> findBookById(Long id);
    void deleteById(Long id);
}
