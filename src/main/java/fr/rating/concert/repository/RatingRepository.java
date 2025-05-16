package fr.rating.concert.repository;

import fr.rating.concert.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByConcertId(Long concertId);
}