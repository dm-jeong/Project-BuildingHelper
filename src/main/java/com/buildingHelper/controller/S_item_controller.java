package com.buildingHelper.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Share_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.buildingHelper.service.S_item_service;

@Controller
@RequestMapping("/shareitems")
public class S_item_controller 
{
	private final S_item_service s_item_service;

	@Autowired // 자동으로 주입
    public S_item_controller(S_item_service s_item_service) 
    {
        this.s_item_service = s_item_service; // 주입 받은 객체를 클래스 변수에 할당
    }

	@GetMapping
	public String request_s_item_list(Share_item share_item, Model model) // 모든 물품들을 가져오는 함수
	{
		List<Share_item> list = s_item_service.get_all_s_item_list(); // 모든 물품 정보를 가져옴
		model.addAttribute("s_item_list", list); // 모델에 s_item_list라는 이름으로 객체 추가
		
		Set<Map<String, Object>> categories = s_item_service.get_all_categories(); // 모든 카테고리 정보를 가져옴
	    model.addAttribute("categories", categories); // 모델에 categories라는 이름으로 객체 추가
	    // set : 중복을 허용하지 않음, Map : 키와 값으로 구성

		return "Share_items";
	}

	@GetMapping("/{s_category}")
	public String request_s_items_by_category(@PathVariable("s_category") String s_item_category, Model model) // 특정 카테고리로 이동하는 함수
	{
	    List<Share_item> s_items_by_category = s_item_service.get_s_item_list_by_category(s_item_category); // 카테고리에 해당하는 물품 정보를 가져옴
	    model.addAttribute("s_item_list",s_items_by_category); // 모델에 객체 추가
	    
	    Set<Map<String, Object>> categories = s_item_service.get_all_categories(); // 모든 카테고리 정보를 가져옴
	    model.addAttribute("categories", categories); // 모델에 객체 추가

	    return "Share_items";
	}
	
	@GetMapping("/shareitem")
	public String request_s_item_by_id(@RequestParam("id") Integer share_item_id, Model model) // 특정 물품페이지로 이동
	{
	    Share_item s_item_by_id = s_item_service.get_s_item_by_id(share_item_id); // 특정 물품의 정보를 가져옴
	    model.addAttribute("share_item", s_item_by_id); // 모델에 객체 추가

	    String s_item_category = s_item_by_id.getS_category(); // 특정 물품의 카테고리를 가져옴
	    List<Share_item> s_items_by_category = s_item_service.get_s_item_list_by_category(s_item_category); // 카테고리에 해당하는 물품 정보를 가져옴
	    
	    // 현재 아이템을 제외한 목록을 만드는 로직
	    List<Share_item> final_list = new ArrayList<Share_item>();
	    for (Share_item item : s_items_by_category) {
	        if (!item.getShare_item_id().equals(share_item_id)) {
	            final_list.add(item);
	        }
	    }
	    
	    model.addAttribute("final_list", final_list); // 모델에 객체 추가

	    return "Share_item";
	}
	
	@GetMapping("/add")
	public String request_add_s_item_form(@ModelAttribute("new_s_item") Share_item share_item) // 물품 등록 페이지로 이동
	{
		return "Add_s_item";
	}
	
	@PostMapping("/add")
	public String submit_add_new_s_item(@Valid @ModelAttribute("new_s_item") Share_item share_item, BindingResult result, @RequestParam("file") MultipartFile file, HttpServletRequest request) // 물품 등록하는 함수
	{
	    share_item.setS_condition("대여가능"); // 물품을 새로 등록할 때는 s_condition을 '대여가능'으로 설정
	    
        if(result.hasErrors()){
            return "Add_s_item";
        } // 오류가 있을 경우 다시 Add_s_item로 이동
        
        if (!file.isEmpty()) {
            String file_name = file.getOriginalFilename(); // 사용자가 업로드한 파일의 이름
            share_item.setS_file_name(file_name); // share_item 객체의 s_file_name을 업로드된 파일의 이름으로 설정
            try {
            	File image_file = new File(request.getSession().getServletContext().getRealPath("/resources/images/"), file_name); // File 객체 생성. 파일의 이름과 저장될 위치 전달
                file.transferTo(image_file); // 업로드된 파일을 지정된 위치에 저장
            } catch (IOException e) {
                e.printStackTrace(); // IOException이 발생하면 에러 메시지를 출력
            }
        }
		
		HttpSession session = request.getSession();
	    House house = (House)session.getAttribute("house");
	    int r_address_id = house.getAddress_id();
	    share_item.setS_address_id(r_address_id);
	    // 세션 정보를 활용하여 저장
		
		s_item_service.set_new_s_item(share_item); // 물품 등록  서비스 호출
		return "redirect:/shareitems";
	}
	
	@ModelAttribute
	public void addAttributes(Model model) // 특정 문자 모델에 저장
	{
		model.addAttribute("add_title","신규 물품 등록");
	}
	
	@GetMapping("/update")
	public String get_update_s_item_form(@ModelAttribute("update_s_item") Share_item share_item, @RequestParam("id") Integer share_item_id, Model model) // 물품 수정페이지로 이동
	{
		Share_item s_item_by_id = s_item_service.get_s_item_by_id(share_item_id); // 특정 물품 정보를 가져옴
		model.addAttribute("update_s_item", s_item_by_id); // 모델에 객체 추가
		return "S_update_form";
	}
	
	@PostMapping("/update")
	public String submit_update_s_item_form(@ModelAttribute("update_s_item") Share_item share_item, BindingResult result, @RequestParam("file") MultipartFile file, HttpServletRequest request) // 물품 수정하는 함수
	{
        if(share_item.isS_approval() == true)
		{
			share_item.setS_condition("대여불가");
		}
		else
		{
			share_item.setS_condition("대여가능");
		} // 승인 여부에 따라 컨디션이 바뀌게 설정
        
        if(result.hasErrors()){
            return "Share_items";
        } // 오류가 있을 경우 다시 Share_items로 이동
        
        if (!file.isEmpty()) {
            String file_name = file.getOriginalFilename(); // 사용자가 업로드한 파일의 이름
            share_item.setS_file_name(file_name); // share_item 객체의 s_file_name을 업로드된 파일의 이름으로 설정
            try {
                File image_file = new File(request.getSession().getServletContext().getRealPath("/resources/images/"), file_name); // File 객체 생성. 파일의 이름과 저장될 위치 전달
                file.transferTo(image_file); // 업로드된 파일을 지정된 위치에 저장
            } catch (IOException e) {
                e.printStackTrace(); // IOException이 발생하면 에러 메시지를 출력
            }
        }
        
	    s_item_service.set_update_s_item(share_item); // 물품 수정하는 서비스 호출
	    return "redirect:/shareitems";
	}
	
	@RequestMapping(value="/delete")
	public String get_delete_s_item_form(Model model, @RequestParam("id") Integer share_item_id) // 물품 삭제하는 함수
	{
		s_item_service.set_delete_s_item(share_item_id); // 물품 삭제하는 서비스 호출
		return "redirect:/shareitems";
	}

}
