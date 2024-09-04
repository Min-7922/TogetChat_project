package com.min.togetChat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.togetChat.entity.MemberDTO;
import com.min.togetChat.servicce.serviceInterface.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService service;
	
	@GetMapping("login")
	public String login() {
		return "/member/login";
	}
	
	@PostMapping("login")
	public String login(MemberDTO member, HttpSession session) {
		MemberDTO login = service.login(member);
		if(login != null) {
			session.setAttribute("login", login);
			return "redirect:/";
		}else {
			return "redirect:/member/login?error=true";
		}
	}
	
	@GetMapping("join")
	public String join() {
		return "/member/join";
	}
	
	@PostMapping("join") 
	public String join(MemberDTO member) {
		int success = service.join(member);
		if(success == 1) {
			return "redirect:/member/login?joinsuccess=true";
		}else {
			return "/member/join";
		}
	}
}
