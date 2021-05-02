package com.spring.remote.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.remote.model.Proceso;
import com.spring.remote.services.ServiceProceso;

@Controller
@CrossOrigin("*")
public class ControllerProceso {
	@Autowired
	ServiceProceso proceso;
	
	@GetMapping("/procesos")
	public ResponseEntity<List<Proceso>> getAllProcesos(){
		List<Proceso> procesos = proceso.getAllProcesos();
		return new ResponseEntity<>(procesos, new HttpHeaders(), HttpStatus.ACCEPTED);		
	}
}
