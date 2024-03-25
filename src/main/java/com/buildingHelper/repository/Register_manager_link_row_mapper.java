package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.buildingHelper.domain.Register_link;

public class Register_manager_link_row_mapper implements RowMapper<Register_link> {

	@Override
	public Register_link mapRow(ResultSet rs, int rowNum) throws SQLException {
		Register_link register_link = new Register_link();
		register_link.setRegister_link_id(rs.getInt("register_link_id"));
		register_link.setRegister_address_id(rs.getInt("register_address_id"));
		register_link.setRegister_url_link(rs.getString("register_url_link"));
        
		register_link.setExpired_date(rs.getString("expired_date"));
        
		register_link.setRegister_house_name(rs.getString("register_house_name"));
		register_link.setRegister_house_dong(rs.getString("register_house_dong"));
		register_link.setRegister_house_hosu(rs.getString("register_house_hosu"));
        
        return register_link;
    }
}
