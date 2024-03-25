package com.buildingHelper.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingHelper.domain.Estate_trade;
import com.buildingHelper.domain.Item_trade;
import com.buildingHelper.repository.Item_trade_repository;


@Service
public class Item_trade_service_impl implements Item_trade_service{

	@Autowired
	private Item_trade_repository item_trade_repository;
	
	
	// 게시글 전부 조회 
	@Override
	public List<Item_trade> get_all_item_trade_list(HttpSession session) {
		System.out.println("item_trade service입니다");
		return item_trade_repository.get_all_item_trade_list(session);  // repository 접근 
	}

	// add : 게시글 등록
	@Override
	public void set_new_item_trade(Item_trade i_t, HttpSession session) {
		item_trade_repository.set_new_item_trade(i_t, session);
	}

	// 게시글 상세 조회
	@Override
	public Item_trade get_item_trade_by_id(int item_trade_id) {	
		Item_trade info = item_trade_repository.get_item_trade_id(item_trade_id);
		return info;
	}

	
	// 게시글 수정
	@Override
	public void set_update_item_trade(Item_trade item_trade) {
		item_trade_repository.set_update_item_trade(item_trade);
	}

	// 게시글 삭제
	@Override
	public void set_delete_item_trade(String item_trade_id) {
		item_trade_repository.set_delete_item_trade(item_trade_id);
	}

	
	// 시간 표시 전처리 : 몇 일 전, 몇 시간 전
	@Override
	public String set_time(Item_trade item_trade_by_id) {
		
		
			// 현재 시간
			LocalDateTime reg_date = item_trade_by_id.getReg_date();
			

			// 시간 차이 계산
			LocalDateTime current_date = LocalDateTime.now(); 

		    long hours_diff = ChronoUnit.HOURS.between(reg_date,current_date);
		    long days_diff = ChronoUnit.DAYS.between(reg_date,current_date);
		    long minutes_diff = ChronoUnit.MINUTES.between(reg_date, current_date);
		    
		    // 일 전 시간 전 표시 
		    String time_diff;
		    if (days_diff > 0) {
		    	time_diff = days_diff + "일 전";  // 1일 전 
		    } else if(hours_diff>0){
		    	time_diff = hours_diff + "시간 전";  // 0일일 경우  1시간 전 
		    }
		    else {
		    	time_diff = minutes_diff + "분 전";  //  0시간일 경우  10분 전 
		    }
		    
		return time_diff;
	}

	// 시간 표시 전처리 : 몇 일 전, 몇 시간 전 list 버전 	- estate_trade_main.jsp에 사용 
	@Override
	public void listed_set_time(List<Item_trade> list) {  
	    for (Item_trade item_trade : list) {
	        // 현재 시간

	        LocalDateTime updateDate = item_trade.getReg_date();
	        // 시간 차이 계산
	        LocalDateTime currentDate = LocalDateTime.now();
	        long hoursDiff = ChronoUnit.HOURS.between(updateDate, currentDate);
	        long daysDiff = ChronoUnit.DAYS.between(updateDate, currentDate);
	        long minutesDiff = ChronoUnit.MINUTES.between(updateDate, currentDate);

	        // 일 전 시간 전 표시 
	        String timeDiff;
	        if (daysDiff > 0) {
	            timeDiff = daysDiff + "일 전";
	        } else if (hoursDiff > 0) {
	            timeDiff = hoursDiff + "시간 전";
	        } else {
	            timeDiff = minutesDiff + "분 전";
	        }
	        
	        item_trade.setReg_date_time_diff(timeDiff);
	    }    
	}
	
	// 날짜 표시 형식 지정 : yyyy년 MM월 dd일
	@Override
	public String set_formatted_time(Item_trade item_trade_by_id) {
		
		// 글 작성 시간 : reg_date 불러오기
		LocalDateTime reg_date = item_trade_by_id.getReg_date();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"); // 표시 패턴 지정
	    String formatted_date = reg_date.format(formatter);  // reg_date를 formatter 에 적용 

		return formatted_date;
	}
	

	
	
}
