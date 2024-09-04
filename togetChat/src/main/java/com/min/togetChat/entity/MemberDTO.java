package com.min.togetChat.entity;

//	IDX      NOT NULL NUMBER        
//	USERID   NOT NULL VARCHAR2(500) 
//	USERPW   NOT NULL VARCHAR2(500) 
//	EMAIL    NOT NULL VARCHAR2(500) 
//	NICKNAME NOT NULL VARCHAR2(500) 
//	ROLE     NOT NULL NUMBER 

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	private int idx;
	private String userId;
	private String userPw;
	private String email;
	private String nickName;
	private int role;
}
