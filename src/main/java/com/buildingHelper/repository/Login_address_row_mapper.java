package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;

public class Login_address_row_mapper implements RowMapper<Address>{
	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
		Address address = new Address();
		
		address.setAddress_id(rs.getInt("address_id"));
		address.setAddress(rs.getString("address"));
		address.setHouse_name(rs.getString("house_name"));

		return address;
	}

}
