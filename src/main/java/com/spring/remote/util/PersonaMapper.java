package com.spring.remote.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.remote.model.Persona;

public class PersonaMapper implements RowMapper<Persona>{

	@Override
	public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
		Persona persona = new Persona();
		persona.setId(rs.getInt("ID"));
		persona.setNombre(rs.getString("NOMBRE"));
		persona.setApellido(rs.getString("APELLIDO"));
		//persona.setTelefono(rs.getString("TELEFONO"));
		persona.setCiudad(rs.getString("CIUDAD"));
		persona.setDomicilio(rs.getString("DOMICILIO"));
		persona.setRfc(rs.getString("RFC"));
		persona.setCurp(rs.getString("CURP"));
		return persona;
	}

}
