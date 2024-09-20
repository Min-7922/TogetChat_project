package com.min.togetChat.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.min.togetChat.entity.BoardDTO;

@Mapper
public interface BoardDAO {
	
	@Select("select * from board where programIdx = #{idx} order by idx desc")
	List<BoardDTO> getList(int idx);
	
	@Insert("insert into board (programIdx, title, writer, content) "
			+ "values (#{programIdx}, #{title}, #{writer}, #{content})")
	void add(BoardDTO boardDTO);
	
	@Select("select idx from board where rownum = 1 order by idx desc")
	int getBoardIdx();
	
	@Insert("insert into boardImage (boardIdx, image) values (#{boardIdx}, #{image})")
	void addImage(int boardIdx, String image);
	
	

}
