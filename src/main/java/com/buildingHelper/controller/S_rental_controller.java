package com.buildingHelper.controller;

import java.time.LocalDate;
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

import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Share_item;
import com.buildingHelper.service.S_item_service;

@Controller
@RequestMapping("/shareitems/rental")
public class S_rental_controller 
{
	private final S_item_service s_item_service;

	@Autowired // 자동으로 주입
    public S_rental_controller(S_item_service s_item_service) 
    {
        this.s_item_service = s_item_service; // 주입 받은 객체를 클래스 변수에 할당
    }

    @GetMapping("/request/{shareItemId}")
    public String show_request_page(@PathVariable Integer shareItemId, Model model) // 요청 페이지로 이동하는 함수
    {
	    Share_item shareItem = s_item_service.get_s_item_by_id(shareItemId); // 특정 물품 정보를 가져옴
	    LocalDate today = LocalDate.now(); // 오늘 날짜 설정
	
	    model.addAttribute("today", today); // 모델에 추가
	    model.addAttribute("shareItem", shareItem); // 모델에 추가
	
	    return "S_request";
    }

    @PostMapping("/request/{shareItemId}")
    public String submit_request(@PathVariable Integer shareItemId,  HttpServletRequest request) // 요청을 제출하는 함수
    {
    	Share_item shareItem = s_item_service.get_s_item_by_id(shareItemId); // 특정 물품 정보를 가져옴
    	
        HttpSession session = request.getSession();
        House house = (House)session.getAttribute("house");
        String resident_id = house.getHouse_dong() + house.getHouse_hosu();
        shareItem.setResident_id(resident_id);
        // 세션 정보를 활용하여 저장
        
        shareItem.setCurrentTime(); // 현재시각 설정
        shareItem.setS_return_time(request.getParameter("s_return_time")); // 반납시각 설정 
        shareItem.setS_request(true); // request를 true로 변경
        
        s_item_service.set_update_s_request(shareItem); // 물품 신청 서비스 호출
        
        return "redirect:/shareitems/rental/complete?shareItemId=" + shareItemId;
    }




    @GetMapping("/complete")
    public String show_complete_page(Integer shareItemId, Model model) // 신청 완료 페이지로 이동
    {
        Share_item shareItem = s_item_service.get_s_item_by_id(shareItemId); // 특정 물품 정보를 가져옴

        model.addAttribute("shareItem", shareItem); // 모델에 저장

        return "S_complete";
    }

    

    @GetMapping("/confirm")
    public String show_confirm_page(Model model) { // 신청 확인 페이지로 이동
        List<Share_item> s_items_request = s_item_service.get_s_items_request(); // rerquest가 true인 물품 정보 가져옴
        
        model.addAttribute("s_items_request", s_items_request); // 모델에 저장
        
        return "S_confirm";
    }
    
    @GetMapping("/approve")
    public String s_move_approve(Model model) { // 승인 페이지로 이동
        List<Share_item> s_items_request = s_item_service.get_s_items_request(); // rerquest가 true인 물품 정보 가져옴

        model.addAttribute("s_items_request", s_items_request); // 모델에 저장
        
        return "S_approve";
    }

    @PostMapping("/approve/{shareItemId}")
    public String s_approve(@PathVariable Integer shareItemId) { // 신청을 승인하는 함수
        Share_item ShareItem = s_item_service.get_s_item_by_id(shareItemId); // 특정 물품 정보 가져오는 함수

        ShareItem.setS_approval(true); // approval을 true로 변경
        ShareItem.setS_request(true); // 변경되지 않게 초기화 차원

        s_item_service.update_s_item(ShareItem); // request와 approval를 변경하는 서비스 호출
        
        return "redirect:/shareitems/rental/approve";
    }

    @PostMapping("/return/{shareItemId}")
    public String returnItem(@PathVariable Integer shareItemId) { // 반납하는 함수
        Share_item returnItem = new Share_item(); // 새로운 Share_item 객체를 생성(모든 값을 초기화)
        returnItem.setShare_item_id(shareItemId); // Share_item 객체에 shareItemId를 설정. 반납 처리할 물품을 식별하기 위해
 
        s_item_service.update_s_item(returnItem); // request와 approve를 변경하는 서비스 호출
        
        return "redirect:/shareitems/rental/approve";
    }


}
