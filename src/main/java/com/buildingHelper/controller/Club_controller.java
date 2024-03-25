package com.buildingHelper.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.buildingHelper.domain.Club;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.service.Club_service;

@Controller
@RequestMapping("/clubs")
public class Club_controller 
{

    private final Club_service club_service; // 밑에서 이용할 객체 선언

    @Autowired // 자동으로 주입
    public Club_controller(Club_service club_service) 
    {
        this.club_service = club_service; // 주입 받은 객체를 클래스 변수에 할당
    }

    @GetMapping
    public String get_clubs(Model model) // 클럽 목록 조회 함수
    {
        List<Club> clubs = club_service.get_all_clubs(); // 모든 클럽 정보를 가져와 모델에 저장
        model.addAttribute("posts", clubs);
        return "Clubs"; // Clubs.jsp로 반환
    }
    
    @GetMapping("/add")
    public String add_club_move(Model model)  // 클럽 추가페이지로 이동하는 함수
    {
        model.addAttribute("club", new Club()); // 빈 Club 객체를 모델에 추가
        return "Club_add"; // Club_add.jsp로 반환
    }

    @PostMapping("/add")
    public String add_club(@Valid @ModelAttribute("club") Club club, BindingResult result, @RequestParam("c_file") MultipartFile file, HttpServletRequest request) { // 클럽 추가 함수
        if(result.hasErrors()){
            return "Club_add";
        } // 오류가 있을 경우 다시 Club_add.jsp로 이동
        
        if (!file.isEmpty()) { // 클럽 이미지 파일이 첨부된 경우
    		String save = request.getSession().getServletContext().getRealPath("/resources/images/"); 
    		// 이미지를 저장할 위치를 지정
            String file_name = file.getOriginalFilename(); // 사용자가 업로드한 파일의 이름
            club.setC_file_name(file_name); // 클럽 객체의 c_file_name을 업로드된 파일의 이름으로 설정
            try {
                File image_file = new File(save, file_name); // File 객체 생성. 파일의 이름과 저장될 위치 전달
                file.transferTo(image_file); // 업로드된 파일을 지정된 위치에 저장
            } catch (IOException e) {
                e.printStackTrace(); // IOException이 발생하면 에러 메시지를 출력
            }
        }
        
        club.setCurrentTime(); // 작성시간 설정

        HttpSession session = request.getSession();
        Resident resident = (Resident)session.getAttribute("resident");
        House house = (House)session.getAttribute("house");
        club.setC_writer_id(resident.getR_login_id());
        club.setC_address_id(house.getAddress_id());
        // 세션 정보를 활용하여 저장

        club_service.add_club_ing(club); // 클럽 추가 서비스 실행
        return "redirect:/clubs"; // 원래 페이지로 리다이렉트
    }

    @GetMapping("/update")
    public String get_update_club_form(@RequestParam("id") int c_id, Model model) { // 클럽 수정 함수로 이동
        Club club_by_id = club_service.get_club_by_id(c_id); // c_id에 해당하는 특정 클럽정보를 가져옴
        model.addAttribute("update_club", club_by_id); // 모델에 저장
        return "Club_update_form"; // Club_update_form.jsp로 반환
    }
    
    @PostMapping("/update")
    public String submit_update_club_form(@ModelAttribute("update_club") Club club, @RequestParam("c_file") MultipartFile file, HttpServletRequest request) { // 클럽을 수정하는 함수
    	if (club.getC_id() == null) {
    	    return "redirect:/clubs";
    	} // 클럽 ID가 null이면 원래 페이지로 리다이렉트
    	
        if (!file.isEmpty()) { // 클럽 이미지 파일이 첨부된 경우
            String file_name = file.getOriginalFilename(); // 사용자가 업로드한 파일의 이름
            club.setC_file_name(file_name); // 클럽 객체의 c_file_name을 업로드된 파일의 이름으로 설정
            String save = request.getSession().getServletContext().getRealPath("/resources/images/");  // 이미지를 저장할 위치를 지정
            try {
                File image_file = new File(save, file_name); // File 객체 생성. 파일의 이름과 저장될 위치 전달
                file.transferTo(image_file); // 업로드된 파일을 지정된 위치에 저장
            } catch (IOException e) {
                e.printStackTrace(); // IOException이 발생하면 에러 메시지를 출력
            }
        }

        club_service.update_club_ing(club); // 클럽 수정 서비스 실행
        return "redirect:/clubs"; // 원래 페이지로 리다이렉트
    }
    
    @RequestMapping(value="/delete")
    public String delete_club(@RequestParam("c_id") Integer c_id) { // 클럽 삭제 함수
        club_service.delete_club_ing(c_id); // 클럽 삭제 서비스 실행
        return "redirect:/clubs"; // 원래 페이지로 리다이렉트
    }

}