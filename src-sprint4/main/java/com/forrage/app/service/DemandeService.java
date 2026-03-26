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
    @Autowired private HistoriqueStatutRepository historiqueStatutRepository;
    @Autowired private DevisRepository devisRepository;

    @Transactional
    public Demande changerStatut(Demande demande, String codeStatut) {
        Statut s = statutRepository.findByCode(codeStatut).orElseThrow();
        demande.setStatut(s);
        Demande saved = demandeRepository.save(demande);

        HistoriqueStatut h = new HistoriqueStatut();
        h.setDemande(saved);
        h.setStatut(s);
        h.setDateChangement(LocalDateTime.now());
        historiqueStatutRepository.save(h);

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

    @Transactional
    public void accepterDevis(Integer idDevis) {
        Devis devis = devisRepository.findById(idDevis).orElseThrow();
        Statut accepte = statutRepository.findByCode("accepte").orElseThrow();
        devis.setStatut(accepte);
        devisRepository.save(devis);

        if ("etude".equals(devis.getTypeDevis().getLibelle())) {
            changerStatut(devis.getDemande(), "etude_en_cours");
        } else {
            changerStatut(devis.getDemande(), "travaux_en_cours");
        }
    }

    @Transactional
    public void refuserDevis(Integer idDevis) {
        Devis devis = devisRepository.findById(idDevis).orElseThrow();
        Statut refuse = statutRepository.findByCode("refuse").orElseThrow();
        devis.setStatut(refuse);
        devisRepository.save(devis);

        // Si le devis est refusé, on pourrait revenir à un certain statut ou arrêter
        // Pour l'instant on garde le statut actuel de la demande ou on définit un statut d'arrêt
    }
}
