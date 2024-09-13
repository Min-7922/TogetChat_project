package com.min.togetChat.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.togetChat.entity.MemberDTO;
import com.min.togetChat.entity.ProgramDTO;
import com.min.togetChat.servicce.serviceInterface.ProgramService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	@Value("${file.upload-dir}")
	private String realPath;
	
	private final ProgramService programservice;
	
	// 기본 대문페이지
	@GetMapping("/")
	public String home(HttpSession session, Model model) {
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		// 로그인 성공
		if(login != null) {
			String profile = login.getProfile();
			
			if(!profile.contains("defaultprofile")) {
				// 타임리프가 인식할 수 있도록 /uploadFiles/로 시작하는 주소로 바꿔주기
				String path = realPath.replace("D:/uploadFiles", "/uploadFiles/");
				login.setProfile(path + profile);
			}
			List<ProgramDTO> programList = programservice.getAllList();
			model.addAttribute("programList", programList);
			
			session.setAttribute("login", login);
			return "content/home";
			
		// 로그인하지 않았을 때
		}else {
			return "redirect:/member/login";
		}
	}
	
	@GetMapping("/search")
	public String search() {
		
		return "content/search";
	}
	
	@PostMapping("/search")
	@ResponseBody
	public List<ProgramDTO> getSearchList(@RequestBody HashMap<String, String> map) {
		String search = map.get("search");
		List<ProgramDTO> searchList = programservice.getListBySearch(search);
		return searchList;
	}
	
	
}
