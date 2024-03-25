package com.buildingHelper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Msg;
import com.buildingHelper.domain.Msg_group;
import com.buildingHelper.domain.Resident;

import com.buildingHelper.service.Resident_account_service;

@Controller
@RequestMapping("/resident")
public class Resident_controller {

	@Autowired
	private Resident_account_service resident_account_service;
	
	/*
	 * 설명: 
	 * 	- 계정 정보를 확인하는 메서드입니다.
	 */
	@GetMapping("/account_management")
	public String account_management(@ModelAttribute("Resident") Resident resident,  HttpSession session,
			Model model) {
		Resident temp_resident = (Resident) session.getAttribute("resident");
		resident = resident_account_service.read_resident_information(temp_resident.getR_login_id());
		model.addAttribute("resident", resident);
		
		return "account_management";
	}
	
	/*
	 * 설명: 
	 * 	- PostMapping으로 계정 정보를 수정하는 메서드입니다.
	 */
	@PostMapping("/account_management")
	public String update_account_management(@ModelAttribute("Resident") Resident resident, HttpSession session) {		
		resident_account_service.update_resident_information(resident, session);
		return "redirect:/resident/account_management";
	}
	/*
	 * 설명: 
	 * 	- 같은 house에 사는 모든 세대원을 조회합니다.
	 */
	@GetMapping("/my_house_all_resident")
	public String read_my_house_all_resident(@ModelAttribute("Resident") Resident resident, HttpSession session,
			Model model) {
		Resident session_resident = (Resident) session.getAttribute("resident");
		House session_house = (House) session.getAttribute("house");
		
		
		List<Resident> list_of_my_house_resident = resident_account_service
				.read_list_of_my_house_management(session_resident.getR_house_id());
		
		model.addAttribute("list_of_my_house_resident", list_of_my_house_resident);
		model.addAttribute("house", session_house);
		return "my_house_all_resident";
	}
	
	@PutMapping("/my_house_all_resident/update/{login_id}")
	public ResponseEntity<?> move_resident(@PathVariable("login_id") String id) {
        return new ResponseEntity<String>("File Uploaded Successfully.", HttpStatus.OK);
	}
	
	@PutMapping("/my_house_all_resident/update/{house_id}")
	public ResponseEntity<?> move_resident_all_in_house(@PathVariable("house_id") int r_house_id) {
        return new ResponseEntity<String>("File Uploaded Successfully.", HttpStatus.OK);
	}	
	
	@GetMapping("/my_house_move")
	public String my_house_move(@ModelAttribute("Resident") Resident resident, Model model, HttpSession session) {
		Resident session_resident = (Resident) session.getAttribute("resident");
		
		List<Resident> list_of_my_house_resident = resident_account_service
				.read_list_of_my_house_management(session_resident.getR_house_id());
		
		model.addAttribute("list_of_my_house_resident", list_of_my_house_resident);
		model.addAttribute("house_id", session_resident.getR_house_id());
		return "my_house_all_resident";
	}
	
	/*
	 * 설명: 
	 * 	같은 집에 거주하는 세대원(사용자)의 house_id를 1로 수정합니다.
	 * 	PutMapping이며 javascript를 통하여 통신합니다.
	 */
	@PutMapping("/my_house_all_resident/update_resident/{r_login_id}")
	public ResponseEntity<?> move_resident(@PathVariable("r_login_id") String r_login_id, HttpSession session) {
		Resident session_resident = (Resident) session.getAttribute("resident");
		
		resident_account_service.update_move_resident(r_login_id);
		if(session_resident.getR_login_id() == r_login_id) {
			resident_account_service.update_resident_session(session_resident, session);
		}
        return new ResponseEntity<String>("File Uploaded Successfully.", HttpStatus.OK);
	}
	
	/*
	 * 
	 * 설명:
	 * 	같은 집의 모든 세대원들(사용자들)의 house_id를 1로 수정합니다.
	 * 	주로 이사갈 때 사용합니다. 
	 */
	@PutMapping("/my_house_all_resident/update_resident_all/{r_house_id}")
	public ResponseEntity<?> move_resident_all_in_house(@PathVariable("r_house_id") int r_house_id, HttpSession session) {
		System.out.println("move_resident_all_in_house에 접근하였습니다.");
		Resident resident = (Resident) session.getAttribute("resident");
		House house = (House) session.getAttribute("house");
		
		resident_account_service.update_move_all_resident_by_house_id(house, resident, session);
        return new ResponseEntity<String>("File Uploaded Successfully.", HttpStatus.OK);
	}
	
	/*
	 * 설명:
	 * - 'message/send'와 'message/read'로 갈 수 있는 분기점을 제공합니다.
	 */
	@GetMapping("/message")
	public String message_page() {
		return "message";
	}
	
	/*
	 * 설명:
	 * - 메시지를 전송할 수 있는 폼 양식을 제공합니다.
	 * - message_send.jsp로 페이지 렌더링합니다.
	 */
	@GetMapping("/message/send")
	public String create_message_send(@ModelAttribute("Msg") Msg msg, HttpSession session, Model model) {
		return "message_send";
	}
	
