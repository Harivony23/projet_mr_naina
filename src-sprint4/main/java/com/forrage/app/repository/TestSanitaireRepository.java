package com.forrage.app.repository;
import com.forrage.app.model.TestSanitaire;
import com.forrage.app.model.Travaux;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TestSanitaireRepository extends JpaRepository<TestSanitaire, Integer> {
    List<TestSanitaire> findByTravaux(Travaux travaux);
}
