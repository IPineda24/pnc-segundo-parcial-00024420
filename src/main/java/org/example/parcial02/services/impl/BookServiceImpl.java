package org.example.parcial02.services.impl;


import lombok.RequiredArgsConstructor;
import org.example.parcial02.common.mappers.BookMapper;
import org.example.parcial02.domain.dto.request.CreateBookRequest;
import org.example.parcial02.domain.dto.request.UpdateBookRequest;
import org.example.parcial02.domain.dto.response.BookResponse;
import org.example.parcial02.domain.entities.Book;
import org.example.parcial02.domain.enums.Genre;
import org.example.parcial02.expcions.BusinessRuleException;
import org.example.parcial02.expcions.ResourceNotFoundException;
import org.example.parcial02.repositories.BookRepository;
import org.example.parcial02.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    public BookResponse createBook(CreateBookRequest request) {
        if (bookRepository.existsByTitleIgnoreCase(request.getTitle())) {
            throw new BusinessRuleException("Ya existe un libro con el título: " + request.getTitle());
        }
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new BusinessRuleException("El ISBN ya esta registrado.");
        }

        Book book = bookMapper.toEntity(request);

        book.setAvailableCopies(request.getTotalCopies());
        book.setAvailable(true);

        return bookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    public List<BookResponse> getAllBooks(Genre genre, Boolean available) {
        return bookRepository.findByFilters(genre, available)
                .stream().map(bookMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con ID: " + id));
        return bookMapper.toResponse(book);
    }

    @Override
    public BookResponse updateBook(Long id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));

        if (!book.getTitle().equalsIgnoreCase(request.getTitle()) &&
                bookRepository.existsByTitleIgnoreCase(request.getTitle())) {
            throw new BusinessRuleException("El nuevo título ya está en uso.");
        }

        int prestados = book.getTotalCopies() - book.getAvailableCopies();
        int nuevasCopiasDisponibles = request.getTotalCopies() - prestados;

        if (nuevasCopiasDisponibles < 0) {
            throw new BusinessRuleException("No puedes reducir el inventario por debajo de la cantidad de libros prestados.");
        }

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setGenre(request.getGenre());
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(nuevasCopiasDisponibles);
        book.setAvailable(nuevasCopiasDisponibles > 0);
        book.setPublishedDate(request.getPublishedDate());
        book.setDescription(request.getDescription());

        return bookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));

        if (book.getAvailableCopies() < book.getTotalCopies()) {
            throw new BusinessRuleException("No se puede eliminar el libro. Existen copias prestadas actualmente.");
        }

        bookRepository.delete(book);
    }
}