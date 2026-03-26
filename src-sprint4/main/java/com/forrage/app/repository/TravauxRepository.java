package com.forrage.app.repository;
import com.forrage.app.model.Travaux;
import com.forrage.app.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface TravauxRepository extends JpaRepository<Travaux, Integer> {
    Optional<Travaux> findByDemande(Demande demande);
}
