package com.buildingHelper.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Error_controller {
	/*
	 * 설명: 예외 처리시 error.jsp를 렌더링합니다.
	 */
	@GetMapping("/error")
    public String Error404(HttpServletResponse res) {
        return "error";
    }
}
