package com.example.crudusuario.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crudusuario.model.Persona;
import com.example.crudusuario.service.PersonaService;



@Controller
@RequestMapping("/admin/personas")
public class PersonaAdminController {
    private final PersonaService personaService;

    public PersonaAdminController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("persona", new Persona());
        return "persona/crear";
    }

    @PostMapping
    public String guardar(@ModelAttribute Persona persona) {
        personaService.agregarPersona(persona);
        return "redirect:/user/personas";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Persona persona = personaService.obtenerPersonaPorId(id);
        model.addAttribute("persona", persona);
        return "persona/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Persona persona) {
        personaService.actualizarPersona(id, persona);
        return "redirect:/user/personas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return "redirect:/user/personas";
    }
}
