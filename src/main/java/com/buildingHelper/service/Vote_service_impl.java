package com.buildingHelper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Vote;
import com.buildingHelper.domain.Vote_participant;
import com.buildingHelper.repository.Vote_repository;

@Service
public class Vote_service_impl implements Vote_service // 서비스 인터페이스 구현
{
	private final Vote_repository vote_repository; // 밑에서 이용할 객체 선언

    @Autowired // 자동으로 주입
    public Vote_service_impl(Vote_repository vote_repository) {
        this.vote_repository = vote_repository; // 주입 받은 객체를 클래스 변수에 할당
    }

    // 아래 함수들은 Vote_service 인터페이스에서 정의한 함수들의 구현체
    // 각 함수는 vote_repository의 함수를 호출하여 수행   
    @Override
    public List<Vote> get_all_votes_and_page1(int address, Criteria cri) {
        int offset = cri.get_page_start();
        int limit = cri.getPer_num();
        return vote_repository.get_all_votes_and_offset1(address, offset, limit);
    }
    
    @Override
    public List<Vote> get_all_votes_and_page2(int address, Criteria cri) {
        int offset = cri.get_page_start();
        int limit = cri.getPer_num();
        return vote_repository.get_all_votes_and_offset2(address, offset, limit);
    }
    
    @Override
    public int get_total_votes1(int address) {
        return vote_repository.get_total_votes1(address);
    }
    
    @Override
    public int get_total_votes2(int address) {
        return vote_repository.get_total_votes2(address);
    }
    
    @Override
    public void add_vote_ing(Vote vote) {
        vote_repository.add_vote_ing(vote);
    }
    
    @Override
    public Vote get_vote_by_id(int v_id) {
        return vote_repository.get_vote_by_id(v_id);
    }
    
    @Override
    public boolean duplicate_vote_ing(int v_id, int house_id) {
        return vote_repository.duplicate_vote_ing(v_id, house_id);
    }
    
    @Override
    public void vote_ing(int v_id, int voteOption) {
        vote_repository.vote_ing(v_id, voteOption);
    }
    
    @Override
    public void vote_ing2(Vote_participant vote_participant)
    {
    	vote_repository.vote_ing2(vote_participant);
    }
    
    @Override
    public void delete_vote_ing(Integer v_id) {
        vote_repository.delete_vote_ing(v_id);
    }
    
    @Override
    public Vote_participant get_vote_result(int v_id, int house_id) {
        return vote_repository.get_vote_result(v_id, house_id);
    }
}
