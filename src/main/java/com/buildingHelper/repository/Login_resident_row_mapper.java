package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.buildingHelper.domain.Resident;



public class Login_resident_row_mapper implements RowMapper<Resident> {

	@Override
	public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
		Resident resident = new Resident();
		resident.setR_login_id(rs.getString("r_login_id"));
		resident.setR_category(rs.getString("r_category"));
		resident.setR_nickname(rs.getString("r_nickname"));
		resident.setR_house_id(rs.getInt("r_house_id"));
		return resident;
	}
	
}
