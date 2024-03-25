package com.buildingHelper.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.buildingHelper.domain.Estate_trade;
import com.buildingHelper.domain.Item_trade;

public interface Item_trade_service {
	List<Item_trade> get_all_item_trade_list(HttpSession session); // 모두 조회(기본페이지)
	void set_new_item_trade(Item_trade i_t, HttpSession session); // 게시글 등록
	Item_trade get_item_trade_by_id(int item_trade_id); // 게시글 하나 조회(id기준) 
	void set_update_item_trade(Item_trade item_trade);  // 게시글 수정
	void set_delete_item_trade(String item_trade_id); // 게시글 삭제
	String set_time(Item_trade item_trade_by_id);  // 몇 일 전, 몇 시간 전 계산 전처리 
	void listed_set_time(List<Item_trade> list);	// 몇 일 전, 몇 시간 전 계산 전처리 list 버전

	
	String set_formatted_time(Item_trade item_trade_by_id); // 날짜형식 전처리
	
	
}
