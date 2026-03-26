package com.forrage.app.repository;
import com.forrage.app.model.Etude;
import com.forrage.app.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EtudeRepository extends JpaRepository<Etude, Integer> {
    Optional<Etude> findByDemande(Demande demande);
}
