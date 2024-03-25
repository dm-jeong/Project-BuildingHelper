package com.buildingHelper.repository;

import java.util.List;

import com.buildingHelper.domain.Club_board;
import com.buildingHelper.domain.Club_reply;

public interface Cb_repository // 레포지토리 인터페이스 정의
{
	List<Club_board> get_posts_by_c_id_and_offset(Integer c_id, int offset, int limit); // c_id에 해당하는 특정 게시판 정보 가져오는 함수 + 페이지 정보 가져옴
	int get_total_posts_by_c_id(Integer c_id); // 총 글의 갯수 정보 가져오는 함수
	void add_post_ing(Club_board new_post); // 글 등록하는 함수
	Club_board get_post_by_id(Integer id); // 특정 게시글 정보 가져오는 함수
	void increase_view_count(Integer id); // 조회수 증가 함수
	void delete_post(Integer cb_id); // 게시글 삭제 함수
	void update_post(Club_board updated_post); // 게시글 수정 함수
	void post_reply(Club_reply club_reply); // 작성된 댓글 저장 함수
	List<Club_reply> get_reply(Integer id); // cb_id(cr_cb_id와 같음)에 해당하는 댓글리스트 불러오는 함수
	void delete_reply(Integer cr_id); // cr_id에 해당하는 댓글 삭제하는 함수
}
