package com.min.togetChat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.togetChat.entity.BoardDTO;
import com.min.togetChat.entity.MemberDTO;
import com.min.togetChat.entity.ProgramDTO;
import com.min.togetChat.entity.ReplyDTO;
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
	public String wirte(@PathVariable int programIdx, BoardDTO boardDTO) 
								throws IllegalStateException, IOException {
		
		boardDTO.setProgramIdx(programIdx);
		boardservice.add(boardDTO);
		
		return "redirect:/board/list/" + programIdx;
	}
	
	@GetMapping("view/{boardIdx}")
	public String view(@PathVariable int boardIdx, Model model) {
		// 게시글 정보
		BoardDTO boardDTO = boardservice.selectOne(boardIdx);
		// 게시글 이미지 정보
		List<String> images = boardservice.getBoardImages(boardIdx);
		// 댓글 정보
		List<ReplyDTO> replyList = boardservice.getReplyList(boardIdx);
		int replyCount = boardservice.getReplyCount(boardIdx);
		
		model.addAttribute("boardDTO", boardDTO);
		model.addAttribute("images", images);
		model.addAttribute("replyList", replyList);
		model.addAttribute("replyCount", replyCount);
		
		return "content/board/view";
	}
	
	@PostMapping("writeReply")
	@ResponseBody
	public ReplyDTO writeReply(@RequestBody HashMap<String, String> map) {
		String writer = map.get("writer");
		int boardIdx = Integer.parseInt(map.get("boardIdx"));
		String content = map.get("content");
		
		ReplyDTO writeReplyDTO = new ReplyDTO();
		writeReplyDTO.setBoardIdx(boardIdx);
		writeReplyDTO.setContent(content);
		writeReplyDTO.setWriter(writer);
		
		boardservice.addReply(writeReplyDTO);
		ReplyDTO replyDTO = boardservice.getReplyOne(writer);
		
		return replyDTO;
	}
	
	@PutMapping("replyModify")
	@ResponseBody
	public int replyModify(@RequestBody HashMap<String, String> map) {
		int replyIdx = Integer.parseInt(map.get("replyIdx"));
		String content = map.get("replyContent");
		int row = boardservice.replyModify(replyIdx, content);
		return row;
	}
	
	@DeleteMapping("replyDelete")
	@ResponseBody
	public int replyDelete(@RequestParam int replyIdx) {
		int row = boardservice.replyDelete(replyIdx);
		return row;
	}
	
	@DeleteMapping("deleteImage")
	@ResponseBody
	public int deleteImage(@RequestBody HashMap<String, String> map) {
		String imagePath = map.get("imagePath");
		String boardIdx = map.get("boardIdx");
		
		int row = boardservice.deleteImage(boardIdx, imagePath);
		return row;
	}
	
	@GetMapping("modify/{boardIdx}")
	public String boardModify(@PathVariable int boardIdx, Model model) {
		BoardDTO boardDTO = boardservice.selectOne(boardIdx);
		List<String> boardImages = boardservice.getBoardImages(boardIdx);
		
		model.addAttribute("boardDTO", boardDTO);
		model.addAttribute("boardImages", boardImages);
		
		return "content/board/modify";
	}
	
	@PostMapping("modify/{boardIdx}")
	public String boardModify(@PathVariable int boardIdx, BoardDTO boardDTO) throws IllegalStateException, IOException {
		boardDTO.setIdx(boardIdx);
		boardservice.boardModify(boardDTO);
		
		return "redirect:/board/view/" + boardIdx;
	}
	
	@GetMapping("delete/{boardIdx}")
	public String getMethodName(@PathVariable int boardIdx) {
		int programIdx = boardservice.getProgramIdxByBoardIdx(boardIdx);
		boardservice.boardDelete(boardIdx);
		return "redirect:/board/list/" + programIdx;
	}
	
	
	
	
	
}
