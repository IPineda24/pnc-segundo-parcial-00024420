package org.example.parcial02.domain.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.parcial02.domain.enums.Genre;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Genre genre;
    private Integer totalCopies;
    private Integer availableCopies;
    private Boolean available;
    private LocalDate publishedDate;
    private String description;
}