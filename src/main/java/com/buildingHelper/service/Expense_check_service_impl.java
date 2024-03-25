package com.buildingHelper.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Expense_check;
import com.buildingHelper.domain.Page_maker;
import com.buildingHelper.repository.Expense_check_repository;

@Service
public class Expense_check_service_impl  implements Expense_check_service{

	@Autowired
	private Expense_check_repository expense_check_repository;
	
	
	
	// 이번달 조회
	@Override
	public Expense_check get_expense_check_main(HttpSession session) {
		return expense_check_repository.get_expense_check_main(session);
	}

	// 저번달 조회
	@Override
	public Expense_check expense_check_main_last_month(HttpSession session) {
		return expense_check_repository.expense_check_main_last_month(session);
	}
	


	// 이번년도 조회
	@Override
	public List<Expense_check> get_expense_by_year(HttpSession session) {
		return expense_check_repository.get_expense_by_year(session);
	}


	

	

	// 전년도 조회
	@Override
	public List<Expense_check> expense_check_by_last_year(HttpSession session) {
		return expense_check_repository.expense_check_by_last_year(session);
	}





	// 관리비 조회
	@Override
	public List<Expense_check> get_expense_check_id(Criteria cri, HttpSession session) {
		
		return expense_check_repository.get_expense_check_id(cri, session);
		 
	}


	
	
	
	
	// expense_check용 페이징
	@Override
	public Page_maker set_page_maker(Criteria cri, HttpSession session) {
		
		// db에서 count(*) 실행 
		int total_count = get_total_count(session);   
		
		// criteria 세팅
		cri.setPage(cri.getPage() <= 0 ? 1 : cri.getPage());
		cri.setPer_num(cri.getPer_num() <= 0 ? 10 : cri.getPer_num());
		
		
        Page_maker page_maker = new Page_maker();      
        page_maker.setCri(cri);
        page_maker.setTotal_count(total_count);  // count(*) 실행 한 것을 dto에 저장 (여기 실행 단계에 가서 전처리 실행)
        
        return page_maker;
	}
	
	// 글 전체 개수 조회
	@Override
	public int get_total_count(HttpSession session) {
		return 	expense_check_repository.get_total_count(session);
		
	}


//	오버로딩
	
	@Override
	public Expense_check get_expense_check_main(int year, int month, HttpSession session) {
		return expense_check_repository.get_expense_check_main(year, month, session);

	}

	@Override
	public Expense_check expense_check_main_last_month(int year, int month, HttpSession session) {
		return expense_check_repository.expense_check_main_last_month(year, month, session);

	}

	@Override
	public List<Expense_check> get_expense_by_year(int year, int month, HttpSession session) {
		return expense_check_repository.get_expense_by_year(year, month, session);

	}

	@Override
	public List<Expense_check> expense_check_by_last_year(int year, int month, HttpSession session) {
		return expense_check_repository.expense_check_by_last_year(year, month, session);

	}

	
	
	
	// 전처리 - 이번달/저번달 증감 비율 % 소수점 2자리만 표시되게
	@Override
	public String get_expense_diff(Expense_check this_month, Expense_check last_month) {
			
		int this_month_price = this_month.getExpense_price();
		int last_month_price = last_month.getExpense_price();
		// 소수점2자리만표시되게 전처리
		DecimalFormat df = new DecimalFormat("0.00");
		double expense_change = ((double) (this_month_price - last_month_price) / last_month_price) * 100;
		String formatted_expense_change = df.format(expense_change);
			
		return formatted_expense_change;
	}

	// 증가했어요! 감소했어요! 표시
	@Override
	public String increase_or_decrease(String charge_diff) {
		double expense_change = Double.parseDouble(charge_diff);
		String message;
		if (expense_change > 0) {
			message = "증가했어요!";
		} else {
			message = "감소했어요!";
		}
		return message;
	}
	
	
}
