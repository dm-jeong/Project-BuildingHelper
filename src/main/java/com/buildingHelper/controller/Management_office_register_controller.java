package com.buildingHelper.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.service.Register_resident_service;

/*
 * 거주자 전용 회원가입 페이지
 * 목표:
 * URL 파라미터 따라서 다른 주택 ID를 가지며 가입 되도록 할 것
 */
@Controller
@RequestMapping("/register")
public class Management_office_register_controller {
	
	@Autowired
	private Register_resident_service register_resident_service;
	
	/*
	 * 설명:
	 * 	- 회원가입 페이지에 접속합니다. 
	 */
	@GetMapping("/{register_url}")
	public String register_resident(@ModelAttribute("Resident") Resident resident, 
			@PathVariable String register_url, HttpServletRequest request,
			@ModelAttribute("Register_manager_link") Register_link register_link,
			Model model) {
		register_link = register_resident_service.read_register_url_link_unlimited(register_url);
		if(register_link == null) 
		{
			return "error"; // 없는 가입 링크에 접근할 경우 에러 페이지로 이동합니다.
		}
		model.addAttribute("register_house_name", register_link.getRegister_house_name());
		model.addAttribute("register_house_dong", register_link.getRegister_house_dong());
		model.addAttribute("register_house_hosu", register_link.getRegister_house_hosu());

		return "register_resident";
	}
	/*
	 * 설명:
	 *  - 회원가입을 진행합니다.
	 */
	@PostMapping("/{register_url}")
	public String register_resident_process(@ModelAttribute("Resident") Resident resident,  @PathVariable String register_url) {
		House temp_house_check = register_resident_service.read_and_create_house_if_table_not_exist(resident, register_url);
		register_resident_service.create_register_resident(resident, temp_house_check);
		return "redirect:/";
	}
}
