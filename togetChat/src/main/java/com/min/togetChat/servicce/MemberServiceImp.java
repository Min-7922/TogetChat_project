package com.min.togetChat.servicce;

import org.springframework.stereotype.Service;

import com.min.togetChat.entity.MemberDTO;
import com.min.togetChat.repository.MemberDAO;
import com.min.togetChat.servicce.serviceInterface.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{
	
	private final MemberDAO dao;
	
	@Override
	public MemberDTO login(MemberDTO member) {
		return dao.login(member);
	}
	
	@Override
	public int join(MemberDTO member) {
		return dao.join(member);
	}

}