	/*
	 * 설명: 자바스크립트와 ajax를 활용하여 데이터를 전송합니다. 
	 * 없는 동과 호수를 선택할 경우 alert를 출력합니다.
	 */
	@PostMapping("/message/send_message")
	@ResponseBody
	public String create_message_send_process(@ModelAttribute("Msg") Msg msg, HttpSession session, Model model) {
		Address session_address = (Address) session.getAttribute("address");
		House session_house = (House) session.getAttribute("house");
		Resident session_resident = (Resident) session.getAttribute("resident");
		msg.setMsg_address_id(session_address.getAddress_id());
		
		if(resident_account_service.create_send_message(session_resident, msg) == true) {
			return "redirect:/message/send";
		} else 
			return "false";
	}
	
	
	/*
	 * 설명: SQL문에서 msg_mid_receiver 테이블을 '리스트'로 읽어옵니다.
	 * 메시지 내용을 읽기 위해서는 a 태그를 클릭하셔야 합니다.
	 */
	@GetMapping("/message/read")
	public String read_message_list(@ModelAttribute("Msg") Msg msg, HttpSession session, 
			Model model) {
		Address session_address = (Address) session.getAttribute("address");
		House session_house = (House) session.getAttribute("house");
		Resident session_resident = (Resident) session.getAttribute("resident");
		
		List<Msg> list_of_msg = resident_account_service.read_list_of_msg(session_address, session_house);
		model.addAttribute("list_of_msg", list_of_msg);
		return "message_read_list";
	}
	
	/*
	 * 메시지 1개의 모든 내용을 읽습니다.
	 * RequestParam으로 읽어옵니다.
	 */
	@GetMapping("/message/read_detail") // URL 예시: msg=1
	public String message_read_by_id(@RequestParam String msg_id, Model model, HttpSession session) {
		Address session_address = (Address) session.getAttribute("address");
		House session_house = (House) session.getAttribute("house");
		Resident session_resident = (Resident) session.getAttribute("resident");
		// 차후 유효성 검사를 위해 address와 house 파라미터가 있습니다.
		Msg msg = resident_account_service.read_msg_detail(msg_id, session_address, session_house); 
		
		model.addAttribute("msg", msg);
		return "message_read_detail";
	}
	
	/*
	 * 설명:
	 *  - 그룹 메시지를 전송할 수 있는 폼 양식을 제공합니다.
	 *  - message_group_send.jsp로 페이지 렌더링합니다.
	 */
	@GetMapping("/message/send_group_message")
	public String create_message_group_send(@ModelAttribute("Msg_group") Msg_group msg_group, HttpSession session, Model model) {
		return "message_group_send";
	}
	
	/*
	 * 설명: 
	 *  - 자바스크립트와 ajax를 활용하여 데이터를 전송합니다. 
	 *  - 없는 동과 호수를 선택할 경우 alert를 출력합니다.
	 */
	@PostMapping("/message/send_group_message_process")
	@ResponseBody
	public String create_message_group_send_process(@ModelAttribute("Msg_group") Msg_group msg_group, HttpSession session, Model model) {
		Address session_address = (Address) session.getAttribute("address");
		House session_house = (House) session.getAttribute("house");
		Resident session_resident = (Resident) session.getAttribute("resident");
		msg_group.setMsg_group_address_id(session_address.getAddress_id());
		
		if(session_resident.getR_category().equals("관리소")) {
			System.out.println("session 테스트 관리소");
			msg_group.setMsg_group_receiver_house_dong(null);
		} else if(session_resident.getR_category().equals("동대표")) {
			System.out.println("session 테스트 동대표");
			msg_group.setMsg_group_sender_house_dong(session_house.getHouse_dong());
			msg_group.setMsg_group_receiver_house_dong(session_house.getHouse_dong());
		}
		msg_group.setMsg_group_sender_house_dong(session_house.getHouse_dong());
		msg_group.setMsg_group_sender_house_hosu(session_house.getHouse_hosu());
		
		if(resident_account_service.create_send_group_message(session_resident, msg_group) == true) {
			return "redirect:/message/send_group_message_process";
		} else {
			return "false";
		}
	}	
	
	/*
	 * 설명:
	 * 	- 그룹 메시지를 리스트 형태로 읽어옵니다.
	 */
	@GetMapping("/message/read_group_message")
	public String read_message_group_list(@ModelAttribute("Msg_group") Msg msg, Model model, HttpSession session) {
		Address session_address = (Address) session.getAttribute("address");
		House session_house = (House) session.getAttribute("house");
		Resident session_resident = (Resident) session.getAttribute("resident");
		List<Msg_group> list_of_msg_group = resident_account_service.read_list_of_msg_group(session_address, session_house);
		model.addAttribute("list_of_msg_group", list_of_msg_group);
		return "message_group_read_list";
	}
	
	/*
	 * 설명:
	 * 	- 받은 단체 메시지의 상세 내용을 읽습니다. 
	 */
	@GetMapping("/message/read_group_message_detail") // msg_group_id?=1 형태의 URL로 RequestParam으로 매핑됩니다.
	public String read_message_group_detail(@RequestParam String msg_group_id, Model model, HttpSession session) {
		Address session_address = (Address) session.getAttribute("address");
		House session_house = (House) session.getAttribute("house");
		Resident session_resident = (Resident) session.getAttribute("resident");
		Msg_group msg_group = resident_account_service.read_msg_group_detail(msg_group_id, session_address, session_house);
		model.addAttribute("msg_group", msg_group);
		return "message_group_read_detail";
	}
	
	
}
