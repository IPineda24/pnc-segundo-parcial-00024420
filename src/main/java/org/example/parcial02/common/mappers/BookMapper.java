package org.example.parcial02.common.mappers;


import org.example.parcial02.domain.dto.request.CreateBookRequest;
import org.example.parcial02.domain.dto.response.BookResponse;
import org.example.parcial02.domain.entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(CreateBookRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .genre(request.getGenre())
                .totalCopies(request.getTotalCopies())
                .publishedDate(request.getPublishedDate())
                .description(request.getDescription())
                .build();
    }

    public BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .genre(book.getGenre())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .available(book.getAvailable())
                .publishedDate(book.getPublishedDate())
                .description(book.getDescription())
                .build();
    }
}