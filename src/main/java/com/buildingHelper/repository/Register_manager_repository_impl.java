package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;
@Repository
public class Register_manager_repository_impl implements Register_manager_repository {

	@Autowired
	private JdbcTemplate template;

	/*
	 * 설명:
	 * 	가입 링크 생성 메서드
	 *  기간 만료 없음
	 */
	@Override
	public void create_register_link_unlimited(Register_link register_link) {
		String sql = "INSERT INTO register_link(" + "register_address_id, " + "register_url_link,  "
				+ "register_house_name, " + "register_house_dong, " + "register_house_hosu) " 
				+ "VALUES"
					+ "(?, ?, ?, ?, ?); ";

		String temp_time = String.valueOf(System.currentTimeMillis()); // URL 생성을 위한 시간 값

		String temp_register_url_link = register_link.getRegister_address_id() + "-" + temp_time;
		template.update(sql, register_link.getRegister_address_id(), temp_register_url_link,
				register_link.getRegister_house_name(), register_link.getRegister_house_dong(),
				register_link.getRegister_house_hosu());
	}


	/*
	 * read_register_link_unlimited(String house_id) 만료 기간이 무제한인 링크만 가져옵니다.
	 */
	@Override
	public List<Register_link> read_register_link_unlimited(Register_link register_link, Address address, HttpServletRequest request) {
		// 실행할 SQL문
		String sql = "SELECT " 
				+ "register_link_id, register_house_dong, register_house_hosu, register_url_link " 
				+ "FROM register_link " 
				+ "WHERE "
					+ "register_address_id = ? AND expired_date = '9999-12-31 23:59:59' AND register_expired_check = FALSE "
				+ "GROUP BY " 
					+ "register_house_name, register_house_dong, register_house_hosu "
				+ "ORDER BY register_house_dong * 1, register_house_hosu * 1;"; // '* 1'이 없을 경우 1다음에 10이 옵니다. 이런 경우를 없애기 위해 *1을 합니다. 단, 숫자 대신 일반 문자열이 먼저 출력됩니다.

		// 현재 URL 값 얻어오기
		StringBuffer current_full_url = request.getRequestURL(); // http://localhost:8080/buildingHelper/management_office/management_office_register_link
		int last_index_1 = current_full_url.toString().lastIndexOf('/'); // 문자열의 마지막 /를 찾습니다.
		// current_full_url에서 /(슬래시)를 기준으로 나머지 문자열 제거 ->
		// http://localhost:8080/buildingHelper/management_office/
		final String current_url_without_sub_url_1 = current_full_url.substring(0, last_index_1); // http://localhost:8080/buildingHelper
		int lastIndex2 = current_url_without_sub_url_1.toString().lastIndexOf('/');

		// current_url_without_sub_url_1에서 /(슬래시)를 기준으로 나머지 문자열 제거 ->
		// http://localhost:8080/buildingHelper/
		final String current_url_without_sub_url_2 = current_url_without_sub_url_1.substring(0, lastIndex2);

		@SuppressWarnings({ "deprecation" })
		List<Register_link> list_of_register_url = (List<Register_link>) template.query(sql,
				new Object[] { address.getAddress_id() }, new RowMapper<Register_link>() {
					public Register_link mapRow(ResultSet rs, int rowNum) throws SQLException {
						Register_link temp_register_link = new Register_link();
						temp_register_link.setRegister_link_id(rs.getInt("register_link_id"));
						temp_register_link.setRegister_house_dong(rs.getString("register_house_dong"));
						temp_register_link.setRegister_house_hosu(rs.getString("register_house_hosu"));
						temp_register_link.setRegister_url_link(
								current_url_without_sub_url_2 + "/register/" + rs.getString("register_url_link"));
						return temp_register_link;
					}
				});
		return list_of_register_url;
	}
	/*
	 * 설명:
	 * 	최근에 생성한 링크를 최대 5개까지 가져옵니다. (LIMIT 5)
	 */
	@Override
	public List<Register_link> read_register_link_recent_5_links(Address address, HttpServletRequest request) {
		String sql = "SELECT " 
				+ "register_link_id, register_house_dong, register_house_hosu, register_url_link " 
				+ "FROM register_link " 
				+ "WHERE "
					+ "register_address_id = ? AND expired_date = '9999-12-31 23:59:59' AND register_expired_check = FALSE "
				+ "GROUP BY " 
					+ "register_house_name, register_house_dong, register_house_hosu "
				+ "ORDER BY register_link_id DESC "
				+ "LIMIT 5;"; // '* 1'이 없을 경우 1다음에 10이 옵니다. 이런 경우를 없애기 위해 *1을 합니다. 단, 숫자 대신 일반 문자열이 먼저 출력됩니다.
				
				// 현재 URL 값 얻어오기
				StringBuffer current_full_url = request.getRequestURL(); // http://localhost:8080/buildingHelper/management_office/management_office_register_link
				int last_index_1 = current_full_url.toString().lastIndexOf('/'); // 문자열의 마지막 /를 찾습니다.

				// current_full_url에서 /(슬래시)를 기준으로 나머지 문자열 제거 ->
				// http://localhost:8080/buildingHelper/management_office/
				final String current_url_without_sub_url_1 = current_full_url.substring(0, last_index_1); // http://localhost:8080/buildingHelper
				int lastIndex2 = current_url_without_sub_url_1.toString().lastIndexOf('/');

				// current_url_without_sub_url_1에서 /(슬래시)를 기준으로 나머지 문자열 제거 ->
				// http://localhost:8080/buildingHelper/
				final String current_url_without_sub_url_2 = current_url_without_sub_url_1.substring(0, lastIndex2);

				@SuppressWarnings({ "deprecation" })
				List<Register_link> list_of_register_url = (List<Register_link>) template.query(sql,
						new Object[] { address.getAddress_id() }, new RowMapper<Register_link>() {
							public Register_link mapRow(ResultSet rs, int rowNum) throws SQLException {
								Register_link temp_register_link = new Register_link();
								temp_register_link.setRegister_link_id(rs.getInt("register_link_id"));
								temp_register_link.setRegister_house_dong(rs.getString("register_house_dong"));
								temp_register_link.setRegister_house_hosu(rs.getString("register_house_hosu"));
								temp_register_link.setRegister_url_link(
										current_url_without_sub_url_2 + "/register/" + rs.getString("register_url_link"));
								return temp_register_link;
							}
						});
				return list_of_register_url;
	}

