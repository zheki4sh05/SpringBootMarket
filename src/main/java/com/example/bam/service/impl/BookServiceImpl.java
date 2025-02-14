package com.example.bam.service.impl;

import com.example.bam.entity.Book;
import com.example.bam.repository.BookRepository;
import com.example.bam.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        log.info("Save new book {} ", book);
        return bookRepository.save(book);
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    //unused methods
    @Override
    public Book findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    @Override
    public Book findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
        log.info("Delete book with id {} ", id);
    }

    @Override //return Page<Book>
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}
