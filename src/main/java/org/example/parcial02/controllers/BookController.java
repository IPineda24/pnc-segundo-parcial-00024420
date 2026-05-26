package org.example.parcial02.controllers;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.parcial02.domain.dto.request.CreateBookRequest;
import org.example.parcial02.domain.dto.request.UpdateBookRequest;
import org.example.parcial02.domain.dto.response.BookResponse;
import org.example.parcial02.domain.enums.Genre;
import org.example.parcial02.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid CreateBookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(request));
    }

    // Los @RequestParam(required = false) permiten que los filtros sean opcionales
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks(
            @RequestParam(required = false) Genre genre,
            @RequestParam(required = false) Boolean available) {
        return ResponseEntity.ok(bookService.getAllBooks(genre, available));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Long id,
            @RequestBody @Valid UpdateBookRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Código 204
    }
}