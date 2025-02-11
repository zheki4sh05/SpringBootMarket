package com.example.bam.mapper.impl;

import com.example.bam.dto.BookDto;
import com.example.bam.entity.Book;
import com.example.bam.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BookMapperImpl implements BookMapper {
    public Book bookDtoToBook(BookDto bookDto) throws IOException {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setDescription(bookDto.getDescription());
        book.setContent(bookDto.getContent().getBytes());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        return book;
    }
}
