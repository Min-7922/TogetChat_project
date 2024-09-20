package com.min.togetChat.service.serviceInterface;

import java.util.List;

import com.min.togetChat.entity.ProgramDTO;

public interface ProgramService {

	List<ProgramDTO> getAllList();

	List<ProgramDTO> getListByCategory(String category);

	List<ProgramDTO> getListBySearch(String search);

	ProgramDTO getOne(int idx);

}
