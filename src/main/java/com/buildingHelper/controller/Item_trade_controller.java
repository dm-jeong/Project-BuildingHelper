package com.buildingHelper.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.Item_trade;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.service.Item_trade_service;

@Controller
@RequestMapping("/itemtrade")
public class Item_trade_controller {
	

	@Autowired
	private Item_trade_service item_trade_service;
	

	// 개인 거래 게시글 전부 조회 - 세션의 r_login_id 와 db의 r_login_id 대조 하기위해 session 을 파라미터로 사용
	@GetMapping
	public String request_item_trade_list(Model model, HttpSession session) {
		System.out.println("item_trade로 가는 컨트롤러입니다.");
		List<Item_trade> list = item_trade_service.get_all_item_trade_list(session); // db로 감 - 서비스 		

		
		item_trade_service.listed_set_time(list); // 게시글 수정 시간 가져와서 전처리(몇 일전, 몇 시간전) list 버전
		
		model.addAttribute("item_trade",list);
		return "item_trade_main"; 
	}
	
	
	// add : 개인 거래 게시글 등록 
	@GetMapping("/add")
	public String request_add_item_trade_form(@ModelAttribute Item_trade item_trade, Model model) {
		System.out.println("ADD 폼으로 보내는 컨트롤러입니다(GET).");		  
	    return "add_item_trade";  // add 하는 view로 이동 
	}
	
	@PostMapping("/add")
	public String submit_add_new_item_trade(@ModelAttribute Item_trade item_trade, BindingResult result, MultipartHttpServletRequest request, HttpSession session) {
		System.out.println("ADD 폼 입력 다 하고 난 후 컨트롤러입니다(POST).");
		
		System.out.println("파일네임"+item_trade.getFile_name());

		
		
		if(result.hasErrors()) return "add_item_trade";    // 유효성 검사
		
		
		// 이미지파일 이름 설정 
		MultipartFile img = item_trade.getImg_name();    // img_name 가져옴	
		String saveName = img.getOriginalFilename();  // img_name의 원본 파일 이름 가져옴
		String save = request.getSession().getServletContext().getRealPath("/resources/images/");  // 저장 경로 설정
		File saveFile = new File(save,saveName);      // 경로 + 파일이름 합침
		
	
		// 이미지 파일이 비어있지 않은 경우(선택한 파일이 있는 경우), 해당 이미지를 실제 파일로 저장
		if(img != null&& !img.isEmpty()) {
			try {
				img.transferTo(saveFile);   // transferto 메서드를 사용하여 파일을 저장
				item_trade.setFile_name(saveName);   //  저장된 파일의 이름을 fime_name에 설정 
				System.out.println("파일네임"+item_trade.getFile_name());
			}catch(Exception e) {
				throw new RuntimeException("도서 이미지 업로드가 실패하였습니다",e);
			}
		}
		
		item_trade_service.set_new_item_trade(item_trade, session);   // 게시글 등록(add) 하는 repository 설정 
		return "redirect:/itemtrade";
	}
	
	
	
	@GetMapping("/trade")
	public String request_trade_id(@RequestParam("id") int item_trade_id, Model model, HttpSession session) {
		System.out.println("아파트 입주민 거래 게시판 상세 정보 ");
		
		// id 해당하는 dto
		Item_trade item_trade_by_id = item_trade_service.get_item_trade_by_id(item_trade_id);
		model.addAttribute("item_trade_by_id",item_trade_by_id);
		System.out.println("어드레스아이디"+item_trade_by_id.getAddress_id());

		// 글 작성 시간 yyMMdd 형식 잡아주기
	    String formatted_date = item_trade_service.set_formatted_time(item_trade_by_id);
		model.addAttribute("formatted_date",formatted_date);
		
		// 몇 일 전, 몇 시간 전  
	    String time_diff = item_trade_service.set_time(item_trade_by_id);
	    model.addAttribute("time_diff", time_diff); 
	    
	    // 더보기(include)
		List<Item_trade> list = item_trade_service.get_all_item_trade_list(session);
		model.addAttribute("item_trade",list);
	
		// 세션의 r_login_id 와 db의 r_login_id 확인
		Resident r_login_id = (Resident) session.getAttribute("resident");  // 세션에서 r_login_id 가져옴
		String session_r_login_id = r_login_id.getR_login_id(); 
		
		String db_r_login_id = item_trade_by_id.getR_login_id();  // db에 item_trade 테이블의 r_login_id 가져옴
		// 두 r_login_id 일치 확인 : 수정/삭제 버튼 표시 여부
		boolean is_owner = session_r_login_id != null && session_r_login_id.equals(db_r_login_id);  
		model.addAttribute("is_owner",is_owner);
		
		return "item_trade";
	}
	
	
	// update : 수정
	@GetMapping("/update")
	public String get_update_item_trade_form(@ModelAttribute("update") Item_trade item_trade, @RequestParam("id") int item_trade_id, Model model) {
		System.out.println("수정컨트롤러");
		Item_trade it_by_id = item_trade_service.get_item_trade_by_id(item_trade_id);  // 수정할 페이지(view)에 보여줄 기존 데이터 가져오기
		model.addAttribute("item_trade", it_by_id);
		return "update_item_trade";
	}
	
	
	@PostMapping("/update")
	public String submit_update_item_trade_form(@ModelAttribute("update") Item_trade item_trade, HttpServletRequest request) {
		System.out.println("업데이트 제출 컨트롤러");
//		System.out.println("수정작성후"+item_trade);
//		
//		System.out.println("수정작성후 - Item_trade ID: " + item_trade.getItem_trade_id());
//		System.out.println("수정작성후 - Title: " + item_trade.getTitle());
//		System.out.println("수정작성후 - Category: " + item_trade.getCategory());



		// 이미지 처리(add 떄와 동일)
		MultipartFile img_name = item_trade.getImg_name();   //  dto에서 img_name 가져옴
		String save = request.getSession().getServletContext().getRealPath("/resources/images/");  

		if(img_name != null && !img_name.isEmpty()) {
			try {
				String fname = img_name.getOriginalFilename();
				img_name.transferTo(new File(save + fname));
				item_trade.setFile_name(fname);
				System.out.println("getRealPath부분입니다");
			}catch(Exception e){
				throw new RuntimeException("Book Image saving failed", e);
			}
		}


		item_trade_service.set_update_item_trade(item_trade);


		return "redirect:/itemtrade";
	}
	
	
	
	
	// 삭제 컨트롤러 : hiddenmapping 사용 
	@DeleteMapping(value="/delete/{id}")
	public String delete_item_trade(Model model, @PathVariable("id") String item_trade_id ) {
		System.out.println("삭제컨트롤러");
		item_trade_service.set_delete_item_trade(item_trade_id);
		return "redirect:/itemtrade";
	}
	
	
	
}
