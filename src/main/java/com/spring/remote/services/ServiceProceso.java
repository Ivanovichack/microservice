package com.spring.remote.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.remote.dao.ProcesoDao;
import com.spring.remote.interfaces.ProcesoInterface;
import com.spring.remote.model.Proceso;

@Service
public class ServiceProceso implements ProcesoInterface{
	@Autowired
	ProcesoDao proceso;
	
	@Override
	public List<Proceso> getAllProcesos() {
		
		return proceso.getAllProcesos();
	}

}
