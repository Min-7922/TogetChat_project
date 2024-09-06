package com.min.togetChat.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.min.togetChat.entity.MemberDTO;

@Mapper
public interface MemberDAO {
	
	@Select("select * from member where userId = #{userId} and userPw = #{userPw} and rownum = 1")
	MemberDTO login(String userId, String userPw);
	
	@Insert("insert into member (userId, userPw, nickName, email, profile)"
			+ "	values (#{userId}, #{userPw}, #{nickName}, #{email}, #{profile})")
	int join(MemberDTO member);
	
	@Select("select * from member where ${column} = #{value} and rownum = 1")
	MemberDTO dupCheck(String column, String value);

}
