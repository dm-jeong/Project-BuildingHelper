package com.buildingHelper.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.repository.Register_manager_repository;

@Service
public class Register_manager_service_impl implements Register_manager_service {
	
	@Autowired
	Register_manager_repository register_manager_repository;
	
	// 거주민 가입 링크 생성기
	@Override
	public void create_register_link_unlimited(Register_link register_link, int r_house_id) {
		int temp_house_address_id = register_manager_repository.read_house_address_id_by_house_id(r_house_id);
		System.out.println("temp_house_address_id: " + temp_house_address_id);
		register_link.setRegister_address_id(temp_house_address_id);		
		register_manager_repository.create_register_link_unlimited(register_link);
	}
	
	@Override
	public List<Register_link> read_register_link_unlimited(Register_link register_link, Address address, HttpServletRequest request) {
		List<Register_link> list_register_manager_link = register_manager_repository.read_register_link_unlimited(register_link, address, request);
		return list_register_manager_link;
	}
	
	
	@Override
	public List<Register_link> read_register_link_recent_5_links(Address address, HttpServletRequest request) {
		List<Register_link> list_register_link_recent_5_links = register_manager_repository.read_register_link_recent_5_links(address, request);
		return list_register_link_recent_5_links;
	}
		
	// 설명: 파라미터 house_id로 house 테이블의 house_name을 읽어옵니다. 
	@Override
	public String read_house_name(String house_id) {
		return register_manager_repository.read_house_name(house_id);
	}	
	
	/*
	 * 설명: addres_id가 존재하는지 검증합니다. 
	 * 반환 값: 
	 * 	address_id가 있을 경우 1을 반환합니다.
	 * 	address_id가 없을 경우 0을 반환합니다.
	 */
	@Override
	public int read_check_has_address_id(int address_id) {
		return register_manager_repository.read_check_has_address_id(address_id);
	}
	
	/*
	 * 설명: SQL문을 사용하여 house_id로 address_id를 읽어옵니다.
	 */
	@Override
	public int read_house_address_id_by_house_id(int house_id) {
		return register_manager_repository.read_house_address_id_by_house_id(house_id);
	}
	/*
	 * 설명:
	 * 	- 같은 address_id를 가진 세대원을 조회합니다.
	 * 	- 관리소에서 사용합니다.
	 */
	@Override
	public List<Resident> read_resident_information_by_office(Resident resident, Address address) {
		return register_manager_repository.read_resident_information_by_office(resident, address);
	}
}
