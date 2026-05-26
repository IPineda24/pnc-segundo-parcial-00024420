package org.example.parcial02.repositories;

import jakarta.validation.constraints.NotBlank;
import org.example.parcial02.domain.entities.Book;
import org.example.parcial02.domain.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByFilters(@Param("genre") Genre genre, @Param("available") Boolean available);

    boolean existsByIsbn(@NotBlank(message = "ISBN es requerido") String isbn);

    boolean existsByTitleIgnoreCase(@NotBlank(message = "Se necesita el titulo") String title);
}