package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Msg_group;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;

@Repository
public class Register_resident_repository_impl implements Register_resident_repository {
	@Autowired
	private JdbcTemplate template;

	/*
	 * register_url_link
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Register_link read_register_url_link_unlimited(String register_url) {
		Register_link register_link = null;
		String sql = "SELECT " 
					+ "register_address_id, register_house_name, "
					+ "register_house_dong, register_house_hosu " 
				+ "FROM " 
					+ "register_link "
				+ "WHERE " 
					+ "register_url_link = ?;";
		
		try {
		register_link = template.queryForObject(sql, new Object[] { register_url }, new RowMapper<Register_link>() {
			public Register_link mapRow(ResultSet rs, int row_num) throws SQLException {
				Register_link temp_register_link = new Register_link();
				temp_register_link.setRegister_address_id(rs.getInt("register_address_id"));
				temp_register_link.setRegister_house_name(rs.getString("register_house_name"));
				temp_register_link.setRegister_house_dong(rs.getString("register_house_dong"));
				temp_register_link.setRegister_house_hosu(rs.getString("register_house_hosu"));
				return temp_register_link;
			}
		});
		return register_link;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public void create_register_url_link_process(Resident resident, String register_url) {
		String temp_url = register_url.substring(0, register_url.indexOf('-'));
		
		// 가입 url에서 -이전까지의 서브 도메인의 문자열만 얻습니다.
		String register_resident_sql = "INSERT INTO resident " 
					+ "(r_house_id, r_login_id, r_login_pw, "
					+ "r_nickname, r_phone_number, r_email, " 
					+ "r_category) " 
				+ "VALUES" 
					+ "(?, ?, ?, " 
					+ "?, ?, ?, "
					+ "?);";
		
		template.update(register_resident_sql, temp_url, resident.getR_login_id(), resident.getR_login_pw(),
				resident.getR_nickname(), resident.getR_phone_number(), resident.getR_email(), "세대원");
	}
	/* 메서드 이름: read_and_create_house_table_check
	 * 반환 타입: House
	 * 설명: 
	 * 	집의 id 값(house_address_id)와 집(House)의 동과 집(House)의 호수를 WHERE절로 사용하여 DB에 존재하는지 확인합니다.
	 * 	집(House)이 DB에 존재하지 않을 경우 예외처리가 발생합니다. 
	 * 		예외 처리(EmptyResultDataAccessException)가 발생하여 DB에 집(House)을 생성하는 메서드를 호출합니다.
	 * 		예외 처리의 2번째 코드인 return read_and_create_house_if_table_not_exist를 호출합니다.
	 * 	이제 집이 존재하므로 House 객체를 반환합니다.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public House read_and_create_house_if_table_not_exist(Resident resident, String register_url) {
		String sql = "SELECT " 
						+ "house_id, house_dong, house_hosu " 
					+ "FROM house " 
					+ "WHERE "
						+ "house_address_id =? AND house_dong = ? AND house_hosu=?;";

		String temp_register_url = register_url;
		String[] temp_split = temp_register_url.split("-");
		String temp_register_url_by_address_id = temp_split[0];		
		
		try {
			House house = template.queryForObject(sql,
					new Object[] { temp_register_url_by_address_id, 
							resident.getHouse().getHouse_dong(), 
							resident.getHouse().getHouse_hosu() },
					new RowMapper<House>() {
						@Override
						public House mapRow(ResultSet rs, int rowNum) throws SQLException {
							House temp_house = new House();
							temp_house.setHouse_id(rs.getInt("house_id"));
							temp_house.setHouse_dong(rs.getString("house_dong"));
							temp_house.setHouse_hosu(rs.getString("house_hosu"));
							return temp_house;
						}
					});
			return house;
		} catch (EmptyResultDataAccessException e) {
			create_house_in_register(resident, register_url);
			// 3번째 실행 단계입니다. return으로 반환값을 반환하고 메서드를 종료합니다.
			return read_and_create_house_if_table_not_exist(resident, register_url);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

	public void create_house_in_register(Resident resident, String register_url) {
		String sql = "INSERT INTO house"
				+ "(house_address_id, house_dong, house_hosu) "
				+ "VALUES(?, ?, ?);";
		
		String temp_register_url = register_url;
		String[] temp_split = temp_register_url.split("-");
		String temp_register_url_by_address_id = temp_split[0];
		
		template.update(sql,
				temp_register_url_by_address_id, 
				resident.getHouse().getHouse_dong(), 
				resident.getHouse().getHouse_hosu());		
	}
	
	/* 
	 * 
	 */
	@Override
	public void create_register_resident(Resident resident, House house_check) {
		String sql = "INSERT INTO resident"
					+ "(r_house_id, r_login_id, r_login_pw, "
					+ "r_nickname, r_phone_number, r_email, "
					+ "r_category) "
				+ "VALUES"
					+ "(?, ?, ?, "
					+ "?, ?, ?, "
					+ "?);";
		System.out.println("create_register_resident : " + house_check.getHouse_id());
		template.update(sql, 
				house_check.getHouse_id(), resident.getR_login_id(), resident.getR_login_pw(),
				resident.getR_nickname(), resident.getR_phone_number(), resident.getR_email(),
				"세대원"
				);
	}

}
