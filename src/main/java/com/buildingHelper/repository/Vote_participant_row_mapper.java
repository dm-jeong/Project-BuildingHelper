package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.buildingHelper.domain.Vote_participant;

//Vote_participant 객체를 위한 RowMapper 클래스
//Spring JDBC에서 ResultSet의 각 행을 Vote_participant 객체로 매핑하는 방법을 정의
public class Vote_participant_row_mapper implements RowMapper<Vote_participant>
{
	// ResultSet의 행을 Vote_participant 객체로 매핑하는 함수
	@Override
	public Vote_participant mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Vote_participant vote_participant = new Vote_participant(); // 새 Vote_participant 객체 생성
		// ResultSet에서 Vote_participant 객체의 각 필드에 해당하는 값을 가져와서 설정
        // "" 안은 데이터베이스 테이블의 컬럼 이름
		vote_participant.setVp_id((Integer) rs.getObject("vp_id"));
		vote_participant.setVp_v_id((Integer) rs.getObject("vp_v_id"));
		vote_participant.setVp_result(rs.getString("vp_result"));
		vote_participant.setVp_house_id(rs.getInt("vp_house_id"));
		return vote_participant; // 매핑이 완료된 Vote_participant 객체를 반환
	}
}
