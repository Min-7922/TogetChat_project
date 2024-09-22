package com.min.togetChat.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.min.togetChat.entity.BoardDTO;
import com.min.togetChat.entity.ReplyDTO;

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
	
	@Select("select * from board where idx = #{idx}")
	BoardDTO selectOne(int idx);
	
	@Select("select image from boardImage where boardIdx = #{boardIdx}")
	List<String> getBoardImages(int boardIdx);
	
	@Select("select * from reply where boardIdx = #{boardIdx} order by idx desc")
	List<ReplyDTO> getReplyList(int boardIdx);
	
	@Insert("insert into reply (boardIdx, writer, content) values (#{boardIdx}, #{writer}, #{content})")
	void addReply(ReplyDTO replyDTO);
	
	@Select("select * from reply where writer = #{writer} and rownum = 1 order by idx desc")
	ReplyDTO getReplyOneByWriter(String writer);
	
	@Update("update reply set content = #{content} where idx = #{replyIdx}")
	int replyModify(int replyIdx, String content);
	
	

}
