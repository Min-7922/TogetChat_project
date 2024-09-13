package com.min.togetChat.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.min.togetChat.entity.ProgramDTO;

@Mapper
public interface ProgramDAO {
	
	// 모든 프로그램 조회
	@Select("select * from program order by idx desc")
	List<ProgramDTO> getAllList();
	
	// 카테고리별 프로그램 리스트 조회
	@Select("select program.* from program "
			+ "	join category "
			+ "	on category.idx = program.categoryIdx"
			+ "	where category.name = #{category}"
			+ "	order by program.idx desc")
	List<ProgramDTO> getListByCategory(String category);
	
	@Select("select * from program where title like #{search} order by idx desc")
	List<ProgramDTO> getListBySearch(String search);

}
