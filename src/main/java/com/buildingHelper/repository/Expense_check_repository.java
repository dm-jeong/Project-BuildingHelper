package com.buildingHelper.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Expense_check;

public interface Expense_check_repository {
	
	Expense_check get_expense_check_main(HttpSession session);  // 이번달 조회
	Expense_check expense_check_main_last_month(HttpSession session);  // 저번달 조회
	List<Expense_check> get_expense_by_year(HttpSession session);  // 이번년도
	List<Expense_check> expense_check_by_last_year(HttpSession session); // 전년도
	
	List<Expense_check> get_expense_check_id(Criteria cri, HttpSession session);  // 페이징적용 리스트조회
	public Integer get_total_count(HttpSession session);  // 게시글갯수조회(페이징용)
	

	//오버로딩
	Expense_check get_expense_check_main(int year,int month, HttpSession session);  // 이번달 조회
	Expense_check expense_check_main_last_month(int year,int month, HttpSession session);  // 저번달 조회
	List<Expense_check> get_expense_by_year(int year,int month, HttpSession session);  // 이번년도
	List<Expense_check> expense_check_by_last_year(int year,int month, HttpSession session); // 전년도	
	
	
	
}