package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;

public class Resident_row_mapper implements RowMapper<Resident> {

	@Override
	public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
		Resident resident = new Resident();
		resident.setResident_id(rs.getInt("resident_id"));
		resident.setR_house_id(rs.getInt("r_house_id"));
		resident.setR_login_id(rs.getString("r_login_id"));
		resident.setR_login_pw(rs.getString("r_login_pw"));
		resident.setR_nickname(rs.getString("r_nickname"));
		resident.setR_phone_number(rs.getString("r_phone_number"));
		resident.setR_email(rs.getString("r_email"));
		resident.setR_category(rs.getString("r_category"));
		
		return resident;
	}

}
