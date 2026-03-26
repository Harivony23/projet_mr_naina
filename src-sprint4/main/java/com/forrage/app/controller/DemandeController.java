package com.forrage.app.controller;

import com.forrage.app.model.*;
import com.forrage.app.repository.*;
import com.forrage.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/demandes")
public class DemandeController {

    @Autowired private DemandeService demandeService;
    @Autowired private DevisService devisService;
    @Autowired private EtudeService etudeService;
    @Autowired private TravauxService travauxService;
    @Autowired private ClientRepository clientRepository;
    @Autowired private DevisRepository devisRepository;
    @Autowired private EtudeRepository etudeRepository;
    @Autowired private TravauxRepository travauxRepository;
    @Autowired private TestSanitaireRepository testSanitaireRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("demandes", demandeService.getAllDemandes());
        return "demande/list";
    }

    @GetMapping("/creer")
    public String createForm(Model model) {
        model.addAttribute("demande", new Demande());
        model.addAttribute("clients", clientRepository.findAll());
        return "demande/form";
    }

    @PostMapping("/creer")
    public String save(@ModelAttribute Demande demande) {
        demandeService.creerDemande(demande);
        return "redirect:/demandes";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Integer id, Model model) {
        Demande demande = demandeService.getDemandeById(id);
        model.addAttribute("demande", demande);
        model.addAttribute("devisList", devisRepository.findByDemande(demande));
        
        Etude etude = etudeRepository.findByDemande(demande).orElse(null);
        model.addAttribute("etude", etude);
        
        Travaux travaux = travauxRepository.findByDemande(demande).orElse(null);
        model.addAttribute("travaux", travaux);
        
        if (travaux != null) {
            model.addAttribute("tests", testSanitaireRepository.findByTravaux(travaux));
        }
        
        return "demande/details";
    }

    @PostMapping("/{id}/devis")
    public String creerDevis(@PathVariable Integer id, @RequestParam String type, @RequestParam String libelle, @RequestParam BigDecimal montant) {
        Demande demande = demandeService.getDemandeById(id);
        List<DetailsDevis> details = new ArrayList<>();
        DetailsDevis d = new DetailsDevis();
        d.setLibelle(libelle);
        d.setMontant(montant);
        details.add(d);
        devisService.creerDevis(demande, type, details);
        return "redirect:/demandes/" + id;
    }

    @PostMapping("/devis/{id}/accepter")
    public String accepterDevis(@PathVariable Integer id, @RequestParam Integer demandeId) {
        demandeService.accepterDevis(id);
        return "redirect:/demandes/" + demandeId;
    }

    @PostMapping("/devis/{id}/refuser")
    public String refuserDevis(@PathVariable Integer id, @RequestParam Integer demandeId) {
        demandeService.refuserDevis(id);
        return "redirect:/demandes/" + demandeId;
    }

    @PostMapping("/{id}/etude")
    public String enregistrerEtude(@PathVariable Integer id, @RequestParam Integer devisId, @RequestParam String resultat, @RequestParam String commentaire) {
        Demande demande = demandeService.getDemandeById(id);
        Devis devis = devisRepository.findById(devisId).orElseThrow();
        etudeService.enregistrerResultatEtude(demande, devis, resultat, commentaire);
        return "redirect:/demandes/" + id;
    }

    @PostMapping("/{id}/travaux/demarrer")
    public String demarrerTravaux(@PathVariable Integer id, @RequestParam Integer devisId) {
        Demande demande = demandeService.getDemandeById(id);
        Devis devis = devisRepository.findById(devisId).orElseThrow();
        travauxService.demarrerTravaux(demande, devis);
        return "redirect:/demandes/" + id;
    }

    @PostMapping("/travaux/{id}/eau")
    public String arriveeEau(@PathVariable Integer id, @RequestParam Integer demandeId) {
        travauxService.enregistrerArriveeEau(id);
        return "redirect:/demandes/" + demandeId;
    }

    @PostMapping("/travaux/{id}/terminer")
    public String terminerTravaux(@PathVariable Integer id, @RequestParam Integer demandeId) {
        travauxService.terminerTravaux(id);
        return "redirect:/demandes/" + demandeId;
    }

    @PostMapping("/travaux/{id}/test")
    public String testSanitaire(@PathVariable Integer id, @RequestParam Integer demandeId, @RequestParam String resultat, @RequestParam String commentaire) {
        travauxService.enregistrerTestSanitaire(id, resultat, commentaire);
        return "redirect:/demandes/" + demandeId;
    }
}
