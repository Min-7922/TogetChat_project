package com.min.togetChat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.togetChat.entity.ProgramDTO;
import com.min.togetChat.service.serviceInterface.ProgramService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/program/")
@RequiredArgsConstructor
public class ProgramController {
	
	private final ProgramService programservice;
	
	private static final Map<String, String> categoryMap = Map.of(
				"drama", "드라마",
				"movie", "영화",
				"entertainment", "예능",
				"animation", "애니메이션"
			);
	
	@GetMapping("{category}")
	public String category(@PathVariable String category, Model model) {
		String categoryName = categoryMap.getOrDefault(category, "기타");
		
		List<ProgramDTO> listByCategory = programservice.getListByCategory(category);
		
		model.addAttribute("categoryName",categoryName);
		model.addAttribute("listByCategory", listByCategory);
		
		return "content/programCategory";
	}
	
	

}
