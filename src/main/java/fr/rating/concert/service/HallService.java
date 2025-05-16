package fr.rating.concert.service;


import fr.rating.concert.entity.Hall;
import fr.rating.concert.repository.HallRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HallService {

    // Caution: this is autogenarated by the AllArgsConstructor annotation using lombok
    private HallRepository hallRepository;


    public Hall getOrCreateHall(String name, String city) {
        return hallRepository.findByNameAndCity(name, city)
                .orElseGet(() -> {
                    Hall hall = new Hall();
                    hall.setName(name);
                    hall.setCity(city);
                    return hallRepository.save(hall);
                });
    }
}
