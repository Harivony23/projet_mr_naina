package com.forrage.app.service;

import com.forrage.app.model.*;
import com.forrage.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DevisService {

    @Autowired private DevisRepository devisRepository;
    @Autowired private TypeDevisRepository typeDevisRepository;
    @Autowired private StatutRepository statutRepository;
    @Autowired private DemandeService demandeService;

    @Transactional
    public Devis creerDevis(Demande demande, String typeLibelle, List<DetailsDevis> details) {
        TypeDevis t = typeDevisRepository.findByLibelle(typeLibelle).orElseThrow();
        Statut enAttente = statutRepository.findByCode("devis_en_attente").orElseThrow();

        Devis d = new Devis();
        d.setDemande(demande);
        d.setTypeDevis(t);
        d.setDateDevis(LocalDate.now());
        d.setStatut(enAttente);

        BigDecimal total = BigDecimal.ZERO;
        for (DetailsDevis detail : details) {
            detail.setDevis(d);
            total = total.add(detail.getMontant());
        }
        d.setMontantTotal(total);
        d.setDetails(details);

        Devis saved = devisRepository.save(d);

        // Update demande status
        if ("etude".equals(typeLibelle)) {
            demandeService.changerStatut(demande, "devis_etude_envoye");
        } else {
            demandeService.changerStatut(demande, "devis_forage_envoye");
        }

        return saved;
    }
}
