package com.spring.remote.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.spring.remote.model.Persona;
import com.spring.remote.services.ServicePersona;

@RestController
@Validated
@CrossOrigin("*")
public class ControllerPersona {
	
	@Autowired
	ServicePersona servicePersona;
	
	@GetMapping("/personas")
	public ResponseEntity<List<Persona>> getAllPersonas(){
		List<Persona> personas = servicePersona.getPersonas();
		return new ResponseEntity<>(personas, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/persona/{id}")
	public ResponseEntity<Object> updatePersonById(@RequestBody Persona persona, @PathVariable("id") int id) {
		System.out.println(id + " " + persona.getNombre());
		servicePersona.updatePersonById(persona, id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/persona/{id}")
	public ResponseEntity<Object> deletePersonById(@PathVariable int id){
		servicePersona.deletePersonById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/persona")
	public ResponseEntity<Object> insertPerson(
			@RequestHeader(name = "idPais", required = true) int idPais,
			@Valid @RequestBody(required = true) List<Persona> personas){
		personas.forEach(p -> {
			System.out.println(p.getId());
			System.out.println(p.getNombre());
			System.out.println(p.getApellido());
			p.getListTelefonos().forEach(telefono -> {
				System.out.println("\t " + telefono);
			});
			System.out.println(p.getCiudad());
			System.out.println(p.getDomicilio());
			System.out.println(p.getRfc());
			System.out.println(p.getCurp());
		});			
		return ResponseEntity.status(HttpStatus.CREATED).body("Creado correctamente");
	}
}
