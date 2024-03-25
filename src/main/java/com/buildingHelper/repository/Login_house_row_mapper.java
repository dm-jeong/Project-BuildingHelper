package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.buildingHelper.domain.House;


public class Login_house_row_mapper implements RowMapper<House>{
	@Override
	public House mapRow(ResultSet rs, int rowNum) throws SQLException {
		House house = new House();
		house.setHouse_id(rs.getInt("house_id"));
		house.setAddress_id(rs.getInt("house_address_id"));
		house.setHouse_dong(rs.getString("house_dong"));
		house.setHouse_hosu(rs.getString("house_hosu"));
		
		return house;
	}
}
