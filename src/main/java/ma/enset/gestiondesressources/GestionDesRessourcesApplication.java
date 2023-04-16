package ma.enset.gestiondesressources;

import ma.enset.gestiondesressources.entities.Ressource;
import ma.enset.gestiondesressources.repositories.RessourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class GestionDesRessourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionDesRessourcesApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RessourceRepository ressourceRepository) {
        return args -> {
            for (int i=0; i<10; i++){
                Ressource r=new Ressource();
                r.setTitre("titre"+i);
                r.setDateAchat(new Date());
                r.setPrix(1000+i);
                r.setNote(i+9);
                ressourceRepository.save(r);
            }
            ressourceRepository.findAll().forEach(p -> {
                System.out.println(p.getId() + " " + p.getTitre());
            });
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
