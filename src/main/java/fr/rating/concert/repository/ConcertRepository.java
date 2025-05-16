package fr.rating.concert.repository;

import fr.rating.concert.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
