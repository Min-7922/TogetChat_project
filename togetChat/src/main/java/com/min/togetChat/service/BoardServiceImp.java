package com.min.togetChat.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.min.togetChat.component.FileComponent;
import com.min.togetChat.entity.BoardDTO;
import com.min.togetChat.entity.ReplyDTO;
import com.min.togetChat.repository.BoardDAO;
import com.min.togetChat.service.serviceInterface.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImp implements BoardService{
	
	@Value("${file.upload-dir}")
	private String realPath;
	
	private final BoardDAO dao;
	private final FileComponent fileComponent;
	
	@Override
	public List<BoardDTO> getList(int idx) {
		return dao.getList(idx);
	}
	
	@Override
	public void add(BoardDTO boardDTO) throws IllegalStateException, IOException {
		// 글을 써서 DB에 넣는 작업
		dao.add(boardDTO);
		// 가장 최근 idx를 반환하는 메서드
		int boardIdx = dao.getBoardIdx();
		
		if(boardDTO.getFiles() != null && boardDTO.getFiles().length > 0) {
			MultipartFile[] files = boardDTO.getFiles();
			
			for(MultipartFile file : files) {
				if(!file.isEmpty() && file != null) {
					String image = fileComponent.getImage(file);
					dao.addImage(boardIdx, image);
				}
			}
		}
		
	} // end of add method
	
	@Override
	public BoardDTO selectOne(int idx) {
		return dao.selectOne(idx);
	}
	
	@Override
	public List<String> getBoardImages(int boardIdx) {
		List<String> boardImages = dao.getBoardImages(boardIdx);
		
		if(boardImages != null && boardImages.size() > 0) {
			
			for(int i=0; i<boardImages.size(); i++) {
				String path = realPath.replace("D:/uploadFiles", "/uploadFiles/");
				boardImages.set(i, path + boardImages.get(i));
			}
		}
		return boardImages;
	}
	
	@Override
	public List<ReplyDTO> getReplyList(int boardIdx) {
		return dao.getReplyList(boardIdx);
	}
	
	@Override
	public int getReplyCount(int boardIdx) {
		List<ReplyDTO> replyList = dao.getReplyList(boardIdx);
		int replyCount = replyList.size();
		return replyCount;
	}
		
	// 댓글 작성
	@Override
	public void addReply(ReplyDTO replyDTO) {
		dao.addReply(replyDTO);
	}
	
	// 작성자가 작성한 가장 최근 댓글 1개 조회
	@Override
	public ReplyDTO getReplyOne(String writer) {
		return dao.getReplyOneByWriter(writer);
	}
	
	// 댓글 수정
	@Override
	public int replyModify(int replyIdx, String content) {
		return dao.replyModify(replyIdx, content);
	}

}
