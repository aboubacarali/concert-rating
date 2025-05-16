package fr.rating.concert.service;


import fr.rating.concert.dto.ConcertDto;
import fr.rating.concert.dto.ConcertFormDto;
import fr.rating.concert.entity.Concert;
import fr.rating.concert.repository.ConcertRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ConcertService {

    private ConcertRepository concertRepository;
    private BandService bandService;
    private HallService hallService;


    public List<ConcertDto> getAllConcerts() {
        return concertRepository.findAll().stream()
                .map(this::toDto)
                .sorted((c1, c2) -> c2.getConcertDate().compareTo(c1.getConcertDate())) // Tri par date décroissante
                .collect(Collectors.toList());
    }


    // Récupère un concert par ID sous forme d'entité
    public Concert getConcertById(Long id) {
        return concertRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Concert non trouvé"));
    }


    public ConcertDto addConcert(ConcertFormDto formDto) {
        Concert concert = new Concert();
        concert.setConcertDate(formDto.getConcertDate());
        concert.setBand(bandService.getOrCreateBand(formDto.getBandName()));
        concert.setHall(hallService.getOrCreateHall(formDto.getHallName(), formDto.getCity()));
        Concert savedConcert = concertRepository.save(concert);
        return toDto(savedConcert);
    }


    private ConcertDto toDto(Concert concert) {
        ConcertDto dto = new ConcertDto();
        dto.setId(concert.getId());
        dto.setBandName(concert.getBand().getName());
        dto.setHallName(concert.getHall().getName());
        dto.setConcertDate(concert.getConcertDate());
        return dto;
    }
}
