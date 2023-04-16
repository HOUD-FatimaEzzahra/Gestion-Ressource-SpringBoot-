package ma.enset.gestiondesressources.services;

import ma.enset.gestiondesressources.entities.Ressource;
import ma.enset.gestiondesressources.repositories.RessourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RessouceServiceImpl implements RessourceService{

    private RessourceRepository ressourceRepository;

    public RessouceServiceImpl(RessourceRepository ressourceRepository) {
        this.ressourceRepository = ressourceRepository;
    }
    @Override
    public List<Ressource> findAllRessources() {
        return ressourceRepository.findAll();
    }

    @Override
    public Ressource findRessouceById(Long id) {
        return ressourceRepository.findById(id).orElse(null);
    }

    @Override
    public Ressource saveRessource(Ressource ressource) {
        return ressourceRepository.save(ressource);
    }

    @Override
    public void deleteRessourceById(Long id) {
        ressourceRepository.deleteById(id);
    }

    @Override
    public Page<Ressource> searchRessourcesByKeyword(String keyword, int page, int size) {
        return ressourceRepository.findByTitreContains(keyword, PageRequest.of(page, size));
    }
}
