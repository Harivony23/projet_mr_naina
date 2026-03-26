package com.forrage.app.controller;

import com.forrage.app.model.*;
import com.forrage.app.repository.*;
import com.forrage.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// import java.math.BigDecimal;
// import java.util.ArrayList;
// import java.util.List;

@Controller
@RequestMapping("/demandes")
public class DemandeController {

    @Autowired private DemandeService demandeService;
    @Autowired private ClientRepository clientRepository;

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
        
        return "demande/details";
    }
    
}
