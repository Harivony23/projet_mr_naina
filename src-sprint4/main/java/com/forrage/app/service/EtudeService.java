package com.forrage.app.service;

import com.forrage.app.model.*;
import com.forrage.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EtudeService {

    @Autowired private EtudeRepository etudeRepository;
    @Autowired private DemandeService demandeService;

    @Transactional
    public Etude enregistrerResultatEtude(Demande demande, Devis devisEtude, String result, String comment) {
        Etude e = new Etude();
        e.setDemande(demande);
        e.setDevis(devisEtude);
        e.setDateEtude(LocalDate.now());
        e.setResultat(result);
        e.setCommentaire(comment);

        Etude saved = etudeRepository.save(e);

        if ("faisable".equals(result)) {
            demandeService.changerStatut(demande, "etude_faisable");
        } else {
            demandeService.changerStatut(demande, "etude_non_faisable");
        }

        return saved;
    }
}
