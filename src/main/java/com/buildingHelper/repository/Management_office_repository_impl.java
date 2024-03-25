package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;

@Repository
public class Management_office_repository_impl implements Management_office_repository{
	
	@Autowired
	private JdbcTemplate template;
	
	/*
	 * 메서드 이름: update_r_category_in_resident_by_office
	 * 설명: 
	 * 	- 세대원일 경우 동대표로 변경합니다.
	 *  - 동대표일 경우 세대원으로 변경합니다.
	 */
	@Override
	public void update_r_category_in_resident_by_office(String r_login_id) {
		String temp_r_cateogry = read_r_category_by_r_login_id(r_login_id);
		if(temp_r_cateogry.equals("세대원")) {
			// 세대원일 경우 동대표로 변경하는 SQL 코드를 작성합니다.
			String sql = "UPDATE resident SET r_category = '동대표' WHERE r_login_id = ?;";
			template.update(sql, r_login_id);
		} else if(temp_r_cateogry.equals("동대표")) {
			// 동대표일 경우 세대원으로 변경하는 SQL 코드를 작성합니다.
			String sql = "UPDATE resident SET r_category = '세대원' WHERE r_login_id = ?;";
			template.update(sql, r_login_id);
		}
	}

	@Override
	public String read_r_category_by_r_login_id(String r_login_id) {
		String sql = "SELECT r_category FROM resident WHERE r_login_id = ?";
		String temp_r_category = template.queryForObject(sql, String.class, r_login_id); 
		return temp_r_category;
	}

	@Override
	public void update_r_house_id_into_1_by_office(String r_login_id) {
		String sql = "UPDATE resident SET r_house_id = 1 WHERE r_login_id = ?;";
		template.update(sql, r_login_id);
	}
	/*
	 * 설명:
	 * 	- 실제 거주자(resident)가 살고 있는 집(house)만 조회합니다.
	 *  - EXISTS 연산자를 활용하였습니다. EXISTS 연산자는 값이 존재하는지 확인하는 서브쿼리 절입니다.
	 */
	@Override
	public List<House> read_resident_dong_where_resident_lives_by_office(Address address) {
		String sql = "SELECT distinct h.house_dong "
				+ "FROM house h "
				+ "WHERE "
					+ "EXISTS (SELECT 1 FROM resident r WHERE r.r_house_id = h.house_id) "
					+ "AND h.house_address_id = ? "
					+ "ORDER BY CAST(house_dong AS UNSIGNED);";
		@SuppressWarnings("deprecation")
		List<House> list_of_resident_dong_where_resident_lives = template.query(sql, new Object[] {address.getAddress_id()}, 
				new RowMapper<House>() {
			public House mapRow(ResultSet rs, int rowNum) throws SQLException{
			House temp_house = new House();
			temp_house.setHouse_dong(rs.getString("house_dong"));
			return temp_house;
			} // public House mapRow(ResultSet rs, int rowNum) throws SQLException 메서드 끝
		}); 
		return list_of_resident_dong_where_resident_lives;
	}

	@Override
	public List<House> read_resident_hosu_where_resident_lives_by_office(Address address, String dong) {
		String sql = "SELECT * FROM house "
				+ "WHERE house_address_id = ? AND house_dong=? "
				+ "ORDER BY CAST(house_hosu AS UNSIGNED);";
		@SuppressWarnings("deprecation")
		List<House> list_of_resident_hosu_where_resident_lives = template.query(sql, new Object[] {address.getAddress_id(), dong}, new RowMapper<House>() {
			public House mapRow(ResultSet rs, int rowNum) throws SQLException {
				House temp_house = new House();
				temp_house.setHouse_id(rs.getInt("house_id"));
				temp_house.setAddress_id(rs.getInt("house_address_id"));
				temp_house.setHouse_dong(rs.getString("house_dong"));
				temp_house.setHouse_hosu(rs.getString("house_hosu"));
				
				return temp_house;
			}
		});
		return list_of_resident_hosu_where_resident_lives;
	}

	@Override
	public List<Resident> read_resident_detail(Address address, House house) {
		String sql = "SELECT r.* "
				+ "FROM resident r "
					+ "JOIN house h ON r.r_house_id = h.house_id "
				+ "WHERE house_address_id = ? AND house_dong=? AND house_hosu=?;";
		@SuppressWarnings("deprecation")
		List<Resident> list_resident_detail = template.query(sql, 
				new Object[] {address.getAddress_id(), house.getHouse_dong(), house.getHouse_hosu()}, 
				new RowMapper<Resident>() {
			public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resident temp_resident = new Resident();
				temp_resident.setR_login_id(rs.getString("r_login_id"));
				temp_resident.setR_nickname(rs.getString("r_nickname"));
				temp_resident.setR_phone_number(rs.getString("r_phone_number"));
				temp_resident.setR_category(rs.getString("r_category"));
				return temp_resident;
			}
		});
		
		return list_resident_detail;
	}
	
	/*
	 * 설명:
	 * 	register_link의 register_link_id로 레코드를 삭제하는 코드입니다.
	 */
	@Override
	public void delete_register_link_by_register_link_id(Register_link register_link) {
		String sql = "DELETE FROM register_link "
				+ "WHERE register_link_id = ?;";
		template.update(sql, register_link.getRegister_link_id());
	}
}
