package com.min.togetChat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.togetChat.entity.MemberDTO;
import com.min.togetChat.servicce.serviceInterface.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService service;
	
	@Value("${file.upload-dir}")
	private String realPath;
	
	
	// 로그인 페이지
	@GetMapping("login")
	public String login() {
		return "content/member/login";
	}
	
	// 로그인 했을 때
	@PostMapping("login")
	@ResponseBody
	public int login(@RequestBody HashMap<String, String> param, HttpSession session) {
		MemberDTO login = service.login(param.get("userId"), param.get("userPw"));
		int result = 0;
		if(login != null) {
			session.setAttribute("login", login);
			result = 1;
		}
		return result;
	}
	
	@GetMapping("join")
	public String join() {
		return "content/member/join";
	}
	
//	@PostMapping("join") 
//	@ResponseBody
//	public int join(@ModelAttribute MemberDTO member, @RequestParam("profileFile") MultipartFile file) {
//		member.setProfileFile(file);
//		
//		int row = service.join(member);
//		return row;
//	}
	
	@PostMapping("join")
	public String join(MemberDTO member) {
		int row = service.join(member);
		
		if(row == 1) { return "redirect:/"; }
		else {
			System.out.println("회원가입 실패");
			return "content/member/join?memberJoin=false";
		}
	}
	
	@PostMapping("dupCheck")
	@ResponseBody
	public int dupCheck(@RequestBody Map<String, String> requestData) {
		String column = requestData.get("column");
		String value = requestData.get("value");
		int row = service.dupCheck(column, value);
		return row;
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
