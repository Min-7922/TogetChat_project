package com.min.togetChat.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.min.togetChat.entity.ProgramDTO;

@Mapper
public interface ProgramDAO {
	
	@Select("select * from program order by idx desc")
	List<ProgramDTO> getAllList();

}
