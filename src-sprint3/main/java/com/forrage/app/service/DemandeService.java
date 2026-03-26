package com.forrage.app.service;

import com.forrage.app.model.*;
import com.forrage.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DemandeService {

    @Autowired private DemandeRepository demandeRepository;
    @Autowired private StatutRepository statutRepository;

    @Transactional
    public Demande changerStatut(Demande demande, String codeStatut) {
        Statut s = statutRepository.findByCode(codeStatut).orElseThrow();
        demande.setStatut(s);
        Demande saved = demandeRepository.save(demande);

        return saved;
    }

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    public Demande getDemandeById(Integer id) {
        return demandeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Demande creerDemande(Demande demande) {
        demande.setDateDemande(LocalDate.now());
        return changerStatut(demande, "en_attente");
    }

}
