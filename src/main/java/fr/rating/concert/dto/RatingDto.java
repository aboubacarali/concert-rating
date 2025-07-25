package fr.rating.concert.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RatingDto {
    private Long id;
    private int score;
    private String comment;
    private String username;
    private LocalDateTime ratingDate;
}
