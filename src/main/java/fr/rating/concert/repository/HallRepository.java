package fr.rating.concert.repository;

import fr.rating.concert.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HallRepository extends JpaRepository<Hall, Long> {
    Optional<Hall> findByNameAndCity(String name, String city);
}