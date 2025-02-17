package com.example.crudusuario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crudusuario.model.Persona;
import com.example.crudusuario.repository.PersonaRepository;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    public Persona obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }

    public void actualizarPersona(Long id, Persona personaActualizada) {
        Persona persona = obtenerPersonaPorId(id);
        persona.setNombre(personaActualizada.getNombre());
        persona.setApellidos(personaActualizada.getApellidos());
        persona.setEmail(personaActualizada.getEmail());
        personaRepository.save(persona);
    }

    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }
    
    public void agregarPersona(Persona persona) {
		if (persona.getId() == null)
			personaRepository.save(persona);
	}
}
