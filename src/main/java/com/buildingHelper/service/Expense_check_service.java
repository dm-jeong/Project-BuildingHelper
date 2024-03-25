package com.buildingHelper.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Expense_check;
import com.buildingHelper.domain.Page_maker;

public interface Expense_check_service {
	
	Expense_check get_expense_check_main(HttpSession session);  // 오늘 기준으로 이번달 조회
	Expense_check expense_check_main_last_month(HttpSession session);  // 오늘 기준으로 저번달 조회
	List<Expense_check> get_expense_by_year(HttpSession session);  // 년도 기준으로 조회 
	List<Expense_check> expense_check_by_last_year(HttpSession session); // 전년도 조회
	
	
	List<Expense_check> get_expense_check_id(Criteria cri, HttpSession session); //  id로 관리비 조회 
	public int get_total_count(HttpSession session); // 글 개수 조회 페이징용  
	Page_maker set_page_maker(Criteria cri, HttpSession session);  // expense_check 용 페이징
	
	
	// 오버로딩
	Expense_check get_expense_check_main(int year, int month, HttpSession session);  // 오늘 기준으로 이번달 조회
	Expense_check expense_check_main_last_month(int year, int month, HttpSession session);  // 오늘 기준으로 저번달 조회
	List<Expense_check> get_expense_by_year(int year, int month, HttpSession session);  // 년도 기준으로 조회 
	List<Expense_check> expense_check_by_last_year(int year, int month, HttpSession session); // 전년도 조회
	
	// 전처리
	String get_expense_diff(Expense_check this_month, Expense_check last_month);
	String increase_or_decrease(String charge_diff);

}
