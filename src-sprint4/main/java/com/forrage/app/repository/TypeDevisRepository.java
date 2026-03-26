package com.forrage.app.repository;
import com.forrage.app.model.TypeDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface TypeDevisRepository extends JpaRepository<TypeDevis, Integer> {
    Optional<TypeDevis> findByLibelle(String libelle);
}
