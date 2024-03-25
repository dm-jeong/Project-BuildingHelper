package com.buildingHelper.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.buildingHelper.domain.Vote;
import com.buildingHelper.domain.Vote_participant;

@Repository
public class Vote_repository_impl implements Vote_repository // 리포지토리 인터페이스 구현
{
	private JdbcTemplate v_template; // JdbcTemplate 객체를 선언
	
	@Autowired
	public void v_set_DataSource(DataSource dataSource) // DataSource를 주입받아 템플릿을 초기화
	{
		this.v_template = new JdbcTemplate(dataSource); // 주입받은 DataSource를 이용하여 템플릿을 생성
	}

	// 아래 함수들은 Vote_repository 인터페이스에서 정의한 함수들의 구현체
    // 각 함수는 SQL 쿼리를 실행하여 데이터베이스와 상호작용
    // 템플릿의 함수를 사용하여 쿼리를 실행하고, 결과를 매핑
	@Override
    public List<Vote> get_all_votes_and_offset1(int address, int offset, int limit) {
        String v_SQL = "SELECT * FROM vote WHERE v_category = '관리소' AND v_address_id = ? ORDER BY v_start_time DESC LIMIT ?, ?";
        List<Vote> votes1 = v_template.query(v_SQL, new Object[]{address, offset, limit}, new Vote_row_mapper());
        return votes1;
    }
	
	@Override
	public List<Vote> get_all_votes_and_offset2(int address, int offset, int limit) {
	    String v_SQL = "SELECT * FROM vote WHERE (v_category = '세대원' OR v_category = '동대표') AND v_address_id = ? ORDER BY v_start_time DESC LIMIT ?, ?";
	    List<Vote> votes2 = v_template.query(v_SQL, new Object[]{address, offset, limit}, new Vote_row_mapper());
	    return votes2;
	}

	@Override
    public int get_total_votes1(int address) {
        String v_SQL = "SELECT COUNT(*) FROM vote WHERE v_category = '관리소' AND v_address_id = ?";
        return v_template.queryForObject(v_SQL, new Object[]{address}, Integer.class);
    }
	
	@Override
    public int get_total_votes2(int address) {
        String v_SQL = "SELECT COUNT(*) FROM vote WHERE (v_category = '세대원' OR v_category = '동대표') AND v_address_id = ?";
        return v_template.queryForObject(v_SQL, new Object[]{address}, Integer.class);
    }
	
	@Override
	public void add_vote_ing(Vote vote) {
	    String v_SQL = "INSERT INTO vote (v_title, v_start_time, v_end_time, v_creator_id, v_category, v_address_id, v_house_dong, v_option1, v_option2, v_option3, v_option4, v_option5, v_option6, v_option7, v_option8, v_option9, v_option10, v_option11, v_option12, v_option13, v_option14, v_option15, v_option16, v_option17, v_option18, v_option19, v_option20, v_result1, v_result2, v_result3, v_result4, v_result5, v_result6, v_result7, v_result8, v_result9, v_result10, v_result11, v_result12, v_result13, v_result14, v_result15, v_result16, v_result17, v_result18, v_result19, v_result20) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    v_template.update(v_SQL, vote.getV_title(), vote.getV_start_time(), vote.getV_end_time(), vote.getV_creator_id(), vote.getV_category(), vote.getV_address_id(), vote.getV_house_dong(), vote.getV_option1(), vote.getV_option2(), vote.getV_option3(), vote.getV_option4(), vote.getV_option5(), vote.getV_option6(), vote.getV_option7(), vote.getV_option8(), vote.getV_option9(), vote.getV_option10(), vote.getV_option11(), vote.getV_option12(), vote.getV_option13(), vote.getV_option14(), vote.getV_option15(), vote.getV_option16(), vote.getV_option17(), vote.getV_option18(), vote.getV_option19(), vote.getV_option20(), vote.getV_result1(), vote.getV_result2(), vote.getV_result3(), vote.getV_result4(), vote.getV_result5(), vote.getV_result6(), vote.getV_result7(), vote.getV_result8(), vote.getV_result9(), vote.getV_result10(), vote.getV_result11(), vote.getV_result12(), vote.getV_result13(), vote.getV_result14(), vote.getV_result15(), vote.getV_result16(), vote.getV_result17(), vote.getV_result18(), vote.getV_result19(), vote.getV_result20());
	}

	@Override
    public Vote get_vote_by_id(int v_id) {
        String v_SQL = "SELECT * FROM vote WHERE v_id = ?";
        return v_template.queryForObject(v_SQL, new Object[]{v_id}, new Vote_row_mapper());
    }
	
	@Override
    public boolean duplicate_vote_ing(int v_id, int house_id) 
	{
		String v_SQL = "SELECT count(*) FROM vote_participant WHERE vp_v_id = ? AND vp_house_id = ?";
	    Integer count = v_template.queryForObject(v_SQL, new Object[]{v_id, house_id}, Integer.class);
	    return count != null && count > 0;
    } // 조건에 만족하면 true를 반환
	
	@Override
    public void vote_ing(int v_id, int voteOption) {
        String v_SQL = "UPDATE vote SET v_result" + voteOption + " = v_result" + voteOption + " + 1 WHERE v_id = ?";
        v_template.update(v_SQL, v_id);
    }
	
	@Override
	public void vote_ing2(Vote_participant vote_participant)
	{
		String v_SQL = "INSERT INTO vote_participant (vp_house_id, vp_v_id, vp_result) VALUES (?, ?, ?)";
		v_template.update(v_SQL, vote_participant.getVp_house_id(), vote_participant.getVp_v_id(), vote_participant.getVp_result());
	}
	
	@Override
    public void delete_vote_ing(Integer v_id) {
		String v_SQL = "DELETE FROM vote WHERE v_id = ?";
        v_template.update(v_SQL, v_id);
    }
	
	@Override
	public Vote_participant get_vote_result(int v_id, int house_id) {
	    String v_SQL = "SELECT * FROM vote_participant WHERE vp_v_id = ? AND vp_house_id = ?";
	    List<Vote_participant> results = v_template.query(v_SQL, new Object[]{v_id, house_id}, new Vote_participant_row_mapper());

	    return results.isEmpty() ? null : results.get(0); // 조건에 만족하지 않으면 null 반환
	}

}
