package com.buildingHelper.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.buildingHelper.domain.Item_trade;

public interface Item_trade_repository {
	List<Item_trade> get_all_item_trade_list(HttpSession session);	  // 게시글 전부 조회 
	void set_new_item_trade(Item_trade item_trade, HttpSession session); // 게시글 추가
	Item_trade get_item_trade_id(int item_trade); // 게시글 하나 조회
	void set_update_item_trade(Item_trade item_trade);  // 게시글 수정 
	void set_delete_item_trade(String item_trade_id);  // 게시글 삭제
	
}	
