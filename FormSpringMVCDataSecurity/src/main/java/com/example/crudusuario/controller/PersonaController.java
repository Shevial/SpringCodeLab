package com.example.crudusuario.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crudusuario.service.PersonaService;


@Controller
@RequestMapping("/user/personas")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("personas", personaService.listarPersonas());
        return "persona/index";
    }
}