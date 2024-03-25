package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.buildingHelper.domain.Vote;
import org.springframework.jdbc.core.RowMapper;

//Vote 객체를 위한 RowMapper 클래스
//Spring JDBC에서 ResultSet의 각 행을 Vote 객체로 매핑하는 방법을 정의
public class Vote_row_mapper implements RowMapper<Vote>
{
	// ResultSet의 행을 Vote 객체로 매핑하는 함수
	@Override
	public Vote mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Vote vote = new Vote(); // 새 Vote 객체 생성
		// ResultSet에서 Vote 객체의 각 필드에 해당하는 값을 가져와서 설정
        // "" 안은 데이터베이스 테이블의 컬럼 이름
		vote.setV_id((Integer) rs.getObject("v_id"));
		vote.setV_title(rs.getString("v_title"));
		vote.setV_option1(rs.getString("v_option1"));
		vote.setV_option2(rs.getString("v_option2"));
		vote.setV_option3(rs.getString("v_option3"));
		vote.setV_option4(rs.getString("v_option4"));
		vote.setV_option5(rs.getString("v_option5"));
		vote.setV_option6(rs.getString("v_option6"));
		vote.setV_option7(rs.getString("v_option7"));
		vote.setV_option8(rs.getString("v_option8"));
		vote.setV_option9(rs.getString("v_option9"));
		vote.setV_option10(rs.getString("v_option10"));
		vote.setV_option11(rs.getString("v_option11"));
		vote.setV_option12(rs.getString("v_option12"));
		vote.setV_option13(rs.getString("v_option13"));
		vote.setV_option14(rs.getString("v_option14"));
		vote.setV_option15(rs.getString("v_option15"));
		vote.setV_option16(rs.getString("v_option16"));
		vote.setV_option17(rs.getString("v_option17"));
		vote.setV_option18(rs.getString("v_option18"));
		vote.setV_option19(rs.getString("v_option19"));
		vote.setV_option20(rs.getString("v_option20"));
		vote.setV_result1(rs.getInt("v_result1"));
		vote.setV_result2(rs.getInt("v_result2"));
		vote.setV_result3(rs.getInt("v_result3"));
		vote.setV_result4(rs.getInt("v_result4"));
		vote.setV_result5(rs.getInt("v_result5"));
		vote.setV_result6(rs.getInt("v_result6"));
		vote.setV_result7(rs.getInt("v_result7"));
		vote.setV_result8(rs.getInt("v_result8"));
		vote.setV_result9(rs.getInt("v_result9"));
		vote.setV_result10(rs.getInt("v_result10"));
		vote.setV_result11(rs.getInt("v_result11"));
		vote.setV_result12(rs.getInt("v_result12"));
		vote.setV_result13(rs.getInt("v_result13"));
		vote.setV_result14(rs.getInt("v_result14"));
		vote.setV_result15(rs.getInt("v_result15"));
		vote.setV_result16(rs.getInt("v_result16"));
		vote.setV_result17(rs.getInt("v_result17"));
		vote.setV_result18(rs.getInt("v_result18"));
		vote.setV_result19(rs.getInt("v_result19"));
		vote.setV_result20(rs.getInt("v_result20"));
		vote.setV_start_time(rs.getTimestamp("v_start_time"));
		vote.setV_end_time(rs.getString("v_end_time"));
		vote.setV_creator_id(rs.getString("v_creator_id"));
		vote.setV_category(rs.getString("v_category"));
		vote.setV_address_id(rs.getInt("v_address_id"));
		vote.setV_house_dong(rs.getString("v_house_dong"));
		return vote; // 매핑이 완료된 Vote 객체를 반환
	}
}
