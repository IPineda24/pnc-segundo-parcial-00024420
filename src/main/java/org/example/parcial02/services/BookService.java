package org.example.parcial02.services;



import org.example.parcial02.domain.dto.request.CreateBookRequest;
import org.example.parcial02.domain.dto.request.UpdateBookRequest;
import org.example.parcial02.domain.dto.response.BookResponse;
import org.example.parcial02.domain.enums.Genre;

import java.util.List;

public interface BookService {
    BookResponse createBook(CreateBookRequest request);
    List<BookResponse> getAllBooks(Genre genre, Boolean available);
    BookResponse getBookById(Long id);
    BookResponse updateBook(Long id, UpdateBookRequest request);
    void deleteBook(Long id);
}