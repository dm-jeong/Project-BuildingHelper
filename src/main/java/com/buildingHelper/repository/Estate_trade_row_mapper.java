package com.buildingHelper.repository;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.buildingHelper.domain.Estate_trade;


public class Estate_trade_row_mapper implements RowMapper<Estate_trade>{

	@Override
	public Estate_trade mapRow(ResultSet rs, int rowNum) throws SQLException {
		Estate_trade tr = new Estate_trade();
		tr.setEstate_trade_id(rs.getInt("estate_trade"));
		tr.setBuilding_type(rs.getString("building_type"));
		tr.setSales_status(rs.getString("sales_status"));
		tr.setTitle(rs.getString("title"));
		
		
		Timestamp timestamp = rs.getTimestamp("reg_date");
        LocalDateTime local_date_time = timestamp.toLocalDateTime();
        tr.setReg_date(local_date_time);
        
        tr.setDescription(rs.getString("description"));
        tr.setBoard_count(rs.getInt("board_count"));
        tr.setFile_name(rs.getString("file_name"));
        tr.setR_login_id(rs.getString("r_login_id"));
        tr.setAddress_api(rs.getString("address_api"));
        tr.setMove_in(rs.getDate("move_in"));
        
		return tr;
	}

	
	
}
