package com.buildingHelper.repository;

import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Expense_check;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Resident;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class Expense_check_repository_impl implements Expense_check_repository {

	private JdbcTemplate template;

	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	
	
	// 이번달조회
	@Override
	public Expense_check get_expense_check_main(HttpSession session) {
		// 오늘날짜(년월일)
		LocalDate currentDate = LocalDate.now();
		// 오늘날짜에서 년 / 월 추출
		int year = currentDate.getYear();
		int month = currentDate.getMonthValue();

		System.out.println("Year: " + year);
		System.out.println("Month: " + month);
		
        // 세션에서 house_id 조회 
	    House house = (House) session.getAttribute("house");
	    int temp_house_id = house.getHouse_id();
        
	    String sql = "select e.house_id, h.house_id, h.house_dong, h.house_hosu, e.expense_price, e.e_charge, e.water_charge, e.p_charge, e.expense_state, e.expense_year, e.expense_month, e.expense_paid "
	            + "from expense e "
	            + "join house h "
	            + "on e.house_id = h.house_id "
	            + "where e.house_id = ? and e.expense_year= ? and e.expense_month=? ";
	    
		try {
			return template.queryForObject(sql, new Expense_check_row_mapper(), temp_house_id, year, month);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	    
		
		 
	}


	
	
	// 저번 달 조회
	@Override
	public Expense_check expense_check_main_last_month(HttpSession session) {
	    // 현재 날짜 (년/월)
	    LocalDate currentDate = LocalDate.now();
	    int year = currentDate.getYear();
	    int month = currentDate.getMonthValue();

	    // 현재 월이 1월이면 작년 12월로 설정
	    if (month == 1) {
	        year -= 1;
	        month = 12;
	    } else {
	        month -= 1;
	    }

	    // 세션에서 house_id 조회 
	    House house = (House) session.getAttribute("house");
	    int temp_house_id = house.getHouse_id();
	        
	    String sql = "select e.house_id, h.house_id, h.house_dong, h.house_hosu, e.expense_price, e.e_charge, e.water_charge, e.p_charge, e.expense_state, e.expense_year, e.expense_month, e.expense_paid "
	            + "from expense e "
	            + "join house h "
	            + "on e.house_id = h.house_id "
	            + "where e.house_id = ? and e.expense_year = ? and e.expense_month = ?";
	    
	    try {
	        return template.queryForObject(sql, new Expense_check_row_mapper(), temp_house_id, year, month);
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}




	// 년도 기준으로 조회
	@Override
	public List<Expense_check> get_expense_by_year(HttpSession session) {
		LocalDate currentDate = LocalDate.now();
		int year = currentDate.getYear();

		House house = (House) session.getAttribute("house");
	    int temp_house_id = house.getHouse_id();
	    
	    String sql = "select e.house_id, h.house_id, h.house_dong, h.house_hosu, e.expense_price, e.e_charge, e.water_charge, e.p_charge, e.expense_state, e.expense_year, e.expense_month, e.expense_paid "
	            + "from expense e "
	            + "join house h "
	            + "on e.house_id = h.house_id "
	            + "where e.house_id = ? and e.expense_year= ? ";
	    
		List<Expense_check> check= template.query(sql, new Expense_check_row_mapper(),temp_house_id, year);
	    
	    
		return check;
	}

	
	// 전년도 조회
	@Override
	public List<Expense_check> expense_check_by_last_year(HttpSession session) {
		LocalDate currentDate = LocalDate.now();
		int year = currentDate.getYear();

		House house = (House) session.getAttribute("house");
	    int temp_house_id = house.getHouse_id();
	    
	    String sql = "select e.house_id, h.house_id, h.house_dong, h.house_hosu, e.expense_price, e.e_charge, e.water_charge, e.p_charge, e.expense_state, e.expense_year, e.expense_month, e.expense_paid "
	            + "from expense e "
	            + "join house h "
	            + "on e.house_id = h.house_id "
	            + "where e.house_id = ? and e.expense_year= ? ";
	    
		List<Expense_check> check= template.query(sql, new Expense_check_row_mapper(),temp_house_id, year-1);
	    
	    
		return check;
	}



	// house_id 기준으로 전체 조회 
	@Override
	public List<Expense_check> get_expense_check_id(Criteria cri, HttpSession session) {
		
		// 페이징 사용할 limit 변수 2개
	    int offset = cri.get_page_start();
	    int limit = cri.getPer_num();

	    // 세션에서 house_id 조회 
	    House house = (House) session.getAttribute("house");
	    int temp_house_id = house.getHouse_id();
	    
	    
	    String sql = "select e.house_id, h.house_id, h.house_dong, h.house_hosu, e.expense_price, e.e_charge, e.water_charge, e.p_charge, e.expense_state, e.expense_year, e.expense_month, e.expense_paid "
	            + "from expense e "
	            + "join house h "
	            + "on e.house_id = h.house_id "
	            + "where e.house_id = ? "
	            + "ORDER BY e.expense_year desc, e.expense_month desc "
	            + "limit ?, ?";
		
  
		List<Expense_check> check= template.query(sql, new Expense_check_row_mapper(),temp_house_id, offset, limit);
		
		
		return check;

	}
	
	
	// 전체 게시글 개수
	public Integer get_total_count(HttpSession session) {
		// 세션의 house_id 기준으로 게시글 개수 조회
	    House house = (House) session.getAttribute("house");
	    int temp_house_id = house.getHouse_id();
	    
	    String sql = "select count(*) from expense where house_id = ?";
	    
		try {
		    return template.queryForObject(sql, Integer.class, temp_house_id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} 
	}
	
	


//오버로딩

	// 오버로딩-기준날짜
	@Override
	public Expense_check get_expense_check_main(int year, int month, HttpSession session) {

		System.out.println("Year: " + year);
		System.out.println("Month: " + month);
		
        // 세션에서 house_id 조회 
	    House house = (House) session.getAttribute("house");
	    int temp_house_id = house.getHouse_id();
        
	    String sql = "select e.house_id, h.house_id, h.house_dong, h.house_hosu, e.expense_price, e.e_charge, e.water_charge, e.p_charge, e.expense_state, e.expense_year, e.expense_month, e.expense_paid "
	            + "from expense e "
	            + "join house h "
	            + "on e.house_id = h.house_id "
	            + "where e.house_id = ? and e.expense_year= ? and e.expense_month=? ";
	    
		try {
			return template.queryForObject(sql, new Expense_check_row_mapper(), temp_house_id, year, month);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	    
		
		 
	}


	// 오버로딩-기준날짜 전달
	@Override
	public Expense_check expense_check_main_last_month(int year, int month, HttpSession session) {

	    // 현재 월이 1월이면 작년 12월로 설정
	    if (month == 1) {
	        year -= 1;
	        month = 12;
	    } else {
	        month -= 1;
	    }

	    // 세션에서 house_id 조회 
	    House house = (House) session.getAttribute("house");
	    int temp_house_id = house.getHouse_id();
	        
	    String sql = "select e.house_id, h.house_id, h.house_dong, h.house_hosu, e.expense_price, e.e_charge, e.water_charge, e.p_charge, e.expense_state, e.expense_year, e.expense_month, e.expense_paid "
	            + "from expense e "
	            + "join house h "
	            + "on e.house_id = h.house_id "
	            + "where e.house_id = ? and e.expense_year = ? and e.expense_month = ?";
	    
	    try {
	        return template.queryForObject(sql, new Expense_check_row_mapper(), temp_house_id, year, month);
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}


	// 오버로딩 - 기준년월
	@Override
	public List<Expense_check> get_expense_by_year(int year, int month, HttpSession session) {

		House house = (House) session.getAttribute("house");
	    int temp_house_id = house.getHouse_id();
	    
	    String sql = "select e.house_id, h.house_id, h.house_dong, h.house_hosu, e.expense_price, e.e_charge, e.water_charge, e.p_charge, e.expense_state, e.expense_year, e.expense_month, e.expense_paid "
	            + "from expense e "
	            + "join house h "
	            + "on e.house_id = h.house_id "
	            + "where e.house_id = ? and e.expense_year= ? ";
	    
		List<Expense_check> check= template.query(sql, new Expense_check_row_mapper(),temp_house_id, year);
	    
	    
		return check;
	}


	// 오버로딩 - 기준년 전년도
	@Override
	public List<Expense_check> expense_check_by_last_year(int year, int month, HttpSession session) {


		House house = (House) session.getAttribute("house");
	    int temp_house_id = house.getHouse_id();
	    
	    String sql = "select e.house_id, h.house_id, h.house_dong, h.house_hosu, e.expense_price, e.e_charge, e.water_charge, e.p_charge, e.expense_state, e.expense_year, e.expense_month, e.expense_paid "
	            + "from expense e "
	            + "join house h "
	            + "on e.house_id = h.house_id "
	            + "where e.house_id = ? and e.expense_year= ? ";
	    
		List<Expense_check> check= template.query(sql, new Expense_check_row_mapper(),temp_house_id, year-1);
	    
	    
		return check;
	}
	
	

	
	
	
}
