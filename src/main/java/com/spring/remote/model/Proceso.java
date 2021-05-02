package com.spring.remote.model;

public class Proceso {
	private int id_pro;
	private int id_pro_padre;
	private String nombre_proceso;
	private String data_txt;
	private short estado;
	
	public int getId_pro() {
		return id_pro;
	}
	public void setId_pro(int id_pro) {
		this.id_pro = id_pro;
	}
	public int getId_pro_padre() {
		return id_pro_padre;
	}
	public void setId_pro_padre(int id_pro_padre) {
		this.id_pro_padre = id_pro_padre;
	}
	public String getNombre_proceso() {
		return nombre_proceso;
	}
	public void setNombre_proceso(String nombre_proceso) {
		this.nombre_proceso = nombre_proceso;
	}
	public String getData_txt() {
		return data_txt;
	}
	public void setData_txt(String data_txt) {
		this.data_txt = data_txt;
	}
	public short getEstado() {
		return estado;
	}
	public void setEstado(short estado) {
		this.estado = estado;
	}
	
	
}
