package com.spring.mvc.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.mvc.modelos.Persona;


// La interface "PersonaDao" extiende de la interface "CrudRepository"
// CrudRepository es una interface que viene por defecto en "sping" que 
// ya viene con unos cuantos metodos predefinidos de tipo crud (findall, delete ...)

public interface PersonaDao extends CrudRepository<Persona,Long>{
	

}
