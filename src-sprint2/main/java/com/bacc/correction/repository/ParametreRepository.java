package com.bacc.correction.repository;

import com.bacc.correction.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParametreRepository extends JpaRepository<Parametre, Long> {
    List<Parametre> findByMatiereId(Long matiereId);
}
