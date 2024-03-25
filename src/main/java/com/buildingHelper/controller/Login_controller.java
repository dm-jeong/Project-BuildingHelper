package com.buildingHelper.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buildingHelper.domain.Resident;
import com.buildingHelper.service.Login_service;

@Controller
public class Login_controller {
	
	@Autowired
	private Login_service login_service;
	
	
	@GetMapping("/login")
	public String login(@ModelAttribute("Resident") Resident resident) {
		return "login";
	}
	
	/*
	 * 설명: 
	 * 	로그인 처리 PostMapping
	 */
	@PostMapping("/login")
	public String login_process(@ModelAttribute("Resident") Resident resident, Model model, HttpServletRequest request) {
		if(login_service.login(resident, request) == true) { // 로그인 성공	
			return "redirect:/home";
		}
		else if(login_service.login(resident, request) == false) { // 로그인 실패
			model.addAttribute("login_fail", "아이디 혹은 비밀번호가 일치하지 않습니다.");
			return "login";
		}
		
		return "login";
	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate(); // 세션 삭제
		return "redirect:/"; // 첫화면(welcome.jsp)로 이동
	}
	
}
