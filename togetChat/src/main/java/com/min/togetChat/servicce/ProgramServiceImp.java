package com.min.togetChat.servicce;

import java.util.List;

import org.springframework.stereotype.Service;

import com.min.togetChat.entity.ProgramDTO;
import com.min.togetChat.repository.ProgramDAO;
import com.min.togetChat.servicce.serviceInterface.ProgramService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgramServiceImp implements ProgramService{
	
	private final ProgramDAO dao;
	
	@Override
	public List<ProgramDTO> getAllList() {
		return dao.getAllList();
	}

}
