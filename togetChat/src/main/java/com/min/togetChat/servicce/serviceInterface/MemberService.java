package com.min.togetChat.servicce.serviceInterface;

import com.min.togetChat.entity.MemberDTO;

public interface MemberService {

	MemberDTO login(MemberDTO member);

	int join(MemberDTO member);

}
