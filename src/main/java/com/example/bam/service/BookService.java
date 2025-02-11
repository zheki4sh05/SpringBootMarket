package com.example.bam.service;

import com.example.bam.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);
    Book findByTitle(String title);
    Optional<Book> findById(Long id);
    Book findByGenre(String genre);
    Book findByAuthor(String author);
    void delete (Long id);
    List<Book> findAll();
}
