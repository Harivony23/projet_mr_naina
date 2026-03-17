package com.bacc.correction.controller;

import com.bacc.correction.model.Correcteur;
import com.bacc.correction.repository.CorrecteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/correcteurs")
public class CorrecteurController {

    @Autowired
    private CorrecteurRepository correcteurRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("correcteurs", correcteurRepository.findAll());
        return "correcteur/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("correcteur", new Correcteur());
        return "correcteur/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Correcteur correcteur) {
        correcteurRepository.save(correcteur);
        return "redirect:/correcteurs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("correcteur", correcteurRepository.findById(id).orElseThrow());
        return "correcteur/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        correcteurRepository.deleteById(id);
        return "redirect:/correcteurs";
    }
}
