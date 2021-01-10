package com.spring.mvc.web;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.mvc.modelos.Persona;
import com.spring.mvc.servicio.PersonaService;

import lombok.extern.slf4j.Slf4j;

@Controller

@Slf4j
public class ControladorInicio {

	// Con el "Autowired" estamos inyectando la interface "PersonaDao"

	@Autowired
	private PersonaService personaService;

	// Con "@AuthenticationPrincipal User user" guardamos en la variable user el usuario que hizo login
	
	@GetMapping("/")
	public String inicio(Model model,@AuthenticationPrincipal User user) {

		ArrayList<Persona> personas = (ArrayList<Persona>) personaService.listarPersonas();
		System.out.println("Este es el usuario que hizo login   " + user);

		model.addAttribute("personas", personas);

		return "index";
	}

	@GetMapping("/agregar")
	public String agregar(Persona persona) {
		return "modificar";

	}

	 @PostMapping("/guardar")
	    public String guardar(@Valid Persona persona, Errors errores){
	        if(errores.hasErrors()){
	            return "modificar";
	        }
	        personaService.guardar(persona);
	        return "redirect:/";
	    }
	    

	@GetMapping("/editar/{idPersona}")
	public String editar(Persona persona, Model model) {
		persona = personaService.encontrarPersona(persona);
		model.addAttribute("persona", persona);
		return "modificar";
	}

	@GetMapping("/eliminar")
	public String eliminar(Persona persona) {
		personaService.eliminar(persona);
		return "redirect:/";
	}

}