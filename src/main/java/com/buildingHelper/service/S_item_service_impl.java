package com.buildingHelper.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingHelper.domain.Share_item;
import com.buildingHelper.repository.S_item_repository;

@Service
public class S_item_service_impl implements S_item_service 
{
	private final S_item_repository s_item_repository; // 밑에서 이용할 객체 선언
	
	@Autowired // 자동으로 주입
    public S_item_service_impl(S_item_repository s_item_repository) {
        this.s_item_repository = s_item_repository; // 주입 받은 객체를 클래스 변수에 할당
    }
	
	// 아래 함수들은 S_item_service 인터페이스에서 정의한 함수들의 구현체
    // 각 함수는 S_item_repository의 함수를 호출하여 수행  
	public List<Share_item> get_all_s_item_list() 
	{
		return s_item_repository.get_all_s_item_list();
	}
	
	public List<Share_item> get_s_item_list_by_category(String s_category)
	{
		List<Share_item> s_items_by_category = s_item_repository.get_s_item_list_by_category(s_category);
		return s_items_by_category;
	} 
	
	public Share_item get_s_item_by_id(Integer share_item_id)
	{
		Share_item s_item_by_id = s_item_repository.get_s_item_by_id(share_item_id);
		return s_item_by_id;
	}
	
	public void set_new_s_item(Share_item share_item)
	{
		s_item_repository.set_new_s_item(share_item);
	}
	
	public void set_update_s_item(Share_item share_item)
	{
		s_item_repository.set_update_s_item(share_item);
	} 
	
	public void set_update_s_request(Share_item shareItem)
	{
		s_item_repository.set_update_s_request(shareItem);
	}
	
	public void set_delete_s_item(Integer share_item_id)
	{
		s_item_repository.set_delete_s_item(share_item_id);
	} 
	
	public List<Share_item> get_s_items_request() {
	    return s_item_repository.get_s_items_request();
	}
	
	@Override
    public void update_s_item(Share_item shareItem) {
        s_item_repository.update_s_item(shareItem);
    }
	
	@Override
	public Set<Map<String, Object>> get_all_categories() {
	    return s_item_repository.get_all_categories();
	}
}
