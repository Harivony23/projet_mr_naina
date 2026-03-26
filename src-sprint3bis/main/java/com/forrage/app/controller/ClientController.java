package com.forrage.app.controller;

import com.forrage.app.model.Client;
import com.forrage.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired private ClientRepository clientRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "client/list";
    }

    @GetMapping("/creer")
    public String createForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/form";
    }

    @PostMapping("/creer")
    public String save(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/clients";
    }
}
