package com.buildingHelper.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Estate_trade;
import com.buildingHelper.domain.Item_trade;
import com.buildingHelper.repository.Estate_trade_repository;


@Service
public class Estate_trade_service_impl implements Estate_trade_service{

	@Autowired
	private Estate_trade_repository estate_trade_repository;
	
	// 전체 조회
	@Override
	public List<Estate_trade> all_estate_list(Criteria cri) {
		return estate_trade_repository.all_estate_list(cri);
	}

	// add
	@Override
	public void set_new_estate_trade(Estate_trade estate_trade, HttpSession session) {
		estate_trade_repository.set_new_estate_trade(estate_trade, session);

	}

	// 게시글 하나 조회
	@Override
	public Estate_trade get_estate_trade_by_id(int estate_trade_id) {
		return estate_trade_repository.get_estate_trade_id(estate_trade_id);
	}

	// 수정
	@Override
	public void set_update_estate_trade(Estate_trade estate_trade) {
		estate_trade_repository.set_update_estate_trade(estate_trade);
	}

	// 삭제
	@Override
	public void set_delete_estate_trade(int estate_trade_id) {
		estate_trade_repository.set_delete_estate_trade(estate_trade_id);
	}

	// 몇 일 전, 몇 시간 전 표시 전처리 : item_trade와 동일
	@Override
	public String set_time(Estate_trade estate_trade_by_id) {
		// 현재 시간
		LocalDateTime reg_date = estate_trade_by_id.getReg_date();  // reg_date 불러옴
		
		// 시간 차이 계산
		LocalDateTime current_date = LocalDateTime.now();  

	    long hours_diff = ChronoUnit.HOURS.between(reg_date,current_date);
	    long days_diff = ChronoUnit.DAYS.between(reg_date,current_date);
	    System.out.println(days_diff);
	    long minutes_diff = ChronoUnit.MINUTES.between(reg_date, current_date);
	    System.out.println(minutes_diff);

	    // 일 전 시간 전 표시 
	    String time_diff;
	    if (days_diff > 0) {
	    	time_diff = days_diff + "일 전";
	    } else if(hours_diff>0){
	    	time_diff = hours_diff + "시간 전";
	    }
	    else {
	    	time_diff = minutes_diff + "분 전";
	    }
	    
	    return time_diff;		
	}

	
	// 몇 일 전, 몇 시간 전 list버전 
	@Override
	public void listed_set_time(List<Estate_trade> list) {  
	    for (Estate_trade estate_trade : list) {
	        // 현재 시간

	        LocalDateTime updateDate = estate_trade.getReg_date();
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
	        
	        estate_trade.setReg_date_time_diff(timeDiff);
	    }    
	}
	
	
	
	
	// 글 수정 시간 계산 	
	@Override
	public void listed_update_time(List<Estate_trade> list) {
	    for (Estate_trade estate_trade : list) {
	        // 현재 시간
		    System.out.println("글수정시간계산 foreach시작");

	        LocalDateTime updateDate = estate_trade.getUpdate_date();
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
	        
	        estate_trade.setUpdate_date_time_diff(timeDiff);
	    }    
	}
	
	
	


	// 글작성시간 yyyy년 MM월 dd일 양식 처리 전처리 - item_trade와 동일
	@Override
	public String set_formatted_time(Estate_trade estate_trade_by_id) {
		// 글 작성 시간 : reg_date
		LocalDateTime reg_date = estate_trade_by_id.getReg_date();  
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	    String formatted_date = reg_date.format(formatter);

		return formatted_date;
	}



	// 게시글 개수 조회
	@Override
	public int get_total_count() {
		return estate_trade_repository.get_total_count();
	}

	
	// 주소 문자열처리(
	@Override
	public void listed_address_api(List<Estate_trade> list) {
	    for (Estate_trade estate_trade : list) {
	        String temp_address = estate_trade.getAddress_api();
	        if (temp_address != null && !temp_address.isEmpty()) {
	            StringBuilder extracted_words = new StringBuilder();
	            int word_count = 0;

	            // 공백을 기준으로 문자열을 나누고 첫 3개의 단어를 추출
	            for (int i = 0; i < temp_address.length(); i++) {
	                char current_char = temp_address.charAt(i);

	                // 공백이나 탭을 만나면 단어 카운트를 증가시킴
	                if (Character.isWhitespace(current_char)) {
	                    word_count++;
	                 // 연속된 공백을 방지하기 위해 한 번만 추가
	                    if (word_count <= 3) {
	                        extracted_words.append(" ");
	                    }
	                } else {
	                    // 단어 카운트가 3개 이상이면 종료
	                    if (word_count >= 3) {
	                        break;
	                    }
	                    extracted_words.append(current_char);

	                }
	            }

	            estate_trade.setModified_address(extracted_words.toString());
	        }
	    }
	}
	
}	




	
	
	

