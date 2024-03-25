package com.buildingHelper.repository;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.Item_trade;
import com.buildingHelper.domain.Resident;

@Repository
public class Item_trade_repository_impl implements Item_trade_repository{

	private JdbcTemplate template;

	
	// db연결
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	

	public Item_trade_repository_impl() {
		
	}

	
	
	// 세션의 r_address_id 와 db의 address_id 가 일치하는 것만 불러오기 
	@Override	
	public List<Item_trade> get_all_item_trade_list(HttpSession session) {
	    System.out.println("item_trade repository 입니다");
	    
	    Address item_all_adr = (Address)session.getAttribute("address"); // 세션에서 address_id 가져옴
	    int raid = item_all_adr.getAddress_id();

	    String sql;
	    List<Item_trade> list_of_item_trade;

	    sql = "SELECT * FROM item_trade WHERE address_id = ? order by item_trade desc ";
        list_of_item_trade = template.query(sql, new Object[]{raid}, new Item_trade_row_mapper());
	     
        
	    return list_of_item_trade;
	}



	// 추가 - address_id (아파트 단위) 도 db에 같이 저장
	@Override
	public void set_new_item_trade(Item_trade item_trade, HttpSession session) {
		
	    Address set_adr = (Address)session.getAttribute("address");  // 세션에서 address_id 가져옴
	    int raid = set_adr.getAddress_id();

	    Resident set_r_login_id = (Resident)session.getAttribute("resident");  // 세션에서 r_login_id 가져옴 - 작성자 id 누군지 저장
	    String r_login_id = set_r_login_id.getR_login_id();
		
	 // SQL 문
	    String sql = "INSERT INTO item_trade (item_trade, title, category, reg_date, price, description, board_count, file_name, address_id, r_login_id) "+
	                 "VALUES (?, ?, ?, now(), ?, ?, ?, ?, ?, ?)";

	    // SQL 실행
	    template.update(sql, 
	                    item_trade.getItem_trade_id(), 
	                    item_trade.getTitle(), 
	                    item_trade.getCategory(), 
	                    item_trade.getPrice(), 
	                    item_trade.getDescription(), 
	                    item_trade.getBoard_count(), 
	                    item_trade.getFile_name(),
	                    raid, r_login_id);
	}



	// 개인 거래 상세 조회 
	@Override
	public Item_trade get_item_trade_id(int item_trade_id) {
		Item_trade info = null;
		
		String sql = "SELECT count(*) FROM item_trade where item_trade=?";
		int row_count = template.queryForObject(sql, Integer.class, item_trade_id);
		if(row_count != 0) {
			System.out.println("조회수1증가하는곳입니다");
			String updateSql = "UPDATE item_trade SET board_count = board_count + 1 WHERE item_trade = ?";      //  접근할 때마다 조회수 1 증가 (중복 방지 미구현)
			template.update(updateSql, item_trade_id);
			
			sql = "SELECT * FROM item_trade where item_trade=?";
			info = template.queryForObject(sql, new Object[] {item_trade_id}, new Item_trade_row_mapper());	
			
		}
		
		
		if(info == null) 
			throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다(item_trade_id)");
		return info;
	}



	// update : 이미지 업데이트 , 기존 이미지 별 구분
	@Override
	public void set_update_item_trade(Item_trade item_trade) {
		if(item_trade.getFile_name() != null) {
		System.out.println("이미지를 새로 업데이트 했습니다");		
		
			String sql = "update item_trade set title=?, category=?, price=?, description=?, file_name=? where item_trade=?";
			template.update(sql, item_trade.getTitle(), item_trade.getCategory(), item_trade.getPrice(), item_trade.getDescription(), item_trade.getFile_name(), item_trade.getItem_trade_id());
			
		} else  {
			System.out.println("기존 이미지 입니다");
			String sql = "update item_trade set title=?, category=?, price=?, description=? where item_trade=?";
			template.update(sql, item_trade.getTitle(), item_trade.getCategory(), item_trade.getPrice(), item_trade.getDescription(), item_trade.getItem_trade_id());
			
			
		}
	
	}



	// delete : item_trade_id 기준 
	@Override
	public void set_delete_item_trade(String item_trade_id) {
		String sql = "delete from item_trade where item_trade = ?";
		this.template.update(sql, item_trade_id);
	}


		
	
	
}
