package com.example.bam.mapper;

import com.example.bam.dto.BookDto;
import com.example.bam.entity.Book;

import java.io.IOException;

public interface BookMapper {
    Book bookDtoToBook(BookDto bookDto) throws IOException;
}

