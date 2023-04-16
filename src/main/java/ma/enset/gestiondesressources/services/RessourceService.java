package ma.enset.gestiondesressources.services;


import ma.enset.gestiondesressources.entities.Ressource;
import ma.enset.gestiondesressources.repositories.RessourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RessourceService {
    List<Ressource> findAllRessources();

    Ressource findRessouceById(Long id);

    Ressource saveRessource(Ressource ressource);

    void deleteRessourceById(Long id);

    Page<Ressource> searchRessourcesByKeyword(String keyword, int page, int size);
}
