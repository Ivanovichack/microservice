package com.spring.remote.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.spring.remote.model.Proceso;
import com.spring.remote.util.ProcesoMapper;

@Component
public class ProcesoDao {
	@Autowired
	DataSource ds;
	
	public List<Proceso> getAllProcesos(){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(ds)
				.withProcedureName("SP_GET_PROCESOS")
				.returningResultSet("OUT_PROCESOS", new ProcesoMapper());
		Map<String, Object> out = jdbcCall.execute();
		List<Proceso> procesos = (List<Proceso>)out.get("OUT_PROCESOS");
		return procesos;
	}
	
}
