package com.buildingHelper.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Expense_check;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Page_maker;
import com.buildingHelper.service.Expense_check_service;

@Controller
@RequestMapping("/expense")
public class Expense_check_controller {

	@Autowired
	private Expense_check_service expense_check_service;

	@GetMapping
	public String expense_main(HttpSession session, Model model) {
	    // 현재시간 기준으로 이번 달 불러오기
	    Expense_check expense_check_main = expense_check_service.get_expense_check_main(session);
	    model.addAttribute("expense_check", expense_check_main);

	    // 현재 시간 기준으로 저번 달 불러오기
	    Expense_check expense_check_main_last_month = expense_check_service.expense_check_main_last_month(session);
	    model.addAttribute("expense_check_last_month", expense_check_main_last_month);

	    if (expense_check_main_last_month != null) {
	    	
	        // 이번달 저번달 나눠서 % 표시 후 소수점 2자리까지 전처리
	        String charge_diff = expense_check_service.get_expense_diff(expense_check_main, expense_check_main_last_month);
	        model.addAttribute("percent", charge_diff);

	        // 증가했어요! 감소했어요! 표시
	        String message = expense_check_service.increase_or_decrease(charge_diff);
	        model.addAttribute("message", message);
	    }

	    // 이번 년도 전부 불러오기
	    List<Expense_check> expense_check_by_year = expense_check_service.get_expense_by_year(session);
	    model.addAttribute("expense_year", expense_check_by_year);

	    // 전년도 전부 불러오기
	    List<Expense_check> expense_check_by_last_year = expense_check_service.expense_check_by_last_year(session);
	    model.addAttribute("expense_last_year", expense_check_by_last_year);

	    // view 에 필요한 data session 에서 가져옴
	    House session_house = (House) session.getAttribute("house");
	    model.addAttribute("house", session_house);

	    // address session
	    Address session_address = (Address) session.getAttribute("address");
	    model.addAttribute("address", session_address);

	    return "expense_check_main";
	}


	// house_id 기준으로 전부 조회해서 list로 제공
	@GetMapping("/list")
	public String request_expense_id(Criteria cri, Model model, HttpSession session) {
		
		Page_maker page_maker = expense_check_service.set_page_maker(cri, session);
		model.addAttribute("page_maker", page_maker);
		
		// Criteria 객체를 통해 페이징 처리된 데이터를 받아옴
		List<Expense_check> expense_check_by_id = expense_check_service.get_expense_check_id(cri, session);
		model.addAttribute("expense_check", expense_check_by_id);

		// view 에 필요한 data session 에서 가져옴
		House session_house = (House) session.getAttribute("house");
		model.addAttribute("house", session_house);

		// address session
		Address session_address = (Address) session.getAttribute("address");
		model.addAttribute("address", session_address);

		return "expense_check_list";
	}

	// 월 기준으로 상세 조회
	@GetMapping("/month")
	public String detailed_expense_check_from_month(@RequestParam("expense_year") int year, @RequestParam("expense_month") int month, Model model, HttpSession session) {

	    // 월 기준 조회
	    Expense_check expense_check_main = expense_check_service.get_expense_check_main(year, month, session);
	    model.addAttribute("expense_check", expense_check_main);

	    // 월 기준 저번달 조회
	    Expense_check expense_check_main_last_month = expense_check_service.expense_check_main_last_month(year, month, session);
	    model.addAttribute("expense_check_last_month", expense_check_main_last_month);
	    System.out.println(expense_check_main_last_month);
	    
	    
	    if (expense_check_main_last_month != null) {
	        // 이번달 저번달 나눠서 % 표시 후 소수점 2자리까지 전처리
	        String charge_diff = expense_check_service.get_expense_diff(expense_check_main, expense_check_main_last_month);
	        model.addAttribute("percent", charge_diff);

	        // 증가했어요! 감소했어요! 표시
	        String message = expense_check_service.increase_or_decrease(charge_diff);
	        model.addAttribute("message", message);
	    }

	    // 이번 년도 전부 불러오기
	    List<Expense_check> expense_check_by_year = expense_check_service.get_expense_by_year(year, month, session);
	    model.addAttribute("expense_year", expense_check_by_year);

	    // 전년도 전부 불러오기
	    List<Expense_check> expense_check_by_last_year = expense_check_service.expense_check_by_last_year(year, month, session);
	    model.addAttribute("expense_last_year", expense_check_by_last_year);

	    // view에 필요한 데이터 session 에서 가져옴
	    Address session_address = (Address) session.getAttribute("address");
	    model.addAttribute("address", session_address);
	    House session_house = (House) session.getAttribute("house");
	    model.addAttribute("house", session_house);

	    return "expense_check_main";
	}


}
