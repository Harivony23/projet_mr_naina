package com.forrage.app.service;

import com.forrage.app.model.*;
import com.forrage.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class TravauxService {

    @Autowired private TravauxRepository travauxRepository;
    @Autowired private TestSanitaireRepository testSanitaireRepository;
    @Autowired private DemandeService demandeService;

    @Transactional
    public Travaux demarrerTravaux(Demande demande, Devis devisForage) {
        Travaux t = new Travaux();
        t.setDemande(demande);
        t.setDevis(devisForage);
        t.setDateDebut(LocalDate.now());

        demandeService.changerStatut(demande, "travaux_en_cours");
        return travauxRepository.save(t);
    }

    @Transactional
    public void enregistrerArriveeEau(Integer idTravaux) {
        Travaux t = travauxRepository.findById(idTravaux).orElseThrow();
        t.setDateArriveeEau(LocalDate.now());
        travauxRepository.save(t);
    }

    @Transactional
    public void terminerTravaux(Integer idTravaux) {
        Travaux t = travauxRepository.findById(idTravaux).orElseThrow();
        t.setDateFin(LocalDate.now());
        travauxRepository.save(t);
        demandeService.changerStatut(t.getDemande(), "termine");
    }

    @Transactional
    public void enregistrerTestSanitaire(Integer idTravaux, String result, String comment) {
        Travaux t = travauxRepository.findById(idTravaux).orElseThrow();
        TestSanitaire test = new TestSanitaire();
        test.setTravaux(t);
        test.setDateTest(LocalDate.now());
        test.setResultat(result);
        test.setCommentaire(comment);
        testSanitaireRepository.save(test);
    }
}
