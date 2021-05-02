package com.spring.remote.interfaces;

import java.util.List;

import com.spring.remote.model.Persona;

public interface PersonaInterface {
	
	public List<Persona> getPersonas();
	public void updatePersonById(Persona persona, int id);
	public void deletePersonById(int id);
	public void insertPersona(Persona persona);
}
