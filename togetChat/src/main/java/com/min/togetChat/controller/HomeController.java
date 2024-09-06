package com.min.togetChat.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.min.togetChat.entity.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Value("${file.upload-dir}")
	private String realPath;
	
	@GetMapping("/")
	public String home(HttpSession session, Model model) {
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		if(login != null) {
			String profile = login.getProfile();
			if(!profile.contains("defaultprofile")) {
				// 타임리프가 인식할 수 있도록 /uploadFiles/로 시작하는 주소로 바꿔주기
				String path = realPath.replace("D:/uploadFiles", "/uploadFiles/");
				login.setProfile(path + profile);
				System.out.println("프로필 파일 경로 : " + login.getProfile());
			}
			model.addAttribute("login", login);
			
			return "content/home";
		}else {
			return "redirect:/member/login";
		}
		
	}
	
}
