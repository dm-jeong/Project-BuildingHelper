package com.buildingHelper.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.repository.Login_repository;

@Service
public class Login_service_impl implements Login_service {

	@Autowired
	private Login_repository login_repository;
	
	/*
	 * 설명: 
	 * 	로그인 성공 시 true, 로그인 실패 시 false 반환
	 */
	@Override
	public boolean login(Resident resident, HttpServletRequest request) {
		if (login_repository.login(resident) == true) { // 로그인 성공 시
			HttpSession session = request.getSession();
			
			/*
			 * 설명: resident 세션에 담는 정보: r_login_id, r_nickname, r_category, r_house_id
			 */
			resident = login_repository.read_resident_information_when_login(resident);			
			session.setAttribute("resident", resident);
			session.getAttribute("resident");
			Resident session_resident = (Resident) session.getAttribute("resident");
			/*
			 * 설명: house 세션에 담는 정보: SELECT * FROM house;
			 */
			House session_house = login_repository.read_house_information_when_login(session_resident);
			session.setAttribute("house", session_house);
			/*
			 * 설명: address 세션에 담는 정보: SELECT * FROM address;
			 */			
			Address session_address = login_repository.read_address_information_when_login(session_house);
			session.setAttribute("address", session_address);			
			
			return true;
		} else if (login_repository.login(resident) == false) { // 로그인 실패시
			return false;
		}
		return false;
	}

	@Override
	public String read_category(String r_login_id) {
		return login_repository.read_category(r_login_id);
	}

	@Override
	public String read_r_house_id_by_r_login_id(String r_login_id) {
		return login_repository.read_r_house_id_by_r_login_id(r_login_id);
	}

	@Override
	public boolean read_house_id_existent(String r_building_id) {
		return login_repository.read_house_id_existent(r_building_id);
	}
	
}
