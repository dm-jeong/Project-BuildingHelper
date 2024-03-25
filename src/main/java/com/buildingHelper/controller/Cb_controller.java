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
import com.buildingHelper.domain.Club_board;
import com.buildingHelper.domain.Club_reply;
import com.buildingHelper.domain.Criteria;
import com.buildingHelper.domain.Page_maker;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.service.Cb_service;
import com.buildingHelper.service.Club_service;

@Controller
@RequestMapping("/clubs/clubboard")
public class Cb_controller {

    private final Cb_service cb_service; // 밑에서 이용할 객체 선언
    
    @Autowired
    private Club_service club_service;

    @Autowired // 자동으로 주입
    public Cb_controller(Cb_service cb_service) 
    {
        this.cb_service = cb_service; // 주입 받은 객체를 클래스 변수에 할당
    }
    
    @GetMapping
    public String list(@RequestParam("c_id") Integer c_id, @RequestParam(value = "page", defaultValue = "1") int page, Model model, HttpServletRequest request) { // 게시판 목록을 가져오는 함수
    	Criteria cri = new Criteria();
    	cri.setPage(page);
        cri.setPer_num(10); // 한 페이지에 보여줄 글의 수
        
    	List<Club_board> posts = cb_service.get_posts_by_c_id_and_page(c_id, cri); // c_id에 해당하는 특정 클럽 게시판 정보를 가져옴 + 페이지 정보 가져옴
        model.addAttribute("posts", posts); // 모델에 posts라는 이름으로 객체 추가
        
        Page_maker pageMaker = new Page_maker();
        pageMaker.setCri(cri);
        pageMaker.setTotal_count(cb_service.get_total_posts_by_c_id(c_id));
        model.addAttribute("pageMaker", pageMaker);
        // 총 글의 갯수 정보 가져와서 모델에 저장
        
        HttpSession session = request.getSession();
        session.setAttribute("c_id", c_id); // 세션에 c_id 저장
        Club club_by_id = club_service.get_club_by_id(c_id); // c_id에 해당하는 특정 클럽정보를 가져옴
        model.addAttribute("club_by_id", club_by_id); // 모델에 저장

        return "Cb_list"; // Cb_list.jsp로 반환
    }
    
    @GetMapping("/addpost")
    public String add_post_page(@RequestParam("c_id") int c_id, Model model) { // 글 등록 페이지로 이동하는 함수
    	model.addAttribute("c_id",c_id);
        return "Cb_add"; // Cb_add.jsp로 반환
    }
    
    @PostMapping("/addpost")
    public String add_post(@Valid @ModelAttribute("post") Club_board new_post, BindingResult result, @RequestParam("file") MultipartFile file, HttpServletRequest request) { // 글 등록하는 함수
        if(result.hasErrors()){
            return "Cb_add";
        } // 오류가 있을 경우 다시 Cb_add.jsp로 이동
        
        if (!file.isEmpty()) {
            String file_name = file.getOriginalFilename(); // 사용자가 업로드한 파일의 이름
            new_post.setCb_file_name(file_name); // 클럽 객체의 cb_file_name을 업로드된 파일의 이름으로 설정
            try {
            	File image_file = new File(request.getSession().getServletContext().getRealPath("/resources/images/"), file_name); // File 객체 생성. 파일의 이름과 저장될 위치 전달
                file.transferTo(image_file); // 업로드된 파일을 지정된 위치에 저장
            } catch (IOException e) {
                e.printStackTrace(); // IOException이 발생하면 에러 메시지를 출력
            }
        }
        
        new_post.setCb_title(request.getParameter("title"));
        new_post.setCb_description(request.getParameter("description"));
        // 입력 받은 제목, 내용 저장
        new_post.setCurrentTime(); // 작성시간 설정

        HttpSession session = request.getSession();
        Resident resident = (Resident)session.getAttribute("resident");
        new_post.setCb_writer_id(resident.getR_login_id());
        Integer c_id = (Integer) session.getAttribute("c_id");
        new_post.setCb_c_id(c_id);
        // 세션 정보를 활용하여 저장

        new_post.setCb_count(0);  // 조회수 초기화
        
        cb_service.add_post_ing(new_post); // 글 등록 서비스 호출

        return "redirect:/clubs/clubboard?c_id="+c_id; // 게시판으로 이동
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") Integer id, @RequestParam(value = "page", defaultValue = "1") int page, Model model, HttpServletRequest request) { // 작성글로 이동하는 함수
        cb_service.increase_view_count(id); // 조회수 증가 서비스 호출
        Club_board post = cb_service.get_post_by_id(id); // 특정 글 정보 가져옴
        model.addAttribute("post", post); // 모델에 post라는 이름으로 객체 추가
        
        HttpSession session = request.getSession();
        int c_id = (int)session.getAttribute("c_id");
        Club club_by_id = club_service.get_club_by_id(c_id); // c_id에 해당하는 특정 클럽정보를 가져옴
        model.addAttribute("club_by_id", club_by_id); // 모델에 저장
        
        Criteria cri = new Criteria();
        cri.setPage(page);
        cri.setPer_num(10); // 한 페이지에 보여줄 글의 수
        
        List<Club_board> posts = cb_service.get_posts_by_c_id_and_page(c_id, cri); // c_id에 해당하는 특정 클럽 게시판 정보를 가져옴 + 페이지 정보 가져옴
        model.addAttribute("posts", posts); // 모델에 posts라는 이름으로 객체 추가
        
        Page_maker pageMaker = new Page_maker();
        pageMaker.setCri(cri);
        pageMaker.setTotal_count(cb_service.get_total_posts_by_c_id(c_id));
        model.addAttribute("pageMaker", pageMaker);
        // 총 글의 갯수 정보 가져와서 모델에 저장
        
        session.setAttribute("cb_id",id); // 아래 reply, reply_delete 함수에서 사용할 예정
        
        List<Club_reply> reply_list = cb_service.get_reply(id); // 뷰 a태그 리퀘스트파람으로 받아온 cb_id(cr_cb_id와 같음)에 해당하는 댓글리스트 불러옴
        model.addAttribute("reply_list",reply_list); // 댓글리스트를 view에서 사용할 모델에 저장
        // 댓글 리스트를 불러오는 로직

        return "Cb_view"; // Cb_view.jsp로 이동
    }
    
