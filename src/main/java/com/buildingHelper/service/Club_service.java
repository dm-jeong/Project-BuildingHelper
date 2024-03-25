package com.buildingHelper.service;

import com.buildingHelper.domain.Club;
import java.util.List;

public interface Club_service // 서비스 인터페이스 정의
{
    List<Club> get_all_clubs(); // 모든 클럽들을 가져오는 함수
    void add_club_ing(Club club); // 클럽을 추가하는 함수
    void delete_club_ing(Integer c_id); // 클럽을 삭제하는 함수
    Club get_club_by_id(Integer c_id); // 특정클럽정보를 가져오는 함수
    void update_club_ing(Club club); // 클럽정보를 수정하는 함수
}
