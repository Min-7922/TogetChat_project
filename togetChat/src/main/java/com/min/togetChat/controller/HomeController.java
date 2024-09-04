package com.min.togetChat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.min.togetChat.entity.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpSession session) {
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		if(login != null) {
			return "/layouts/homeLayout";
		}else {
			return "redirect:/member/login";
		}
		
	}
	
}
