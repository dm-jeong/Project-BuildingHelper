package com.buildingHelper.repository;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.buildingHelper.domain.Item_trade;


public class Item_trade_row_mapper implements RowMapper<Item_trade>{

	@Override
	public Item_trade mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item_trade tr = new Item_trade();
		tr.setItem_trade_id(rs.getInt("item_trade"));
		tr.setAddress_id(rs.getInt("address_id"));
		tr.setTitle(rs.getString("title"));
		tr.setCategory(rs.getString("category"));
	
		
		Timestamp timestamp = rs.getTimestamp("reg_date");
        LocalDateTime local_date_time = timestamp.toLocalDateTime();
        tr.setReg_date(local_date_time);
        
        tr.setPrice(rs.getInt("price"));
        tr.setDescription(rs.getString("description"));
        tr.setBoard_count(rs.getInt("board_count"));
        tr.setFile_name(rs.getString("file_name"));
        tr.setR_login_id(rs.getString("r_login_id"));
           
		return tr;
	}

	
	
}
