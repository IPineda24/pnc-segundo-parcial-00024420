package repositories;

import domain.entities.Book;
import domain.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByFilters(@Param("genre") Genre genre, @Param("available") Boolean available);
}