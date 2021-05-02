package com.spring.remote.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.remote.dao.PersonaDao;
import com.spring.remote.interfaces.PersonaInterface;
import com.spring.remote.model.Persona;

@Service
public class ServicePersona implements PersonaInterface{
	@Autowired
	PersonaDao personaDto;
	
	@Override
	public List<Persona> getPersonas() {
		
		return personaDto.getAllPersonas();
	}

	@Override
	public void updatePersonById(Persona persona, int id) {
		personaDto.updatePersonById(persona, id);
		
	}

	@Override
	public void deletePersonById(int id) {
		personaDto.deletePersonById(id);
		
	}

	@Override
	public void insertPersona(Persona persona) {
		personaDto.insertPerson(persona);
		
	}

}