	@Override
	public String read_house_name(String house_id) {
		String sql = "SELECT house_name FROM house WHERE house_id = ?";
		String house_name = template.queryForObject(sql, String.class, house_id);
		return house_name;
	}

	/*
	 * 설명:
	 * 	세대원이 로그인하여 자기 정보를 확인하는 코드입니다.
	 */
	@Override
	public Resident read_resident_information(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sql = "SELECT r_login_id, r_category, house_dong, house_hosu " 
					+ "FROM v_select_resident_information "
					+ "WHERE r_house_id = ? " 
					+ "ORDER BY house_dong, house_hosu;";

		@SuppressWarnings("deprecation")
		Resident list_of_resident_information = template.queryForObject(sql,
				new Object[] { session.getAttribute("r_house_id") }, new RowMapper<Resident>() {
					public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
						Resident resident_information = new Resident();
						resident_information.setR_login_id(rs.getString("r_login_id"));
						resident_information.setR_category(rs.getString("r_category"));

						// House 객체 생성 및 값 설정
						House house = new House();
						house.setHouse_dong(rs.getString("house_dong"));
						house.setHouse_hosu(rs.getString("house_hosu"));

						return resident_information;
					}
				});

		return list_of_resident_information;
	}

	/*
	 * 작성일: 2024-02-20-화 메서드 
	 * 설명: address_id가 존재하는지 검증합니다. 
	 * 반환: int, 매개 변수와 값이 일치하는 int 값
	 */
	@Override
	public int read_check_has_address_id(int address_id) {
		String sql = "SELECT address_id FROM address WHERE address_id = ?";
		int row_count = template.queryForObject(sql, Integer.class, address_id);
		return row_count;
	}

	/*
	 * 작성일: 2024-02-20-화 메서드 설명: house_id로 house_address_id가 존재하는지 확인합니다. 반환: int,
	 * 실제로 존재하는 house_addres_id의 값이 출력됩니다.
	 */
	@Override
	public int read_house_address_id_by_house_id(int house_id) {
		String sql = "SELECT house_address_id FROM house WHERE house_id = ?";
		int house_address_id = template.queryForObject(sql, Integer.class, house_id);
		return house_address_id;
	}
	/* 2024-02-23-금-세대원 정보 조회 by 관리소
	 * 메서드 이름: read_resident_information_by_office
	 * 메서드 설명: 자기 자신(관리소 계정)을 제외한 같은 address_id를 가진 세대원을 조회합니다. 
	 */
	@Override
	public List<Resident> read_resident_information_by_office(Resident resident, Address address) {
		String sql = "SELECT h.house_dong, h.house_hosu, r.r_login_id, r.r_nickname, r.r_category, a.address_id "
				+ "FROM resident r "
				+ "INNER JOIN house h ON r.r_house_id = h.house_id "
				+ "INNER JOIN address a ON h.house_address_id = a.address_id "
				+ "WHERE address_id = ? AND r_login_id != ?" // 자기 자신을 제외하기 위해 쿼리문에 'r_login_id !=?' 를 사용하였습니다.
				+ "ORDER BY h.house_dong, h.house_hosu ASC;";
		@SuppressWarnings("deprecation")
		List<Resident> lsit_of_address_resident_by_office = (List<Resident>) template.query(sql, new Object[]{address.getAddress_id(), resident.getR_login_id() }, new RowMapper<Resident>() {
			public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resident temp_resident = new Resident();
				Address temp_address = new Address();
				House temp_house = new House();
				// resident의 house
				temp_house.setHouse_dong(rs.getString("house_dong"));
				temp_house.setHouse_hosu(rs.getString("house_hosu"));
				// resident
				temp_resident.setR_login_id(rs.getString("r_login_id"));
				temp_resident.setR_nickname(rs.getString("r_nickname"));
				temp_resident.setR_category(rs.getString("r_category"));
				// resident의 address
				temp_address.setAddress_id(rs.getInt("address_id"));
				
				temp_resident.setHouse(temp_house);
				temp_resident.setAddress(temp_address);
				return temp_resident;			
			}
		});
		return lsit_of_address_resident_by_office;
	}




}
