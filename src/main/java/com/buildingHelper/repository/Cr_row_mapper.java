package com.buildingHelper.repository;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.buildingHelper.domain.Club_reply;

public class Cr_row_mapper implements RowMapper<Club_reply>
{
	@Override
	public Club_reply mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Club_reply club_reply = new Club_reply();
		
		club_reply.setCr_id((Integer) rs.getObject("cr_id"));
		club_reply.setCr_cb_id((Integer) rs.getObject("cr_cb_id"));
		club_reply.setCr_description(rs.getString("cr_description"));
		club_reply.setCr_writer_id(rs.getString("cr_writer_id"));
		club_reply.setCr_time(rs.getTimestamp("cr_time"));
		
		return club_reply;
	}
}
