package com.buildingHelper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Page_maker;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.domain.Vote;
import com.buildingHelper.domain.Vote_participant;
import com.buildingHelper.service.Vote_service;

@Controller
@RequestMapping("/votes")
public class Vote_controller 
{
    private final Vote_service vote_service; // 밑에서 이용할 객체 선언

    @Autowired // 자동으로 주입
    public Vote_controller(Vote_service vote_service) 
    {
        this.vote_service = vote_service; // 주입 받은 객체를 클래스 변수에 할당
    }

    // 객체를 선언하고 주입한 이유는 '의존성 주입'을 하기위해. 의존성을 가지면서도 독립성을 유지할수가 있다.
    
    @GetMapping
    public String get_votes(@RequestParam(value = "page1", defaultValue = "1") int page1,
                            @RequestParam(value = "page2", defaultValue = "1") int page2,
                            Model model, HttpServletRequest request) { // 위아래 게시판 각자 다른 투표 목록을 가져오는 함수
        Criteria cri1 = new Criteria();
        cri1.setPage(page1);
        cri1.setPer_num(5); // 한 페이지에 보여줄 글의 수

        Criteria cri2 = new Criteria();
        cri2.setPage(page2);
        cri2.setPer_num(5); // 한 페이지에 보여줄 글의 수

        // Criteria 객체를 생성하고 페이지 번호를 설정

        HttpSession session = request.getSession();
        House house = (House)session.getAttribute("house");
        int address = house.getAddress_id();
        
        List<Vote> votes1 = vote_service.get_all_votes_and_page1(address, cri1);
        List<Vote> votes2 = vote_service.get_all_votes_and_page2(address, cri2);
        // 각각의 게시판에 대한 투표 목록과 페이지 정보를 서비스에서 가져옴

        Page_maker pageMaker1 = new Page_maker();
        pageMaker1.setCri(cri1);
        pageMaker1.setTotal_count(vote_service.get_total_votes1(address));

        Page_maker pageMaker2 = new Page_maker();
        pageMaker2.setCri(cri2);
        pageMaker2.setTotal_count(vote_service.get_total_votes2(address));
        // 페이징 정보를 담당하는 객체를 생성하고 설정

        model.addAttribute("votes1", votes1);
        model.addAttribute("pageMaker1", pageMaker1);
        model.addAttribute("votes2", votes2);
        model.addAttribute("pageMaker2", pageMaker2);
        // 모델에 데이터를 추가하여 뷰에 전달

        return "Votes";
    }
    
    @GetMapping("/add")
    public String show_vote_add() { // 투표등록페이지로 이동하는 함수
        return "Vote_add"; // Vote_add.jsp 반환
    }

    @PostMapping("/add") 
    public String add_vote(Vote vote, HttpServletRequest request) { // 투표를 등록하는 함수
    	vote.setV_title(request.getParameter("v_title")); 
    	vote.setCurrentTime();
    	vote.setV_end_time(request.getParameter("v_end_time")); 
    	// 입력 받은 정보들 저장
    	
    	String[] options = new String[20];
        for (int i = 0; i < 20; i++) {
            String option = request.getParameter("v_option" + (i + 1));
            if (option != null && !option.isEmpty()) {
                options[i] = option;
            }
        }
        vote.setOptions(options); 
        // 투표옵션을 입력받은만큼 저장
        
        HttpSession session = request.getSession();
        Resident resident = (Resident)session.getAttribute("resident");
        House house = (House)session.getAttribute("house");
        vote.setV_creator_id(resident.getR_login_id());
        vote.setV_category(resident.getR_category());
        vote.setV_address_id(house.getAddress_id());
        vote.setV_house_dong(house.getHouse_dong());
        // 세션 정보를 활용하여 저장
        
        vote_service.add_vote_ing(vote); // 투표를 생성하는 함수 실행

        return "redirect:/votes"; // 원래 페이지로 리다이렉트
    }
    
    @GetMapping("/{v_id}")
    public String get_vote(@PathVariable int v_id, Model model, HttpServletRequest request) { // 특정 투표정보를 가져오는 함수
        Vote vote = vote_service.get_vote_by_id(v_id); // v_id에 해당하는 특정 투표정보를 가져와 vote객체에 선언
        
        HttpSession session = request.getSession();
        House house = (House)session.getAttribute("house");
        boolean duplicate_vote = vote_service.duplicate_vote_ing(v_id, house.getHouse_id());
        // 세션정보의 house_id의 중복투표 여부를 확인하는 함수 실행하여 저장
        
        Vote_participant vote_participant = vote_service.get_vote_result(v_id, house.getHouse_id());
        // 세션정보의 house_id의 투표결과를 가져오는 함수 실행하여 저장
        
        model.addAttribute("vote_participant", vote_participant);
        model.addAttribute("vote", vote);
        model.addAttribute("duplicate_vote", duplicate_vote);
        // 가져온 정보들을 모델에 저장
        return "Vote"; // Vote.jsp 반환
    }

    @PostMapping("/{v_id}")
    public String vote_ing(Vote_participant vote_participant, @PathVariable int v_id, @RequestParam int voteOption, HttpServletRequest request) { // 투표에 참여하는 함수
    	HttpSession session = request.getSession();
    	House house = (House)session.getAttribute("house");
    	vote_participant.setVp_house_id(house.getHouse_id());
    	vote_participant.setVp_v_id(v_id);
    	vote_participant.setVp_result(request.getParameter("voteOption"));
    	// 세션정보에서 가져온 값과 입력받은 값을 저장
    	
        vote_service.vote_ing(v_id, voteOption); // 투표결과를 투표에 반영하는 함수
        
        vote_service.vote_ing2(vote_participant); // 투표참여자를 기록하는 함수
        return "redirect:/votes/" + v_id; // 투표 결과페이지로 리다이렉트
    }
    
    @RequestMapping(value="/delete")
    public String delete_vote(@RequestParam("v_id") Integer v_id) { // 투표를 삭제하는 함수
        vote_service.delete_vote_ing(v_id); // 투표를 삭제하는 함수 실행
        return "redirect:/votes"; // 원래 페이지로 리다이렉트
    }

}

