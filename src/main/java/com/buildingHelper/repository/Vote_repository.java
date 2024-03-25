package com.buildingHelper.repository;

import java.util.List;

import com.buildingHelper.domain.Vote;
import com.buildingHelper.domain.Vote_participant;

public interface Vote_repository // 레포지토리 인터페이스 정의
{
	List<Vote> get_all_votes_and_offset1(int address, int offset, int limit); // (category가 관리소인 경우)모든 투표를 가져오는 함수
	List<Vote> get_all_votes_and_offset2(int address, int offset, int limit); // (category가 세대원, 동대표인 경우)모든 투표를 가져오는 함수
	int get_total_votes1(int address); // (category가 관리소인 경우)총 글의 갯수 가져오는 함수
	int get_total_votes2(int address); // (category가 세대원, 동대표인 경우)총 글의 갯수 가져오는 함수
	void add_vote_ing(Vote vote); // 투표를 생성하는 함수
	Vote get_vote_by_id(int v_id); // 특정투표정보를 가져오는 함수
	void vote_ing(int v_id, int voteOption); // 투표결과를 투표에 반영하는 함수
	void vote_ing2(Vote_participant vote_participant); // 투표참여자를 기록하는 함수
	void delete_vote_ing(Integer v_id); // 투표를 삭제하는 함수
	boolean duplicate_vote_ing(int v_id, int house_id); // 중복투표를 확인하는 함수
	Vote_participant get_vote_result(int v_id, int house_id); // 투표 결과를 가져오는 함수
}
