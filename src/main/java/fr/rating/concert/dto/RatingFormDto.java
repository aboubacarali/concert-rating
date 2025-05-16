package fr.rating.concert.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RatingFormDto {
    @NotNull(message = "Le score est requis")
    @Min(value = 1, message = "Le score doit être au moins 1")
    @Max(value = 5, message = "Le score ne peut pas dépasser 5")
    private Integer score;

    private String comment;
}
