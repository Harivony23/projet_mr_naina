package com.forrage.app.repository;
import com.forrage.app.model.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StatutRepository extends JpaRepository<Statut, Integer> {
    Optional<Statut> findByCode(String code);
}
