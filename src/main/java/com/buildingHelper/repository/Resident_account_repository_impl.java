package com.buildingHelper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Msg;
import com.buildingHelper.domain.Msg_group;
import com.buildingHelper.domain.Resident;

@Repository
public class Resident_account_repository_impl implements Resident_account_repository {

	@Autowired
	private JdbcTemplate template;

	/*
	 * 설명: 로그인 아이디 문자열을 파라미터로 세대원에 대한 정보를 DB에서 읽어옵니다.
	 */
	@Override
	public Resident read_resident_information(String r_login_id) {
		String sql = "SELECT * FROM v_select_resident_information WHERE r_login_id = ?;";

		@SuppressWarnings("deprecation") // 경고(노란밑줄)을 안나오게 하기 위한 어노테이션
		Resident resident_information = template.queryForObject(sql, new Object[] { r_login_id },
				new RowMapper<Resident>() {
			public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resident temp_resident = new Resident();
				temp_resident.setR_login_id(rs.getString("r_login_id"));

				temp_resident.setR_login_pw(rs.getString("r_login_pw"));
				temp_resident.setR_category(rs.getString("r_category"));
				temp_resident.setR_nickname(rs.getString("r_nickname"));
				temp_resident.setR_phone_number(rs.getString("r_phone_number"));
				temp_resident.setR_email(rs.getString("r_email"));
				temp_resident.setR_house_id(rs.getInt("r_house_id"));

				House house = new House();
				house.setHouse_dong(rs.getString("house_dong"));
				house.setHouse_hosu(rs.getString("house_hosu"));

				Address address = new Address();
				address.setHouse_name(rs.getString("house_name"));
				return temp_resident;
			}
		});
		return resident_information;
	}

	/*
	 * 메서드 설명: 회원정보 변경(UPDATE) 메서드
	 */
	@Override
	public void update_resident_information(Resident resident) {
		String sql = "UPDATE resident "
				+ "SET r_login_pw = ?, r_nickname = ?, r_phone_number=?, r_email=? "
				+ "WHERE r_login_id=?;";
		template.update(sql,
				resident.getR_login_pw(), resident.getR_nickname(),
				resident.getR_phone_number(), resident.getR_email(),

				resident.getR_login_id()
				);
	}
	/*
	 * 메서드 설명: 같은 house의 모든 resident를 조회합니다.
	 */
	@Override
	public List<Resident> read_list_of_my_house_management(int r_house_id) {
		String sql = "SELECT r_login_id, r_category, r_nickname, r_phone_number, r_email "
				+ "FROM v_select_resident_information "
				+ "WHERE r_house_id = ?;";
		@SuppressWarnings("deprecation")
		List<Resident> lsit_of_my_house_resident = (List<Resident>) template.query(sql, new Object[]{r_house_id}, new RowMapper<Resident>() {
			public Resident mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resident temp_resident = new Resident();
				temp_resident.setR_login_id(rs.getString("r_login_id"));
				temp_resident.setR_category(rs.getString("r_category"));
				temp_resident.setR_nickname(rs.getString("r_nickname"));
				temp_resident.setR_phone_number(rs.getString("r_phone_number"));
				temp_resident.setR_email(rs.getString("r_email"));
				return temp_resident;			
			}
		});
		return lsit_of_my_house_resident;
	}
	/*
	 * 설명: 세대원 중 1명만 미거주할 때 사용합니다.
	 */
	@Override
	public void update_move_resident(String r_login_id) {
		String sql = "UPDATE resident SET r_house_id = 1 WHERE r_login_id=?;";
		template.update(sql, r_login_id);
	}

	/*
	 * 설명: 이사로 모든 세대원들이 미거주할 때 사용합니다.
	 * house_id의 1은 어느 집에도 거주하지 않는걸 표현하기 위해 미리 넣어둔 데이터입니다.
	 */
	@Override
	public void update_move_all_resident_by_house_id(House house) {
		String sql = "UPDATE resident SET r_house_id = 1 WHERE r_house_id = ?;";
		template.update(sql, house.getHouse_id());
	}

	/*
	 * 설명:
	 * - 전송 메시지를 DB에 INSERT 합니다.
	 */
	@Override
	public boolean create_send_message(Resident resident, Msg msg) {
		String sql = "INSERT INTO msg VALUES(null, ?, " // msg_id, msg_sender_r_login_id
				+ "?, ?, ?, ?, " // sender_house_dong/hosu, receiver_house_dong/hosu
				+ "?, ?, ?, ?, null);"; // address_id, msg_title, msg_content, msg_sent_time, msg_receiver_read_time

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			template.update(sql, resident.getR_login_id(),
					
					msg.getMsg_sender_house_dong(), msg.getMsg_sender_house_hosu(),
					msg.getMsg_receiver_house_dong(), msg.getMsg_receiver_house_hosu(),
					
					msg.getMsg_address_id(), msg.getMsg_title(),
					msg.getMsg_content(), timestamp);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	/*
	 * 설명: 받은 모든 메시지의 리스트를 들고옵니다.
	 */
	@Override
	public List<Msg> read_list_of_msg(Address address, House house) {
		String sql = "SELECT msg_id, msg_sender_r_login_id, "
				+ "msg_sender_house_dong, msg_sender_house_hosu, "
				+ "msg_title, msg_content, msg_sent_time "
				+ "FROM msg "
				+ "WHERE msg_address_id =? AND msg_receiver_house_dong = ? "
				+ "AND msg_receiver_house_hosu = ? "
				+ "ORDER BY msg_sent_time DESC;";
		final SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd [HH:mm]");
		@SuppressWarnings("deprecation")
		List<Msg> list_of_my_house_resident = (List<Msg>) template.query(sql, 
				new Object[]{
						address.getAddress_id(),
						house.getHouse_dong(),
						house.getHouse_hosu()}, 
				new RowMapper<Msg>() {
			public Msg mapRow(ResultSet rs, int rowNum) throws SQLException {
				Msg temp_msg = new Msg();
				temp_msg.setMsg_id(rs.getInt("msg_id"));
				temp_msg.setMsg_sender_r_login_id(rs.getString("msg_sender_r_login_id"));
				temp_msg.setMsg_sender_house_dong(rs.getString("msg_sender_house_dong"));
				temp_msg.setMsg_sender_house_hosu(rs.getString("msg_sender_house_hosu"));
				temp_msg.setMsg_title(rs.getString("msg_title"));
				temp_msg.setMsg_content(rs.getString("msg_content"));
				// sdf를 사용하여 시간 형식을 "2024년 3월 3일 13시 30분" 형식으로 변경합니다.
				temp_msg.setMsg_sent_time(sdf.format(rs.getTimestamp("msg_sent_time"))); 
				return temp_msg;			
			}
		});
		return list_of_my_house_resident;
	}
	
	/*
	 * 설명: 메시지 1개의 내용을 전부 읽습니다. 
	 * 만약 메시지를 처음 읽을 경우 DB에 읽은 시간을 기록합니다.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Msg read_msg_detail(String msg_id, Address address, House house) {
	    String sql = "SELECT * FROM msg WHERE msg_id = ?;";
	    final SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd [HH:mm]");
	    
	    Msg msg = null;
	    try {
	        msg = template.queryForObject(sql, new Object[]{msg_id}, new RowMapper<Msg>() {
	            @Override
	            public Msg mapRow(ResultSet rs, int rowNum) throws SQLException {
	                Msg temp_msg = new Msg();
	                temp_msg.setMsg_sender_house_dong(rs.getString("msg_sender_house_dong"));
	                temp_msg.setMsg_sender_house_hosu(rs.getString("msg_sender_house_hosu"));
	                temp_msg.setMsg_title(rs.getString("msg_title"));
	                temp_msg.setMsg_content(rs.getString("msg_content"));
	                temp_msg.setMsg_sent_time(sdf.format(rs.getTimestamp("msg_sent_time")));
	                temp_msg.setMsg_receiver_read_time(sdf.format(rs.getTimestamp("msg_receiver_read_time")));
	                return temp_msg;
	            }
	        });
	    } catch (Exception e) {
	        // 메시지를 처음 읽어서 receiver_read_time이 없을 경우를 위한 예외처리입니다.
	    	String update_sql = "UPDATE msg SET msg_receiver_read_time = now() WHERE msg_id = ?";
		    template.update(update_sql, msg_id);
	    	return read_msg_detail(msg_id, address, house);
	    }

	    return msg;
	}
	
	/*
	 * 설명: 그룹 메시지를 DB에 INSERT 합니다.
	 */
	@Override
	public boolean create_send_group_message(Resident resident, Msg_group msg_group) {
		String sql = "INSERT INTO msg_group "
				+ "VALUES"
					+ "(null, ?, ?, ?, " // msg_group_id, r_login_id, house_dong, house_hosu
					+ "?, ?, ?, ?, " // r_category, receiver_house_dong, address_id, title
					+ "?, NOW());"; // content, time
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			template.update(sql, resident.getR_login_id(),
					msg_group.getMsg_group_sender_house_dong(),
					msg_group.getMsg_group_sender_house_hosu(),
					
					resident.getR_category(),
					msg_group.getMsg_group_receiver_house_dong(),
					msg_group.getMsg_group_address_id(),
					msg_group.getMsg_group_title(),
					
					msg_group.getMsg_group_content());
			return true;
		} catch(Exception e) {
//			e.printStackTrace(); // 디버깅용 함수
			return false;
		}
	}
	/*
	 * 설명: 그룹 메시지를 list 형식으로 읽어옵니다.
	 * 
	 */
	@Override
	public List<Msg_group> read_list_of_msg_group(Address address, House house) {
		String sql = "SELECT msg_group_id, msg_group_title, msg_group_sender_r_category, msg_sent_time "
				+ "FROM msg_group "
				+ "WHERE msg_group_address_id = ? "
					+ "AND (msg_group_receiver_house_dong = ? OR msg_group_receiver_house_dong IS NULL) "
					+ "ORDER BY msg_sent_time DESC;"; // 가장 마지막에 받은 메시지부터 읽습니다.
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd [HH:mm]"); // 시간 출력 포맷을 지정합니다.
		
		@SuppressWarnings("deprecation")
		List<Msg_group> list_of_group_msg = (List<Msg_group>) template.query(sql, new Object[] {
				address.getAddress_id(),
				house.getHouse_dong()},
				new RowMapper<Msg_group>() {
					public Msg_group mapRow(ResultSet rs, int rowNum) throws SQLException {
						Msg_group temp_msg_group = new Msg_group();
						temp_msg_group.setMsg_group_id(rs.getInt("msg_group_id"));
						temp_msg_group.setMsg_group_title(rs.getString("msg_group_title"));
						temp_msg_group.setMsg_group_sender_r_category(rs.getString("msg_group_sender_r_category"));
						temp_msg_group.setMsg_sent_time(sdf.format(rs.getTimestamp("msg_sent_time")));
						return temp_msg_group;
					}
		});
		return list_of_group_msg;
	}
	
	@Override
	public Msg_group read_msg_group_detail(String msg_group_id, Address address, House house) {
		String sql = "SELECT msg_group_title, msg_group_sender_r_category, msg_group_content, msg_sent_time "
				+ "FROM msg_group "
				+ "WHERE msg_group_id = ?;";
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd [HH:mm]"); // 시간 출력 포맷을 지정합니다.
		@SuppressWarnings("deprecation")
		Msg_group msg_group = template.queryForObject(sql, new Object[] {msg_group_id}, new RowMapper<Msg_group>() {
			@Override
			public Msg_group mapRow(ResultSet rs, int rowNum) throws SQLException {
				Msg_group temp_msg_group = new Msg_group();
				temp_msg_group.setMsg_group_title(rs.getString("msg_group_title"));
				temp_msg_group.setMsg_group_sender_r_category(rs.getString("msg_group_sender_r_category"));
				temp_msg_group.setMsg_group_content(rs.getString("msg_group_content"));
				temp_msg_group.setMsg_sent_time(sdf.format(rs.getTimestamp("msg_sent_time")));
				
				return temp_msg_group;
			}

		});
		return msg_group;
	}
	
}
