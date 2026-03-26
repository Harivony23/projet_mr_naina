package com.forrage.app.repository;
import com.forrage.app.model.Devis;
import com.forrage.app.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DevisRepository extends JpaRepository<Devis, Integer> {
    List<Devis> findByDemande(Demande demande);
}