    @GetMapping("/edit")
    public String edit_page(@RequestParam("cb_id") Integer cb_id, Model model) { // 작성글 수정페이지로 이동하는 함수
        Club_board post = cb_service.get_post_by_id(cb_id); // 특정 글 정보 가져옴
        model.addAttribute("post", post); // 모델에 post라는 이름으로 객체 추가

        return "Cb_update_form"; // Cb_update_form.jsp로 이동
    }

    @PostMapping("/edit")
    public String edit_post(@Valid @ModelAttribute("post") Club_board updated_post, BindingResult result, @RequestParam("file") MultipartFile file, HttpServletRequest request) { // 작성글 수정하는 함수
        if(result.hasErrors()){
            return "Cb_update_form";
        } // 오류가 있을 경우 다시 Cb_update_form.jsp로 이동
            
        if (!file.isEmpty()) {
            String file_name = file.getOriginalFilename(); // 사용자가 업로드한 파일의 이름
            updated_post.setCb_file_name(file_name); // 클럽 객체의 cb_file_name을 업로드된 파일의 이름으로 설정
            try {
                File image_file = new File(request.getSession().getServletContext().getRealPath("/resources/images/"), file_name); // File 객체 생성. 파일의 이름과 저장될 위치 전달
                file.transferTo(image_file); // 업로드된 파일을 지정된 위치에 저장
            } catch (IOException e) {
                e.printStackTrace(); // IOException이 발생하면 에러 메시지를 출력
            }
        }

        cb_service.update_post(updated_post); // 글 수정 서비스 호출

        return "redirect:/clubs/clubboard/view?id=" + updated_post.getCb_id(); // 게시판으로 리다이렉트
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("cb_id") Integer cb_id, HttpServletRequest request) { // 글 삭제 함수
        cb_service.delete_post(cb_id); // 글 삭제 서비스 호출
        Integer c_id = (Integer) request.getSession().getAttribute("c_id"); // 현재 클럽 ID 가져오기
        return "redirect:/clubs/clubboard?c_id=" + c_id; // 게시판으로 리다이렉트
    }
    
    //댓글 관련 메서드 시작. 리스트를 불러오는 로직은 기존 view함수에 추가했음
    
    @PostMapping("/reply")
    public String reply(@RequestParam("description") String description, HttpServletRequest request) // 댓글 입력 메서드
    {
    	Club_reply club_reply = new Club_reply(); // 서비스에 전달 할 새 객체 생성
    	
    	club_reply.setCr_description(description); // 뷰 form태그에서 입력받아 리퀘스트파람으로 받아온 댓글내용 정보를 저장
    	club_reply.setCurrentTime(); // 실시간 작성시간 저장 함수 실행
    	HttpSession session = request.getSession(); // 세션 불러옴
    	Integer id = (Integer) session.getAttribute("cb_id"); // view페이지에 입장할때 세션에 저장한 cb_id가져옴
    	club_reply.setCr_cb_id(id); // 세션에 저장되어 있던 게시글id 저장
        Resident resident = (Resident)session.getAttribute("resident"); // 세션에 저장되어 있던 resident 객체 불러옴
        club_reply.setCr_writer_id(resident.getR_login_id()); // resident객체의 login_id를 댓글작성자id에 저장
    	
    	cb_service.post_reply(club_reply); // 만들어진 객체로 서비스에 있는 함수 실행
    	
    	return "redirect:/clubs/clubboard/view?id="+id; // 댓글 작성한 게시글로 리다이렉트
    }
    
    @GetMapping("/replydelete")
    public String reply_delete(@RequestParam("cr_id") Integer cr_id, HttpServletRequest request) // 댓글 삭제 메서드
    {
    	cb_service.delete_reply(cr_id); // 뷰 a태그에서 리퀘스트파람으로 받아온 cr_id로 서비스에 있는 함수 실행
    	Integer id = (Integer) request.getSession().getAttribute("cb_id"); // view페이지에 입장할때 세션에 저장한 cb_id가져옴
    	
    	return "redirect:/clubs/clubboard/view?id="+id; // 댓글 삭제한 게시글로 리다이렉트
    }
}