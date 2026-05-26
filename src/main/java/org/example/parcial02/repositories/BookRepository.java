package org.example.parcial02.repositories;




import org.example.parcial02.domain.entities.Book;
import org.example.parcial02.domain.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitleIgnoreCase(String title);

    boolean existsByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE " +
            "(:genre IS NULL OR b.genre = :genre) AND " +
            "(:available IS NULL OR b.available = :available)")
    List<Book> findByFilters(@Param("genre") Genre genre, @Param("available") Boolean available);

}