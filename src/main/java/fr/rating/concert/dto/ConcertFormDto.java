package fr.rating.concert.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConcertFormDto {
    @NotBlank(message = "Le nom du groupe est requis")
    private String bandName;

    @NotBlank(message = "Le lieu est requis")
    private String hallName;

    @NotBlank(message = "La ville est requise")
    private String city;

    @NotNull(message = "La date est requise")
    private LocalDate concertDate;
}
