package com.forrage.app.service;

import com.forrage.app.model.Statut;
import com.forrage.app.model.TypeDevis;
import com.forrage.app.repository.StatutRepository;
import com.forrage.app.repository.TypeDevisRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeedService {

    @Autowired private StatutRepository statutRepository;
    @Autowired private TypeDevisRepository typeDevisRepository;

    @PostConstruct
    public void seed() {
        if (statutRepository.count() == 0) {
            saveStatut("en_attente", "En attente", "demande");
            saveStatut("devis_etude_envoye", "Devis étude envoyé", "demande");
            saveStatut("etude_en_cours", "Étude en cours", "demande");
            saveStatut("etude_faisable", "Étude faisable", "demande");
            saveStatut("etude_non_faisable", "Étude non faisable", "demande");
            saveStatut("devis_forage_envoye", "Devis forage envoyé", "demande");
            saveStatut("travaux_en_cours", "Travaux en cours", "demande");
            saveStatut("termine", "Terminé", "demande");

            saveStatut("devis_en_attente", "En attente", "devis");
            saveStatut("accepte", "Accepté", "devis");
            saveStatut("refuse", "Refusé", "devis");
        }

        if (typeDevisRepository.count() == 0) {
            saveType("etude");
            saveType("forage");
        }
    }

    private void saveStatut(String code, String libelle, String cat) {
        Statut s = new Statut();
        s.setCode(code);
        s.setLibelle(libelle);
        s.setCategorie(cat);
        statutRepository.save(s);
    }

    private void saveType(String libelle) {
        TypeDevis t = new TypeDevis();
        t.setLibelle(libelle);
        typeDevisRepository.save(t);
    }
}
