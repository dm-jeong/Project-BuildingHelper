package com.buildingHelper.repository;

import com.buildingHelper.domain.Club_board;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//Club_board 객체를 위한 RowMapper 클래스
//Spring JDBC에서 ResultSet의 각 행을 Club_board 객체로 매핑하는 방법을 정의
public class Cb_row_mapper implements RowMapper<Club_board> {

	// ResultSet의 행을 Club_board 객체로 매핑하는 함수
    @Override
    public Club_board mapRow(ResultSet rs, int rowNum) throws SQLException {
        Club_board club_board = new Club_board(); // 새 Club_board 객체 생성
        // ResultSet에서 Club_board 객체의 각 필드에 해당하는 값을 가져와서 설정
        // "" 안은 데이터베이스 테이블의 컬럼 이름
        club_board.setCb_id((Integer) rs.getObject("cb_id"));
        club_board.setCb_c_id((Integer) rs.getObject("cb_c_id"));
        club_board.setCb_title(rs.getString("cb_title"));
        club_board.setCb_description(rs.getString("cb_description"));
        club_board.setCb_time(rs.getTimestamp("cb_time"));
        club_board.setCb_file_name(rs.getString("cb_file_name"));
        club_board.setCb_writer_id(rs.getString("cb_writer_id"));
        club_board.setCb_count(rs.getInt("cb_count"));
        return club_board; // 매핑이 완료된 Club 객체를 반환
    }
}
