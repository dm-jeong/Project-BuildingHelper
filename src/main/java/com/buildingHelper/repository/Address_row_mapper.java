package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.buildingHelper.domain.Address;


public class Address_row_mapper implements RowMapper<Address>{

	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
		Address ad = new Address();
		ad.setAddress_id(rs.getInt("address_id"));
		ad.setAddress(rs.getString("address"));
		ad.setHouse_name(rs.getString("house_name"));

        return ad;
        
	}

	
	
}
