package com.buildingHelper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingHelper.domain.Club_board;
import com.buildingHelper.domain.Club_reply;
import com.buildingHelper.domain.Criteria;
import com.buildingHelper.repository.Cb_repository;

@Service
public class Cb_service_impl implements Cb_service // 서비스 인터페이스 구현
{
	private final Cb_repository cb_repository; // 밑에서 이용할 객체 선언

    @Autowired
    public Cb_service_impl(Cb_repository cb_repository) {
        this.cb_repository = cb_repository; // 주입 받은 객체를 클래스 변수에 할당
    }

    // 아래 함수들은 Cb_service 인터페이스에서 정의한 함수들의 구현체
    // 각 함수는 Cb_repository의 함수를 호출하여 수행  
    @Override
    public List<Club_board> get_posts_by_c_id_and_page(Integer c_id, Criteria cri) {
        int offset = cri.get_page_start();
        int limit = cri.getPer_num();
        return cb_repository.get_posts_by_c_id_and_offset(c_id, offset, limit);
    }
    
    @Override
    public int get_total_posts_by_c_id(Integer c_id) {
        return cb_repository.get_total_posts_by_c_id(c_id);
    }
    
    @Override
    public void add_post_ing(Club_board new_post) {
        cb_repository.add_post_ing(new_post);
    }
    
    @Override
    public Club_board get_post_by_id(Integer id) {
        return cb_repository.get_post_by_id(id);
    }
    
    @Override
    public void increase_view_count(Integer id) {
        cb_repository.increase_view_count(id);
    }
    
    @Override
    public void update_post(Club_board updated_post) {
        cb_repository.update_post(updated_post);
    }
    
    @Override
    public void delete_post(Integer cb_id) {
        cb_repository.delete_post(cb_id);
    }
    
    @Override
    public void post_reply(Club_reply club_reply)
    {
    	cb_repository.post_reply(club_reply);
    }
    
    @Override
    public List<Club_reply> get_reply(Integer id)
    {
    	return cb_repository.get_reply(id);
    }
    
    @Override
    public void delete_reply(Integer cr_id)
    {
    	cb_repository.delete_reply(cr_id);
    }
}
