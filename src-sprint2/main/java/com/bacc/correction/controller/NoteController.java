package com.bacc.correction.controller;

import com.bacc.correction.model.Note;
import com.bacc.correction.repository.CandidatRepository;
import com.bacc.correction.repository.CorrecteurRepository;
import com.bacc.correction.repository.MatiereRepository;
import com.bacc.correction.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired private NoteRepository noteRepository;
    @Autowired private CandidatRepository candidatRepository;
    @Autowired private MatiereRepository matiereRepository;
    @Autowired private CorrecteurRepository correcteurRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("notes", noteRepository.findAll());
        return "note/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("candidats", candidatRepository.findAll());
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("correcteurs", correcteurRepository.findAll());
        return "note/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Note note) {
        noteRepository.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("note", noteRepository.findById(id).orElseThrow());
        model.addAttribute("candidats", candidatRepository.findAll());
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("correcteurs", correcteurRepository.findAll());
        return "note/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return "redirect:/notes";
    }
}
