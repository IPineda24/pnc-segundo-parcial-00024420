package domain.dto.request;

import domain.enums.Genre;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateBookRequest {
    @NotBlank(message = "Se necesita el titulo")
    private String title;

    @NotBlank(message = "author requerido")
    private String author;

    @NotBlank(message = "ISBN es requerido")
    private String isbn;

    @NotNull(message = "El genero es requerido")
    private Genre genre;

    @NotNull(message = "El total de copias es requerido")
    @Min(value = 1, message = "El total de copias debe ser mayor o igual a 1")
    private Integer totalCopies;

    @PastOrPresent(message = "error de fecha")
    private LocalDate publishedDate;

    private String description;
}