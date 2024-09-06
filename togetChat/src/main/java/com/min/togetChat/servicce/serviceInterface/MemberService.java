package com.min.togetChat.servicce.serviceInterface;

import com.min.togetChat.entity.MemberDTO;

public interface MemberService {

	MemberDTO login(String userId, String userPw);

	int join(MemberDTO member);

	int dupCheck(String column, String value);
	
	String getHashPassword(String password);

}
