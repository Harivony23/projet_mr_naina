package com.bacc.correction.repository;

import com.bacc.correction.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NoteFinalRepository extends JpaRepository<NoteFinal, Long> {
    Optional<NoteFinal> findByCandidatIdAndMatiereId(Long candidatId, Long matiereId);
}
