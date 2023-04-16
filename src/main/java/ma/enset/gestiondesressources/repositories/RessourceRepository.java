package ma.enset.gestiondesressources.repositories;

import ma.enset.gestiondesressources.entities.Ressource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RessourceRepository extends JpaRepository<Ressource,Long> {
    Page<Ressource> findByTitreContains(String keyword, PageRequest of);
}
