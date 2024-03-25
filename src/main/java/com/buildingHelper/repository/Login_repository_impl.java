package com.buildingHelper.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Resident;

@Repository
public class Login_repository_impl implements Login_repository {

	@Autowired
	private JdbcTemplate template; 	// db 사용을 위한 JdbcTemplate 클래스

	// 로그인할 때 DB의 값과 비교합니다.
	@Override
	public boolean login(Resident resident) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) r_category FROM resident WHERE r_login_id=? AND r_login_pw=?";
		int row_count = this.template.queryForObject(sql, Integer.class, resident.getR_login_id(), resident.getR_login_pw());
		if(row_count == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}

	// 사용자의 r_category를 얻습니다.
	// 로그인 한 상태에서 사용한다고 가정하고 사용합니다.
	@Override
	public String read_category(String r_login_id) {
		String sql = "SELECT r_category FROM resident WHERE r_login_id=?";
		String category = template.queryForObject(sql, String.class, r_login_id);
		return category;
	}

	// get_house_id_by_r_login_id(String r_login_id)
	// 아이디로 아이디가 속해있는 '공동주택의 아이디 값'을 얻습니다.
	@Override
	public String read_r_house_id_by_r_login_id(String r_login_id) {
		String sql = "SELECT h.house_id FROM resident AS r "
				+ "JOIN house AS h "
				+ "ON h.house_id = r.r_house_id "
				+ "WHERE r.r_login_id = ?";
		String r_house_id = template.queryForObject(sql, String.class, r_login_id);
		return r_house_id;
	}

	// read_house_id_existent(String r_building_id)
	// 유효성 검사
	// house_id가 실제로 존재하는지 유효성 검사를 합니다.
	@Override
	public boolean read_house_id_existent(String r_house_id) {
		String sql = "SELECT house_id FROM house WHERE house_id=?";
		int row_count = template.queryForObject(sql, Integer.class, r_house_id);
		if (row_count == Integer.parseInt(r_house_id)) {
			return true;
		}
		return false;
	}

	@Override
	public String read_house_name_by_r_login_id(String r_login_id) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * 메서드 이름: read_resident_information_when_login
	 * 설명: 로그인 성공 이후 세션에 담을 때 해당되는 resident 테이블의 정보를 일부 읽어옵니다.
	 */
	public Resident read_resident_information_when_login(Resident resident) {
		String sql = "SELECT r_login_id, r_nickname, r_category, r_house_id "
					+ "FROM resident "
					+ "WHERE r_login_id = ?;";

		@SuppressWarnings("deprecation")
		Resident temp_resident = template.queryForObject(sql, 
				new Object[]{resident.getR_login_id()}, 
				new Login_resident_row_mapper());
		return temp_resident;
	}
	
	
	
	/*
	 * 설명: house_address_id 기준으로 address 세션에 필요한 정보를 담는 메서드입니다.
	 */
	public Address read_address_session(int house_address) {
		String sql = "SELECT * FROM address WHERE address_id= ?";
		@SuppressWarnings("deprecation")
		Address temp_address = template.queryForObject(sql, new Object[] {house_address}, new Address_row_mapper());
		
		return temp_address;	
		
	}
	
	/*
	 * 
	 * 설명: 로그인 성공 이후 세션에 담을 때 해당되는 house 테이블의 컬럼을 읽어옵니다.
	 */
	@Override
	public House read_house_information_when_login(Resident resident) {
		String sql = "SELECT * FROM house WHERE house_id = ?;";
		
		@SuppressWarnings("deprecation")
		House temp_house = template.queryForObject(sql, new Object[] {resident.getR_house_id()}, new Login_house_row_mapper());
		
		return temp_house;
	}
	/*
	 * 
	 * 설명: 로그인 성공 이후 세션에 담을 때 해당되는 address 테이블의 컬럼을 읽어옵니다.
	 */
	@Override
	public Address read_address_information_when_login(House house) {
		String sql = "SELECT * FROM address WHERE address_id = ?;";
		
		@SuppressWarnings("deprecation")
		Address temp_address = template.queryForObject(sql, new Object[] {house.getAddress_id()}, new Login_address_row_mapper());
		
		return temp_address;
	}

}
