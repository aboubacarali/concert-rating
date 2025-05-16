package fr.rating.concert.service;


import fr.rating.concert.entity.Band;
import fr.rating.concert.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BandService {
    @Autowired
    private BandRepository bandRepository;


    public Band getOrCreateBand(String name) {
        return bandRepository.findByName(name)
                .orElseGet(() -> {
                    Band band = new Band();
                    band.setName(name);
                    band.setKind("Unknown"); // Simplification
                    return bandRepository.save(band);
                });
    }
}
