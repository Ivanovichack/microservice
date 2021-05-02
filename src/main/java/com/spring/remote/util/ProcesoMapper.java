package com.spring.remote.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.remote.model.Proceso;

public class ProcesoMapper implements RowMapper<Proceso>{

	@Override
	public Proceso mapRow(ResultSet rs, int rowNum) throws SQLException {
		Proceso proceso = new Proceso();
		proceso.setId_pro(rs.getInt("ID_PRO"));
		proceso.setId_pro_padre(rs.getInt("ID_PRO_PADRE"));
		proceso.setNombre_proceso(rs.getString("NOMBRE_PROCESO"));
		proceso.setData_txt(rs.getString("DATA_TXT"));
		proceso.setEstado(rs.getShort("ESTADO"));
		return proceso;
	}

}
