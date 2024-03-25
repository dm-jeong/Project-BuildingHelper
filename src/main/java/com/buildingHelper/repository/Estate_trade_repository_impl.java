package com.buildingHelper.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Estate_trade;
import com.buildingHelper.domain.Resident;

import javax.servlet.http.HttpSession;


@Repository
public class Estate_trade_repository_impl implements Estate_trade_repository{

	private JdbcTemplate template;

	// db 연결
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	

	// all-view + 페이징처리(지금은해제해놓음)
	@Override
	public List<Estate_trade> all_estate_list(Criteria cri) {
	    int offset = cri.get_page_start();
	    int limit = cri.getPer_num();
		
		String sql = "SELECT * FROM estate_trade order by estate_trade desc ";
		List<Estate_trade> list_of_estate_trade = template.query(sql, new Estate_trade_row_mapper());	
			
		return list_of_estate_trade;
	}
		
	// 페이징용 게시글 개수 가져오는 
	@Override
	public int get_total_count() {
	    String sql = "SELECT COUNT(*) FROM estate_trade";
	    return template.queryForObject(sql, Integer.class);
	}
	
	
	 
	
	
	// add 
	@Override
	public void set_new_estate_trade(Estate_trade estate_trade, HttpSession session) {
		 // SQL 문
		
	    Resident resi = (Resident)session.getAttribute("resident");
	    String r_login_id = resi.getR_login_id();
	    
		
		String sql = "INSERT INTO estate_trade (estate_trade, building_type, sales_status, title, reg_date, description, board_count, file_name, r_login_id,address_api,move_in ) "+
					"VALUES (?,?,default,?,now(), ?, ?, ?,?,?,?)";
		template.update(sql, 
				estate_trade.getEstate_trade_id(), estate_trade.getBuilding_type(), estate_trade.getTitle(), 
				estate_trade.getDescription(), estate_trade.getBoard_count(), estate_trade.getFile_name(), 
				r_login_id, estate_trade.getAddress_api(), estate_trade.getMove_in());
	
	}

	
	// 하나 조회
	@Override
	public Estate_trade get_estate_trade_id(int estate_trade_id) {
		Estate_trade info = null;
		
		String sql = "SELECT count(*) FROM estate_trade where estate_trade=?";
		int row_count = template.queryForObject(sql, Integer.class, estate_trade_id);
		if(row_count != 0) {
			
			System.out.println("조회수1증가하는곳입니다");
			String updateSql = "UPDATE estate_trade SET board_count = board_count + 1 WHERE estate_trade = ?";
			template.update(updateSql, estate_trade_id);
			
			sql = "SELECT * FROM estate_trade where estate_trade=?";
			info = template.queryForObject(sql, new Object[] {estate_trade_id}, new Estate_trade_row_mapper());	
			
		}
		
		
		
		if(info == null) 
			throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다(item_trade_id)");
		return info;
	}

	
	
	
	
	// 수정
	@Override
	public void set_update_estate_trade(Estate_trade estate_trade) {
		if(estate_trade.getFile_name() != null) {
		System.out.println("이미지를 새로 업데이트 했습니다");		
		
			String sql = "update estate_trade set building_type=?, sales_status=?, title=?, description=?, file_name=?, address_api=?, move_in=? where estate_trade=?";
			template.update(sql, estate_trade.getBuilding_type(), estate_trade.getSales_status(), estate_trade.getTitle(), estate_trade.getDescription(), estate_trade.getFile_name(), estate_trade.getAddress_api(), estate_trade.getMove_in(), estate_trade.getEstate_trade_id());
			
		} else  {
			System.out.println("기존 이미지 입니다");
			String sql = "update estate_trade set building_type=?, sales_status=?, title=?, description=?, address_api=?, move_in=? where estate_trade=?";
			template.update(sql, estate_trade.getBuilding_type(), estate_trade.getSales_status(), estate_trade.getTitle(), estate_trade.getDescription(), estate_trade.getAddress_api(), estate_trade.getMove_in(), estate_trade.getEstate_trade_id());
			
			
		}		
	}

	// 삭제
	@Override
	public void set_delete_estate_trade(int estate_trade_id) {
		String sql = "delete from estate_trade where estate_trade = ?";
		this.template.update(sql, estate_trade_id);
	}

	
}
