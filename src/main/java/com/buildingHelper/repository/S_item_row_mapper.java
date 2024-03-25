package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.buildingHelper.domain.Share_item;

public class S_item_row_mapper implements RowMapper<Share_item>
{
	public Share_item mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Share_item share_item = new Share_item();
        share_item.setShare_item_id((Integer) rs.getObject("share_item_id"));
        share_item.setName(rs.getString("name"));
        share_item.setS_category(rs.getString("s_category"));
        share_item.setS_description(rs.getString("s_description"));
        share_item.setS_condition(rs.getString("s_condition"));
        share_item.setResident_id(rs.getString("resident_id"));
        share_item.setS_start_time(rs.getTimestamp("s_start_time"));
        share_item.setS_return_time(rs.getString("s_return_time"));
        share_item.setS_file_name(rs.getString("s_file_name"));
        share_item.setS_request(rs.getBoolean("s_request"));
        share_item.setS_approval(rs.getBoolean("s_approval"));
        share_item.setS_address_id(rs.getInt("s_address_id"));
        return share_item;
	}
}
