package com.buildingHelper.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Estate_trade;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Item_trade;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.service.Estate_trade_service;
import com.buildingHelper.service.Item_trade_service;


// 로그인 성공 시 페이지 
@Controller("")
public class Welcome_controller {

	// api_key 프로퍼티 
	@Value("${kakao_api_key}")
	private String kakao_api_key;
	

	HttpSession session;
	@Autowired
	private Item_trade_service item_trade_service;

	@GetMapping
	public String welcome_login() {
		return "welcome_login";
	}
	
	@GetMapping("/home")
	public String welcome(@ModelAttribute Criteria cri, Model model, HttpSession session) {
		
		// address 세션에 넣음
		Address welcome_adr = (Address)session.getAttribute("address");
		model.addAttribute("address", welcome_adr);
		
		// 동 호수 세션 - house
		House house = (House)session.getAttribute("house");
		model.addAttribute("house", house);
		
		// 사용자 세션 - resident
		Resident resident = (Resident)session.getAttribute("resident");
		model.addAttribute("resident", resident);
		
		// 중고거래 include용
		List<Item_trade> list = item_trade_service.get_all_item_trade_list(session);
		item_trade_service.listed_set_time(list); // 몇 일전 , 몇 시간 전 표시해주는 전처리
		model.addAttribute("item_trade", list);
		
		
		// api 숨기기
		model.addAttribute("api_key", kakao_api_key);
		System.out.println(kakao_api_key);
		return "welcome";
	}
}
