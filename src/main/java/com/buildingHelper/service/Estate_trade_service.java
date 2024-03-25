package com.buildingHelper.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Estate_trade;
import com.buildingHelper.domain.Item_trade;

public interface Estate_trade_service {
	List<Estate_trade> all_estate_list(Criteria cri); // 모두 조회(기본페이지)
	void set_new_estate_trade(Estate_trade estate_trade, HttpSession session); // 생성
	Estate_trade get_estate_trade_by_id(int estate_trade_id); // 조회(id기준) 
	
	void set_update_estate_trade(Estate_trade estate_trade);  // 수정
	void set_delete_estate_trade(int estate_trade_id); // 삭제
	String set_time(Estate_trade estate_trade_by_id);  // 몇 일 전, 몇 시간 전 계산
	void listed_set_time(List<Estate_trade> list);
	String set_formatted_time(Estate_trade estate_trade_by_id); // 날짜형식
	
	int get_total_count();  // 페이징용 전체 게시글 수 계산
	
	void listed_update_time(List<Estate_trade> list); // 게시글 수정 시간 불러와서 몇 일전, 몇 시간 전으로 전처리 후 dto에 전달
    void listed_address_api(List<Estate_trade> list); // 주소 문자열( 광역시/도, 시/군/구, 읍/면/동 3개만표시) 처리해서 dto에 전달

}
