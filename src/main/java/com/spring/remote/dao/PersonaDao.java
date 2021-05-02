package com.spring.remote.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.spring.remote.model.Persona;
import com.spring.remote.util.PersonaMapper;

import oracle.jdbc.internal.OracleTypes;

@Component
public class PersonaDao {
	@Autowired
	DataSource dataSource;
	
	public List<Persona> getAllPersonas() {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withProcedureName("SP_GET_ALL_PEOPLE")
				.returningResultSet("OUT_PEOPLE", new PersonaMapper());
		
		Map<String, Object> out = jdbcCall.execute();
		List<Persona> personas = (List<Persona>)out.get("OUT_PEOPLE");
		
		return personas;
	}
	
	public void updatePersonById(Persona persona, int id) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withProcedureName("SP_UPDATE_PERSON_BYID");
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("IN_ID", id)
				.addValue("IN_NOMBRE", persona.getNombre())
				.addValue("IN_APELLIDO", persona.getApellido())
				//.addValue("IN_TELEFONO", persona.getTelefono())
				.addValue("IN_CIUDAD", persona.getCiudad())
				.addValue("IN_DOMICILIO", persona.getDomicilio())
				.addValue("IN_RFC", persona.getRfc())
				.addValue("IN_CURP", persona.getCurp());
		jdbcCall.execute(in);		
	}
	
	public void deletePersonById(int id) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withProcedureName("SP_DELETE_PERSON_BYID");
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("IN_ID", id);
		jdbcCall.execute(in);

	}
	
	public void insertPerson(Persona persona) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withProcedureName("SP_INSERT_PERSON");
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("IN_ID", persona.getId())
				.addValue("IN_NOMBRE", persona.getNombre())
				.addValue("IN_APELLIDO", persona.getApellido())
				//.addValue("IN_TELEFONO", persona.getTelefono())
				.addValue("IN_CIUDAD", persona.getCiudad())
				.addValue("IN_DOMICILIO", persona.getDomicilio())
				.addValue("IN_RFC", persona.getRfc())
				.addValue("IN_CURP", persona.getCurp());
		jdbcCall.execute(in);
	}
}
