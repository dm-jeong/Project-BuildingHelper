package com.buildingHelper.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.buildingHelper.domain.Club_board;
import com.buildingHelper.domain.Club_reply;

@Repository
public class Cb_repository_impl implements Cb_repository { // 리포지토리 인터페이스 구현

    private JdbcTemplate cb_template; // JdbcTemplate 객체를 선언

    @Autowired
    public void cb_set_DataSource(DataSource dataSource) // DataSource를 주입받아 템플릿을 초기화
    {
        this.cb_template = new JdbcTemplate(dataSource); // 주입받은 DataSource를 이용하여 템플릿을 생성
    }

    // 아래 함수들은 Cb_repository 인터페이스에서 정의한 함수들의 구현체
    // 각 함수는 SQL 쿼리를 실행하여 데이터베이스와 상호작용
    // 템플릿의 함수를 사용하여 쿼리를 실행하고, 결과를 매핑
    @Override
    public List<Club_board> get_posts_by_c_id_and_offset(Integer c_id, int offset, int limit) {
        String cb_SQL = "SELECT * FROM club_board WHERE cb_c_id = ? ORDER BY cb_time DESC LIMIT ?, ?";
        List<Club_board> posts = cb_template.query(cb_SQL, new Object[]{c_id, offset, limit}, new Cb_row_mapper());
        return posts;
    }
    
    @Override
    public int get_total_posts_by_c_id(Integer c_id) {
        String cb_SQL = "SELECT COUNT(*) FROM club_board WHERE cb_c_id = ?";
        return cb_template.queryForObject(cb_SQL, new Object[]{c_id}, Integer.class);
    }

    @Override
    public void add_post_ing(Club_board new_post) {
        String cb_SQL = "INSERT INTO club_board (cb_c_id, cb_title, cb_description, cb_time, cb_file_name, cb_writer_id, cb_count) VALUES (?, ?, ?, ?, ?, ?, ?)";
        cb_template.update(cb_SQL, new_post.getCb_c_id(), new_post.getCb_title(), new_post.getCb_description(), new_post.getCb_time(), new_post.getCb_file_name(), new_post.getCb_writer_id(), new_post.getCb_count());
    }

    @Override
    public Club_board get_post_by_id(Integer id) {
        String cb_SQL = "SELECT * FROM club_board WHERE cb_id = ?";
        Club_board post = cb_template.queryForObject(cb_SQL, new Cb_row_mapper(), id);
        return post;
    }
    
    @Override
    public void increase_view_count(Integer id) {
        String cb_SQL = "UPDATE club_board SET cb_count = cb_count + 1 WHERE cb_id = ?";
        cb_template.update(cb_SQL, id);
    }
    
    @Override
    public void update_post(Club_board updated_post) {
        String cb_SQL = "UPDATE club_board SET cb_title = ?, cb_description = ?, cb_file_name = ? WHERE cb_id = ?";
        cb_template.update(cb_SQL, updated_post.getCb_title(), updated_post.getCb_description(), updated_post.getCb_file_name(), updated_post.getCb_id());
    }

    
    @Override
    public void delete_post(Integer cb_id) {
        String cb_SQL = "DELETE FROM club_board WHERE cb_id = ?";
        cb_template.update(cb_SQL, cb_id);
    }

    @Override
    public void post_reply(Club_reply club_reply)
    {
    	String cb_SQL = "INSERT INTO club_reply (cr_cb_id, cr_description, cr_writer_id, cr_time) VALUES (?,?,?,?)";
    	cb_template.update(cb_SQL, club_reply.getCr_cb_id(), club_reply.getCr_description(), club_reply.getCr_writer_id(), club_reply.getCr_time());
    }
    
    @Override
    public List<Club_reply> get_reply(Integer id)
    {
    	String cb_SQL = "SELECT * FROM club_reply WHERE cr_cb_id=?";
    	List<Club_reply> reply = cb_template.query(cb_SQL, new Object[] {id}, new Cr_row_mapper());
    	return reply;
    }
    
    @Override
    public void delete_reply(Integer cr_id)
    {
    	String cb_SQL = "DELETE FROM club_reply WHERE cr_id=?";
    	cb_template.update(cb_SQL, cr_id);
    }
}

