package fr.rating.concert.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConcertDto {
    private Long id;
    private String bandName;
    private String hallName;
    private LocalDate concertDate;
}
