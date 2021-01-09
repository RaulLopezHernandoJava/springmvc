package com.spring.mvc.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.dao.PersonaDao;
import com.spring.mvc.modelos.Persona;

// Muy importante ponerle la anotacion @Service para que luego el controlador
// de spring pueda hacer referencia a esta

@Service
public class PersonaServiceImpl implements PersonaService {

	// Con el @Autowired conseguimos hacer la inyecccion de la interface
	// "PersonaDao"
	// con ayuda de la factory de Spring que construye bajo demanda

	@Autowired

	private PersonaDao personaDao;

	@Override
	@Transactional(readOnly= true)
	public List<Persona> listarPersonas() {

		return (List<Persona>) personaDao.findAll();
	}

	@Override
	@Transactional
	public void guardar(Persona persona) {
		personaDao.save(persona);

	}

	@Override
	@Transactional
	public void eliminar(Persona persona) {
		personaDao.delete(persona);

	}

	@Override
	@Transactional(readOnly=true)
	public Persona encontrarPersona(Persona persona) {
		// EL "orElse" hace referencia a que so no se encuentra se encuentra
		// el objeto Persona devolvera "null.
		return personaDao.findById(persona.getIdPersona()).orElse(null);
	}
}
