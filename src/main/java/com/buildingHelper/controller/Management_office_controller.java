package com.buildingHelper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.service.Management_office_service;
import com.buildingHelper.service.Register_manager_service;

/*
 * 설명: 
 * 	관리사무소에서 사용하는 Controller(컨트롤러)입니다.
 */
@Controller
@RequestMapping("/management_office")
public class Management_office_controller {

	@Autowired
	private Register_manager_service register_manager_service;
	
	@Autowired
	private Management_office_service management_office_service;
	
	/*
	 * 설명:
	 * 	회원가입 페이지로 이동합니다 
	 */
	@GetMapping("/management_office_register_link")
	public String management_office_register_link(
			@ModelAttribute("Register_manager_link") Register_link register_manager_link, 
			Model model, HttpSession session, HttpServletRequest request) {
		Resident session_resident = (Resident) session.getAttribute("resident");
		House session_house = (House) session.getAttribute("house");
		Address session_address = (Address) session.getAttribute("address");
		
		model.addAttribute("r_house_name", session_address.getHouse_name());
		
		// 최근에 생성한 가입 링크 5개를 List<Register_link>에 담습니다.
		List<Register_link> list_of_register_url_recent_5_links = register_manager_service.read_register_link_recent_5_links(session_address, request);
		model.addAttribute("list_of_register_url_recent_5_links", list_of_register_url_recent_5_links);
		
		// 생성한 모든 가입 링크를 List<Register_link>에 담습니다.
		List<Register_link> list_of_register_url = register_manager_service.read_register_link_unlimited(register_manager_link, session_address, request);
		model.addAttribute("list_of_register_url", list_of_register_url);
		
		return "management_office_register_link";
	}
	/*
	 * 설명: 회원가입 링크를 PostMapping으로 생성합니다.
	 */
	@PostMapping("/management_office_register_link")
	public String create_management_office_register_link(
			@ModelAttribute("Register_manager_link") Register_link register_manager_link,
			Model model, HttpSession session) {
		Resident session_resident = (Resident) session.getAttribute("resident");
		House session_house = (House) session.getAttribute("house");
		Address session_address = (Address) session.getAttribute("address");
		
		register_manager_link.setRegister_address_id(session_address.getAddress_id());
		
		register_manager_service.create_register_link_unlimited(register_manager_link, session_house.getHouse_id());
		
		return "redirect:/management_office/management_office_register_link"; // redirect 안하면 새로고침 눌렀을 때 링크가 또 생성됩니다.
	}
	
	
	/*
	 * 설명:
	 * 	회원가입 링크를 삭제합니다.
	 */	
	@DeleteMapping("/management_office_register_link/delete_register_link/{register_url_link}")
	public ResponseEntity<?> delete_register_link_by_register_linkid(@PathVariable("register_url_link") int register_url_link) {
		System.out.println("회원가입 링크 삭제 접근 확인 " + register_url_link);
		Register_link temp_register_link = new Register_link();
		temp_register_link.setRegister_link_id(register_url_link);
		
		management_office_service.delete_register_link_by_register_link_id(temp_register_link);
        return new ResponseEntity<String>("File Uploaded Successfully.", HttpStatus.OK);
	}
	/*
	 * 설명:
	 * 	- dong(동)을 리스트 형식으로 반환받아서 모델로 가져옵니다.
	 * 
	 */
	@GetMapping("/management_office_read_resident")
	public String management_office_read_resident(@ModelAttribute("Resident") Resident resident, Model model, HttpSession session) {
		Resident session_resident = (Resident) session.getAttribute("resident");
		House session_house = (House) session.getAttribute("house");
		Address session_address = (Address) session.getAttribute("address");
		
		List<House> list_resident_dong_read_by_office = management_office_service.read_resident_dong_where_resident_lives_by_office(session_address);
		model.addAttribute("list_resident_dong_read_by_office", list_resident_dong_read_by_office);
		return "management_office_read_resident";
	}
	
	/*
	 * 설명: 
	 * 	- dong(동)을 파라미터로 해당 dong(동)에 존재하는 hosu(호수)를 출력합니다.  
	 */
	@GetMapping("/management_office_read_resident_by_dong")
	public String management_office_read_resident_by_dong(@RequestParam String dong, Model model, HttpSession session) {
		Resident session_resident = (Resident) session.getAttribute("resident");
		House session_house = (House) session.getAttribute("house");
		Address session_address = (Address) session.getAttribute("address");		
		
		List<House> list_resident_hosu_read_by_office = management_office_service.read_resident_hosu_where_resident_lives_by_office(session_address, dong);
		model.addAttribute("dong", dong); // 현재 페이지가 몇동인지 뷰, management_office_read_resident_by_dong(.jsp)로 보내기 위한 메서드입니다.
		model.addAttribute("list_resident_hosu_read_by_office", list_resident_hosu_read_by_office);
		
		return "management_office_read_resident_by_dong";
	}
	
	/*
	 * 설명: 
	 * 	- dong(동)과 hosu를 파라미터로 해당 house(집)에 존재하는 세대원을 출력합니다.  
	 */
	@GetMapping("/management_office_read_resident_detail")
	public String management_office_read_resident_detail(@RequestParam String dong, @RequestParam String hosu, Model model, HttpSession session) {
		Resident session_resident = (Resident) session.getAttribute("resident");
		House session_house = (House) session.getAttribute("house");
		Address session_address = (Address) session.getAttribute("address");		
		System.out.println("requestparam string dong : " + dong);
		System.out.println("requestparam string hosu : " + hosu);
		
		House temp_house = new House();
		temp_house.setHouse_dong(dong);
		temp_house.setHouse_hosu(hosu);
		
		List<Resident> list_resident_detail = management_office_service.read_resident_detail(session_address, temp_house);
		
		model.addAttribute("temp_house", temp_house);
		model.addAttribute("list_resident_detail", list_resident_detail);
		
		return "management_office_read_resident_detail";
	}	
	
	/*
	 * 설명: 
	 * 	- 자바스크립트(Javascript)로 세대원의 카테고리(r_category)를 변경합니다.
	 * 	- 세대원일 경우 동대표로 변경합니다.
	 *  - 동대표일 경우 세대원으로 변경합니다.
	 */
	@PutMapping("/update/{r_login_id}")
	public ResponseEntity<?> update_r_category(@PathVariable("r_login_id") String id) {
		management_office_service.update_r_category_in_resident_by_office(id);
        return new ResponseEntity<String>("r_category changed", HttpStatus.OK);
	}
		
	/*
	 * 설명: 
	 * 	- 미거주 상태로 변경하는 메서드입니다.
	 * 		- 미거주상태란 세대원(resident)의 house_id를 1로 변경합니다. 
	 *  - 자바스크립트로 서버와 통신합니다. 
	 */
	@PutMapping("/update_move_resident/{r_login_id}")
	public ResponseEntity<?> move_resident_by_office(@PathVariable("r_login_id") String r_login_id) {
		management_office_service.update_r_house_id_into_1_by_office(r_login_id);
        return new ResponseEntity<String>("File Uploaded Successfully.", HttpStatus.OK);
	}	
}
