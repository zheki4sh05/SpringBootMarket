package com.example.bam.controller;

import com.example.bam.dto.BookDto;
import com.example.bam.entity.Book;
import com.example.bam.exception.ContentTypeException;
import com.example.bam.mapper.BookMapper;
import com.example.bam.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @SneakyThrows
    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestPart String title,
                                           @RequestPart String description,
                                           @RequestPart String genre,
                                           @RequestPart String author,
                                           @RequestPart double price,
                                           @RequestPart("content") MultipartFile content) {

        BookDto bookDto = new BookDto();
        bookDto.setTitle(title);
        bookDto.setDescription(description);
        bookDto.setGenre(genre);
        bookDto.setAuthor(author);
        bookDto.setPrice(price);
        bookDto.setContent(content);

        Book book = bookMapper.bookDtoToBook(bookDto);
        Book newBook = bookService.save(book);
        return ResponseEntity.ok(newBook);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Book>> findAll() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<Book> findBookByTitle(@PathVariable String title) {
        Book book = bookService.findByTitle(title);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/findByAuthor/{author}")
    public ResponseEntity<Book> findBookByAuthor(@PathVariable String author) {
        Book book = bookService.findByTitle(author);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/findByGenre/{genre}")
    public ResponseEntity<Book> findBookByGenre(@PathVariable String genre) {
        Book book = bookService.findByTitle(genre);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Book>> findBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/updateById/{id}")
    public Book updateBook(@RequestBody BookDto bookDto, @PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> {
                    book.setTitle(bookDto.getTitle());
                    book.setDescription(bookDto.getDescription());
                    book.setGenre(bookDto.getGenre());
                    book.setAuthor(bookDto.getAuthor());
                    book.setPrice(bookDto.getPrice());
                    try {
                        book.setContent(bookDto.getContent().getBytes());
                    } catch (IOException e) {
                        throw new ContentTypeException("Invalid content format!", e);
                    }
                    return bookService.save(book);
                        }
                ).orElseThrow(() -> new ResourceNotFoundException("Book with ID " + id + " not found!"));
    }

    @DeleteMapping("/deleteBookById/{id}")
    public HttpStatus delete (@PathVariable Long id) {
        bookService.delete(id);
        return HttpStatus.OK;
    }
}
