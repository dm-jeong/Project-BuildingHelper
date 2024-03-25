package com.buildingHelper.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Msg;
import com.buildingHelper.domain.Msg_group;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.repository.Login_repository;
import com.buildingHelper.repository.Resident_account_repository;

@Service
public class Resident_account_service_impl implements Resident_account_service{
	
	@Autowired
	private Resident_account_repository resident_account_repository;
	
	@Autowired
	private Login_repository login_repository; 
	
	@Override
	public Resident read_resident_information(String r_login_id) {
		return resident_account_repository.read_resident_information(r_login_id);
	}

	@Override
	public void update_resident_information(Resident resident, HttpSession session) {
		resident_account_repository.update_resident_information(resident);
		update_resident_session(resident, session);
	}

	@Override
	public List<Resident> read_list_of_my_house_management(int r_house_id) {
		return resident_account_repository.read_list_of_my_house_management(r_house_id);
	}
	
	/*
	 * 세대원 1명의 house_id를 1로 변경합니다.
	 * house_id가 1이면 house_id의 house_address_id 외래키, address_id는 1입니다.
	 */
	@Override
	public void update_move_resident(String r_login_id) {
		resident_account_repository.update_move_resident(r_login_id);
	}
	
	/*
	 * 같은 house에 거주하는 모든 세대원의 house_id와 address_id를 1로 변경합니다.
	 * house_id가 1이면 house_id의 house_address_id 외래키, address_id는 1입니다.
	 */
	@Override
	public void update_move_all_resident_by_house_id(House house, Resident resident, HttpSession session) {
		resident_account_repository.update_move_all_resident_by_house_id(house);
		update_resident_session(resident, session);
	}
	/*
	 * 설명: resident의 session을 갱신합니다.
	 */
	@Override
	public void update_resident_session(Resident resident, HttpSession session) {
		resident = login_repository.read_resident_information_when_login(resident);
		
		session.setAttribute("resident", resident);
		session.getAttribute("resident");
		Resident temp_resident = (Resident) session.getAttribute("resident");
		
		House house = login_repository.read_house_information_when_login(temp_resident);
		session.setAttribute("house", house);
		House session_house = (House) session.getAttribute("house");
		Address address = login_repository.read_address_information_when_login(session_house);
		session.setAttribute("address", address);
	}
	
	/*
	 * 메서드 이름: create_send_message
	 * 설명: 전송 메시지를 DB에 삽입합니다.
	 */
	@Override
	public boolean create_send_message(Resident resident, Msg msg) {	
		return resident_account_repository.create_send_message(resident, msg);
	}
	
	/*
	 * 메서드 이름: read_list_of_msg
	 * 설명: 받은 모든 메시지를 List 타입으로 읽어옵니다.
	 */
	@Override
	public List<Msg> read_list_of_msg(Address address, House house) {
		return resident_account_repository.read_list_of_msg(address, house);
	}
	
	/*
	 * 설명: msg_id로 전체 메시지 내용을 읽어옵니다. 
	 */
	@Override
	public Msg read_msg_detail(String msg_id, Address address, House house) {
		return resident_account_repository.read_msg_detail(msg_id, address, house);
	}
	
	/*
	 * 
	 * 설명: 그룹 메시지를 DB에 삽입합니다.
	 */
	@Override
	public boolean create_send_group_message(Resident resident, Msg_group msg_group) {
		return resident_account_repository.create_send_group_message(resident, msg_group);
	}
	
	/*
	 * 설명: 그룹 메시지를 DB로부터 SELECT해서 들고옵니다.이때 제목, r_category, msg_sent_time을 SELECT 합니다.
	 */
	@Override
	public List<Msg_group> read_list_of_msg_group(Address address, House house) {
		return resident_account_repository.read_list_of_msg_group(address, house);
	}
	
	/*
	 * 설명: 메시지 1개에 대한 내용을 SELECT해서 들고옵니다. msg_group_id를 WHERE절에 넣습니다.
	 */
	@Override
	public Msg_group read_msg_group_detail(String msg_group_id, Address address, House house) {
		return resident_account_repository.read_msg_group_detail(msg_group_id, address, house);
	}
}
