package com.buildingHelper.service;

import com.buildingHelper.domain.Club;
import com.buildingHelper.repository.Club_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Club_service_impl implements Club_service { // 서비스 인터페이스 구현
    
    private final Club_repository club_repository; // 밑에서 이용할 객체 선언

    @Autowired // 자동으로 주입
    public Club_service_impl(Club_repository club_repository) {
        this.club_repository = club_repository; // 주입 받은 객체를 클래스 변수에 할당
    }

    // 아래 함수들은 Club_service 인터페이스에서 정의한 함수들의 구현체
    // 각 함수는 Club_repository의 함수를 호출하여 수행   
    @Override
    public List<Club> get_all_clubs() {
        return club_repository.get_all_clubs();
    }
    
    @Override
    public void add_club_ing(Club club) {
        club_repository.add_club_ing(club);
    }
    
    public void delete_club_ing(Integer c_id) {
        club_repository.delete_club_ing(c_id);
    }
    
    @Override
    public Club get_club_by_id(Integer c_id) {
        return club_repository.get_club_by_id(c_id);
    }
    
    @Override
    public void update_club_ing(Club club) {
        club_repository.update_club_ing(club);
    }
}
