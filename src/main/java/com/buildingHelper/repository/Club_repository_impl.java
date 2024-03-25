package com.buildingHelper.repository;

import com.buildingHelper.domain.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class Club_repository_impl implements Club_repository // 리포지토리 인터페이스 구현
{  
    private JdbcTemplate c_template; // JdbcTemplate 객체를 선언

    @Autowired
    public void c_set_DataSource(DataSource dataSource) // DataSource를 주입받아 템플릿을 초기화
    {
        this.c_template = new JdbcTemplate(dataSource); // 주입받은 DataSource를 이용하여 템플릿을 생성
    }

    // 아래 함수들은 Club_repository 인터페이스에서 정의한 함수들의 구현체
    // 각 함수는 SQL 쿼리를 실행하여 데이터베이스와 상호작용
    // 템플릿의 함수를 사용하여 쿼리를 실행하고, 결과를 매핑
    @Override
    public List<Club> get_all_clubs() {
        String c_SQL = "SELECT * FROM club";
        List<Club> list_of_clubs = c_template.query(c_SQL, new Club_row_mapper());
        return list_of_clubs;
    }
    
    @Override
    public void add_club_ing(Club club) {
        String c_SQL = "INSERT INTO club (c_title, c_description, c_time, c_file_name, c_writer_id, c_address_id) VALUES (?, ?, ?, ?, ?, ?)";
        c_template.update(c_SQL, club.getC_title(), club.getC_description(), club.getC_time(), club.getC_file_name(), club.getC_writer_id(), club.getC_address_id());
    }
    
    public void delete_club_ing(Integer c_id) {
        String c_SQL = "DELETE FROM club WHERE c_id = ?";
        c_template.update(c_SQL, c_id);
    }
    
    @Override
    public Club get_club_by_id(Integer c_id) {
        String c_SQL = "SELECT * FROM club WHERE c_id = ?";
        Club club = c_template.queryForObject(c_SQL, new Club_row_mapper(), c_id);
        return club;
    }
    
    @Override
    public void update_club_ing(Club club) {
        String c_SQL = "UPDATE club SET c_title = ?, c_description = ?, c_file_name = ? WHERE c_id = ?";
        c_template.update(c_SQL, club.getC_title(), club.getC_description(), club.getC_file_name(), club.getC_id());
    }
}
