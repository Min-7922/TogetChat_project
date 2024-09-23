package com.min.togetChat.service.serviceInterface;

import java.io.IOException;
import java.util.List;

import com.min.togetChat.entity.BoardDTO;
import com.min.togetChat.entity.ReplyDTO;

public interface BoardService {

	List<BoardDTO> getList(int idx);

	void add(BoardDTO boardDTO) throws IllegalStateException, IOException;

	BoardDTO selectOne(int idx);

	List<String> getBoardImages(int boardIdx);

	List<ReplyDTO> getReplyList(int boardIdx);

	int getReplyCount(int boardIdx);

	void addReply(ReplyDTO writeReplyDTO);

	ReplyDTO getReplyOne(String writer);

	int replyModify(int replyIdx, String content);

	int replyDelete(int replyIdx);

	int deleteImage(String boardIdx, String imagePath);

	void boardModify(BoardDTO boardDTO) throws IllegalStateException, IOException;

	int getProgramIdxByBoardIdx(int boardIdx);

	void boardDelete(int boardIdx);

}
