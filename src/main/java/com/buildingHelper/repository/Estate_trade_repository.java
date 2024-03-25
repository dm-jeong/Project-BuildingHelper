package com.buildingHelper.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Estate_trade;

public interface Estate_trade_repository {
	List<Estate_trade> all_estate_list(Criteria cri);	// 모두 조회
	void set_new_estate_trade(Estate_trade estate_trade, HttpSession session);  // add : 생성
	Estate_trade get_estate_trade_id(int estate_trade); // 게시글 하나 조회
	
	void set_update_estate_trade(Estate_trade estate_trade); // 게시글 수정
	void set_delete_estate_trade(int estate_trade_id); // 게시글 삭제
	
	int get_total_count();  // 전체 조회 
	
}	
