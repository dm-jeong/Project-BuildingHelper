package com.buildingHelper.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.buildingHelper.domain.Share_item;

public interface S_item_service 
{
	List<Share_item> get_all_s_item_list(); // 모든 물품의 정보 가져오는 함수
	List<Share_item> get_s_item_list_by_category(String s_category); // 특정 카테고리의 물품들 정보 가져오는 함수
	Share_item get_s_item_by_id(Integer share_item_id); // 특정 물품의 정보 가져오는 함수
	void set_new_s_item(Share_item share_item); // 물품 등록하는 함수
	void set_update_s_item(Share_item share_item); // 물품 수정하는 함수
	void set_update_s_request(Share_item shareItem); // 요청을 제출하는 함수
	void set_delete_s_item(Integer share_item_id); // 물품 삭제하는 함수
	List<Share_item> get_s_items_request(); // rerquest가 true인 물품 정보 가져오는 함수
	void update_s_item(Share_item shareItem); // request와 approve를 변경하는 함수
	Set<Map<String, Object>> get_all_categories(); // 모든 카테고리와 해당 address_id를 가져오는 함수
}
