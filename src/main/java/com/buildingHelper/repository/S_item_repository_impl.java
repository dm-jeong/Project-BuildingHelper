package com.buildingHelper.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.buildingHelper.domain.Share_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class S_item_repository_impl implements S_item_repository // 리포지토리 인터페이스 구현
{
	private JdbcTemplate s_template; // JdbcTemplate 객체를 선언
	
	@Autowired
	public void s_set_Jdbc_template(DataSource dataSource)// DataSource를 주입받아 템플릿을 초기화
	{
		this.s_template = new JdbcTemplate(dataSource); // 주입받은 DataSource를 이용하여 템플릿을 생성
	} 
	
	// 아래 함수들은 S_item_repository 인터페이스에서 정의한 함수들의 구현체
    // 각 함수는 SQL 쿼리를 실행하여 데이터베이스와 상호작용
    // 템플릿의 함수를 사용하여 쿼리를 실행하고, 결과를 매핑
	@Override
	public List<Share_item> get_all_s_item_list()
	{
		String s_SQL = "SELECT * FROM s_item";
		List<Share_item> list_of_items = s_template.query(s_SQL, new S_item_row_mapper());
		return list_of_items;
	}
	
	public List<Share_item> get_s_item_list_by_category(String s_category)
	{
		List<Share_item> s_items_by_category = new ArrayList<Share_item>();
		String s_SQL = "SELECT * FROM s_item where s_category LIKE '%" + s_category + "%'";
		s_items_by_category = s_template.query(s_SQL, new S_item_row_mapper());
		return s_items_by_category;
	}
	
	@Override
	public Set<Map<String, Object>> get_all_categories()
	{
	    String s_SQL = "SELECT DISTINCT s_category, s_address_id FROM s_item"; // category와 address_id만
	    List<Map<String, Object>> list = s_template.queryForList(s_SQL);
	    return new HashSet<Map<String, Object>>(list);
	}
	
	public Share_item get_s_item_by_id(Integer share_item_id)
	{
		Share_item s_item_info = null;
		String s_SQL = "SELECT count(*) FROM s_item where share_item_id=?";
		int rowCount = s_template.queryForObject(s_SQL, Integer.class, share_item_id);
		if(rowCount != 0)
		{
			s_SQL = "SELECT * FROM s_item where share_item_id=?";
			s_item_info = s_template.queryForObject(s_SQL, new Object[] {share_item_id}, new S_item_row_mapper());
		}
			return s_item_info;
	}
	
	public void set_new_s_item(Share_item share_item) 
	{
	    String s_SQL = "INSERT INTO s_item(share_item_id, name, s_category, s_description, s_condition, resident_id, s_start_time, s_return_time, s_file_name, s_request, s_approval, s_address_id)"
	            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    s_template.update(s_SQL, share_item.getShare_item_id(), share_item.getName(), 
	            share_item.getS_category(), share_item.getS_description(), share_item.getS_condition(), share_item.getResident_id(),
	            share_item.getS_start_time(), share_item.getS_return_time(), share_item.getS_file_name(), share_item.isS_request(), share_item.isS_approval(), share_item.getS_address_id());
	}
	
	public void set_update_s_item(Share_item share_item) 
	{
	    String s_SQL = "UPDATE s_item SET name=?, s_category=?, s_description=?, s_file_name=? WHERE share_item_id=?";
	    s_template.update(s_SQL, share_item.getName(), share_item.getS_category(), share_item.getS_description(), share_item.getS_file_name(), share_item.getShare_item_id());
	}
	
	public void set_update_s_request(Share_item shareItem) {
	    String s_SQL = "UPDATE s_item SET resident_id=?, s_start_time=?, s_return_time=?, s_request=? WHERE share_item_id=?";
	    s_template.update(s_SQL, shareItem.getResident_id(), shareItem.getS_start_time(), shareItem.getS_return_time(), shareItem.isS_request(), shareItem.getShare_item_id());
	}

	
	public void set_delete_s_item(Integer share_item_id)
	{
		String s_SQL = "DELETE from s_item where share_item_id=?";
		this.s_template.update(s_SQL, share_item_id);
	}
	
	@Override
	public List<Share_item> get_s_items_request() {
	    String s_SQL = "SELECT * FROM s_item WHERE s_request = true";
	    return s_template.query(s_SQL, new S_item_row_mapper());
	}
	
	@Override
    public void update_s_item(Share_item shareItem) {
		if(shareItem.isS_approval() == true)
		{
			shareItem.setS_condition("대여불가");
		}
		else
		{
			shareItem.setS_condition("대여가능");
		} // 승인 여부에 따라 컨디션이 바뀌게 설정
		
        String s_SQL = "UPDATE s_item SET s_condition = ?, resident_id=?, s_start_time=?, s_return_time=?, s_request=?, s_approval = ? WHERE share_item_id = ?";
        s_template.update(s_SQL, shareItem.getS_condition(), shareItem.getResident_id(), shareItem.getS_start_time(), shareItem.getS_return_time(), shareItem.isS_request(), shareItem.isS_approval(), shareItem.getShare_item_id());
    }
}
