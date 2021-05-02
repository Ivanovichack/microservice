package com.spring.remote.model;

import java.util.ArrayList;
import java.util.List;

public class ApiError {
	private String mensaje;
	private List<String> detalles;
	
	public ApiError() {
		detalles = new ArrayList<>();
	}
	
	public void addDetalle(String detalle) {
		detalles.add(detalle);
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public List<String> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<String> detalles) {
		this.detalles = detalles;
	}
}
