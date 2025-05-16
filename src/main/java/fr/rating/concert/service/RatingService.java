package fr.rating.concert.service;


import fr.rating.concert.dto.RatingDto;
import fr.rating.concert.dto.RatingFormDto;
import fr.rating.concert.entity.Rating;
import fr.rating.concert.repository.RatingRepository;
import fr.rating.concert.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RatingService {
    private RatingRepository ratingRepository;
    private UserRepository userRepository;
    private ConcertService concertService;

    public List<RatingDto> getRatingsByConcertId(Long concertId) {
        return ratingRepository.findByConcertId(concertId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public RatingDto addRating(RatingFormDto formDto, Long concertId, Long userId) {
        Rating rating = new Rating();
        rating.setScore(formDto.getScore());
        rating.setComment(formDto.getComment());
        rating.setRatingDate(LocalDateTime.now());
        rating.setConcert(concertService.getConcertById(concertId));
        rating.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé")));
        Rating savedRating = ratingRepository.save(rating);
        return toDto(savedRating);
    }

    private RatingDto toDto(Rating rating) {
        RatingDto dto = new RatingDto();
        dto.setId(rating.getId());
        dto.setScore(rating.getScore());
        dto.setComment(rating.getComment());
        dto.setUsername(rating.getUser().getUsername());
        dto.setRatingDate(rating.getRatingDate());
        return dto;
    }
}
