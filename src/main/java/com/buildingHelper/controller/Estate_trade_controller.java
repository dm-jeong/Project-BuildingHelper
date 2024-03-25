package com.buildingHelper.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Estate_trade;
import com.buildingHelper.domain.Page_maker;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.service.Estate_trade_service;

@Controller
@RequestMapping("/estate")
public class Estate_trade_controller {
	
	@Autowired
	private Estate_trade_service estate_trade_service;
	
	// api_key 프로퍼티 - api_key 암호화
	@Value("${kakao_api_key}")
	private String kakao_api_key;
	
	// 게시글 전체 조회
	@GetMapping
	public String estate_list(Model model, HttpSession session, Criteria cri) {		
        // 게시글 전체 조회 
        List<Estate_trade> list = estate_trade_service.all_estate_list(cri);  // 전체 조회(criteria 적용)
        int total_count = estate_trade_service.get_total_count(); // 페이징 처리를 위한 게시글 전체 개수 가져와서 저장 
        System.out.println("total_count:"+total_count);
        
        // 주소 문자열 처리 
        
        
        // 페이징 처리 
        Page_maker page_maker = new Page_maker();
        page_maker.setCri(cri);
        page_maker.setTotal_count(total_count);
        
//        estate_trade_service.listed_update_time(list);  // 글 수정 시간 (몇일전,몇시간전) 계산해서 dto에 전달 - 현재 사용 하지 않음
        estate_trade_service.listed_set_time(list);  // 글 작성시간(reg_date)을 몇 일전, 몇 시간전 표시한 걸 list에 사용
        estate_trade_service.listed_address_api(list);  // 주소 문자열처리( 광역시/도, 시/군/구, 읍/면/동 3개만표시) 해서 dto에 전달
        
        
        model.addAttribute("page_maker", page_maker);
        model.addAttribute("estate_trade",list);   
		return "estate_trade_main";
	}
	
	
	// add : 게시글 등록 폼으로 이동
	@GetMapping("/add")
	public String add_estate_trade(@ModelAttribute Estate_trade estate_trade, Model model) {
		System.out.println("부동산거래 ADD 폼으로 보내는 컨트롤러입니다(GET).");
		// api 숨기기
		model.addAttribute("api_key", kakao_api_key);
		
		return "add_estate_trade";
		
	}
	
	
	// 
	@PostMapping("/add")
	public String submit_estate_trade(@ModelAttribute Estate_trade estate_trade, BindingResult result, HttpServletRequest request, HttpSession session) {
		System.out.println("ADD 폼 입력 다 하고 난 후 컨트롤러입니다(POST).");
		
		if(result.hasErrors()) return "add_estate_trade";

		MultipartFile img = estate_trade.getImg_name();  // dto에서 img_name 가져옴
		String saveName = img.getOriginalFilename();  // img_name의 원본파일이름 가져옴
		String save = request.getSession().getServletContext().getRealPath("/resources/images/"); // 경로 설정 
		
		File saveFile = new File(save,saveName);  // 실제 저장할 경로를 지정 
		
		if(img != null&& !img.isEmpty()) {
			try {
				img.transferTo(saveFile);  // 파일을 저장
				estate_trade.setFile_name(saveName);  // 파일이름을 dto 에저장
			}catch(Exception e) {
				throw new RuntimeException("이미지 업로드가 실패하였습니다",e);
			}
		}
		
		estate_trade_service.set_new_estate_trade(estate_trade, session);  // db에 update실행
		return "redirect:/estate";
	}
	
	
	@GetMapping("/trade")
	public String request_trade_id(@RequestParam("id") int estate_trade_id, Model model, HttpSession session, Criteria cri) {
		// api 숨기기
		model.addAttribute("api_key", kakao_api_key);
		
		// id 해당하는 dto
		Estate_trade estate_trade_by_id = estate_trade_service.get_estate_trade_by_id(estate_trade_id);
		model.addAttribute("estate_trade_by_id",estate_trade_by_id);
		

		// 글 작성 시간 yyMMdd 형식 잡아주기
	    String formatted_date = estate_trade_service.set_formatted_time(estate_trade_by_id);
		model.addAttribute("formatted_date",formatted_date);
		
		// 몇 일 전, 몇 시간 전  
	    String time_diff = estate_trade_service.set_time(estate_trade_by_id);
	    model.addAttribute("time_diff", time_diff); 
	    
	    // include용
		List<Estate_trade> list2 = estate_trade_service.all_estate_list(cri);
		model.addAttribute("estate_trade",list2);

	    // 세션의 r_login_id와 db의 r_login_id 확인
	    Resident r_login_id = (Resident) session.getAttribute("resident");

	    // 세션이 없는 경우 null을 사용
	    String session_r_login_id = (r_login_id != null) ? r_login_id.getR_login_id() : null;
	    String db_r_login_id = estate_trade_by_id.getR_login_id();

	    // 두 r_login_id 일치 확인 : 수정/삭제 버튼 표시 여부
	    boolean is_owner = session_r_login_id != null && session_r_login_id.equals(db_r_login_id);
	    System.out.println(is_owner);
	    model.addAttribute("is_owner", is_owner);
		
				
		
		return "estate_trade";
	}
	
	
	// update: 수정 
	@GetMapping("/update")
	public String get_update_estate_trade_form(@ModelAttribute("update") Estate_trade item_trade, @RequestParam("id") int estate_trade_id, Model model) {
		System.out.println("ESTATE 수정컨트롤러");
		// api 숨기기
		model.addAttribute("api_key", kakao_api_key);
		
		Estate_trade estate_by_id = estate_trade_service.get_estate_trade_by_id(estate_trade_id);
		model.addAttribute("estate_trade", estate_by_id);
		return "update_estate_trade";
	}
	
	
	
	
	@PostMapping("/update")
	public String submit_update_item_trade_form(@ModelAttribute("update") Estate_trade estate_trade, HttpServletRequest request) {
		System.out.println("업데이트 제출 컨트롤러");

		
		
		// img 파일 처리 : add와 동일
		MultipartFile img_name = estate_trade.getImg_name();
		String save = request.getSession().getServletContext().getRealPath("/resources/images/");
		
		if(img_name != null && !img_name.isEmpty()) {
			try {
				String fname = img_name.getOriginalFilename();
				img_name.transferTo(new File(save + fname));
				estate_trade.setFile_name(fname);
				System.out.println("getRealPath부분입니다");
			}catch(Exception e){
				throw new RuntimeException("Book Image saving failed", e);
			}
		}
		
		
		estate_trade_service.set_update_estate_trade(estate_trade);
		
		
		return "redirect:/estate";
	}
	
	
	
	
	// 삭제 
	@DeleteMapping(value="/delete/{id}")
	public String delete_estate_trade(Model model, @PathVariable("id") int estate_trade_id) {
		System.out.println("삭제컨트롤러");
		estate_trade_service.set_delete_estate_trade(estate_trade_id);
		return "redirect:/estate";
	}
	

	
}

