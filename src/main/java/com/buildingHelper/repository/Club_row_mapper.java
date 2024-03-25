package com.buildingHelper.repository;

import com.buildingHelper.domain.Club;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//Club 객체를 위한 RowMapper 클래스
//Spring JDBC에서 ResultSet의 각 행을 Club 객체로 매핑하는 방법을 정의
public class Club_row_mapper implements RowMapper<Club> 
{
	// ResultSet의 행을 Club 객체로 매핑하는 함수
    @Override
    public Club mapRow(ResultSet rs, int rowNum) throws SQLException 
    {
        Club club = new Club(); // 새 Club 객체 생성
        // ResultSet에서 Club 객체의 각 필드에 해당하는 값을 가져와서 설정
        // "" 안은 데이터베이스 테이블의 컬럼 이름
        club.setC_id((Integer) rs.getObject("c_id"));
        club.setC_title(rs.getString("c_title"));
        club.setC_description(rs.getString("c_description"));
        club.setC_time(rs.getTimestamp("c_time"));
        club.setC_file_name(rs.getString("c_file_name"));
        club.setC_writer_id(rs.getString("c_writer_id"));
        club.setC_address_id(rs.getInt("c_address_id"));
        return club; // 매핑이 완료된 Club 객체를 반환
    }
}
