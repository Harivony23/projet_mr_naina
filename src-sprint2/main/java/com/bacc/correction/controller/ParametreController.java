package com.bacc.correction.controller;

import com.bacc.correction.model.Parametre;
import com.bacc.correction.repository.MatiereRepository;
import com.bacc.correction.repository.OperateurRepository;
import com.bacc.correction.repository.ParametreRepository;
import com.bacc.correction.repository.ResolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parametres")
public class ParametreController {

    @Autowired private ParametreRepository parametreRepository;
    @Autowired private MatiereRepository matiereRepository;
    @Autowired private OperateurRepository operateurRepository;
    @Autowired private ResolutionRepository resolutionRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("parametres", parametreRepository.findAll());
        return "parametre/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("parametre", new Parametre());
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("operateurs", operateurRepository.findAll());
        model.addAttribute("resolutions", resolutionRepository.findAll());
        return "parametre/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Parametre parametre) {
        parametreRepository.save(parametre);
        return "redirect:/parametres";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("parametre", parametreRepository.findById(id).orElseThrow());
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("operateurs", operateurRepository.findAll());
        model.addAttribute("resolutions", resolutionRepository.findAll());
        return "parametre/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        parametreRepository.deleteById(id);
        return "redirect:/parametres";
    }
}
