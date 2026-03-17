package com.bacc.correction.controller;

import com.bacc.correction.model.Matiere;
import com.bacc.correction.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matieres")
public class MatiereController {

    @Autowired
    private MatiereRepository matiereRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("matieres", matiereRepository.findAll());
        return "matiere/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("matiere", new Matiere());
        return "matiere/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Matiere matiere) {
        matiereRepository.save(matiere);
        return "redirect:/matieres";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("matiere", matiereRepository.findById(id).orElseThrow());
        return "matiere/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        matiereRepository.deleteById(id);
        return "redirect:/matieres";
    }
}
