package com.min.togetChat.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.togetChat.entity.BoardDTO;
import com.min.togetChat.entity.MemberDTO;
import com.min.togetChat.entity.ProgramDTO;
import com.min.togetChat.service.serviceInterface.BoardService;
import com.min.togetChat.service.serviceInterface.ProgramService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {
	
	private final ProgramService programservice;
	private final BoardService boardservice;
	
	@GetMapping("list/{idx}")
	public String getList(@PathVariable int idx, Model model) {
		ProgramDTO programDTO = programservice.getOne(idx);
		List<BoardDTO> boardList = boardservice.getList(idx);
		
		model.addAttribute("program", programDTO);
		model.addAttribute("boardList", boardList);
		
		return "content/board/list";
	}
	
	@GetMapping("write/{programIdx}")
	public String write(HttpSession session, Model model) {
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		model.addAttribute("login", login);
		return "content/board/write";
	}
	
	@PostMapping("write/{programIdx}")
	public String wirte(@PathVariable int programIdx, BoardDTO boardDTO) throws IllegalStateException, IOException {
		
		boardDTO.setProgramIdx(programIdx);
		boardservice.add(boardDTO);
		
		return "redirect:/board/list/" + programIdx;
	}
	
	
	
}
