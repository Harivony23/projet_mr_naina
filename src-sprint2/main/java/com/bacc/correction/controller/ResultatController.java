package com.bacc.correction.controller;

import com.bacc.correction.model.NoteFinal;
import com.bacc.correction.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ResultatController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/resultats")
    public String listResultats(Model model) {
        List<NoteFinal> resultats = noteService.getAllFinalNotes();
        model.addAttribute("resultats", resultats);
        return "resultat/list";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/resultats";
    }
}
