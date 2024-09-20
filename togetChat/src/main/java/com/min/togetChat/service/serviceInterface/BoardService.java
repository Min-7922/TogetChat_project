package com.min.togetChat.service.serviceInterface;

import java.io.IOException;
import java.util.List;

import com.min.togetChat.entity.BoardDTO;

public interface BoardService {

	List<BoardDTO> getList(int idx);

	void add(BoardDTO boardDTO) throws IllegalStateException, IOException;

}
