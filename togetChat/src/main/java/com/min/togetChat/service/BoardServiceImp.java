package com.min.togetChat.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.min.togetChat.component.FileComponent;
import com.min.togetChat.entity.BoardDTO;
import com.min.togetChat.repository.BoardDAO;
import com.min.togetChat.service.serviceInterface.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImp implements BoardService{
	
	private final BoardDAO dao;
	private final FileComponent fileComponent;
	
	@Override
	public List<BoardDTO> getList(int idx) {
		return dao.getList(idx);
	}
	
	@Override
	public void add(BoardDTO boardDTO) throws IllegalStateException, IOException {
		System.out.println("Service의 add 메서드 시작~~");
		// 글을 써서 DB에 넣는 작업
		dao.add(boardDTO);
		// 가장 최근 idx를 반환하는 메서드
		int boardIdx = dao.getBoardIdx();
		
		if(boardDTO.getFiles() != null && boardDTO.getFiles().length > 0) {
			MultipartFile[] files = boardDTO.getFiles();
			System.out.println("Multipart[] 시작~~");
			
			for(MultipartFile file : files) {
				if(!file.isEmpty() && file != null) {
					String image = fileComponent.getImage(file);
					dao.addImage(boardIdx, image);
				}
			}
		}
		
	}

}
