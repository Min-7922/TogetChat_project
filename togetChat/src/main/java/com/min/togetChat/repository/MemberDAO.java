package com.min.togetChat.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.min.togetChat.entity.MemberDTO;

@Mapper
public interface MemberDAO {
	
	@Select("select * from member where userId = #{userId} and userPw = #{userPw} and rownum = 1")
	MemberDTO login(MemberDTO member);
	
	@Insert("insert into member (userId, userPw, nickName, email)"
			+ "	values (#{userId}, #{userPw}, #{nickName}, #{email})")
	int join(MemberDTO member);

}
