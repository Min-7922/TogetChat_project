package com.min.togetChat.servicce;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.min.togetChat.component.FileComponent;
import com.min.togetChat.entity.MemberDTO;
import com.min.togetChat.repository.MemberDAO;
import com.min.togetChat.servicce.serviceInterface.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{
	
	private final MemberDAO dao;
	private final FileComponent fileComponent;
	
	Random ran = new Random();
	
	@Override
	public MemberDTO login(String userId, String userPw) {
		String encodedPassword = getHashPassword(userPw);
		return dao.login(userId, encodedPassword);
	}
	
	@Override
	public int join(MemberDTO member) {
		// 파일 업로드 처리
		String profile = "";
		try {
			// 프로필 파일을 첨부한 경우
			if(member.getProfileFile() != null && !member.getProfileFile().isEmpty()) {
				profile = fileComponent.getImage(member.getProfileFile());
			}else {	// 첨부하지 않은 경우 기본이미지로 대체
				int num = ran.nextInt(3) + 1;
				profile = "/images/defaultprofile" + num + ".png";
			}
			member.setProfile(profile);
		} catch (IllegalStateException | IOException e) {
			System.out.println("프로필 이미지 업로드 과정에서 문제가 생겼습니다");
			e.printStackTrace();
		}
		
		// 비밀번호 해시처리
		String password = member.getUserPw();
		String encodePassword = getHashPassword(password);
		
		member.setUserPw(encodePassword);
		
		return dao.join(member);
	}
	
	
	// 비밀번호 해시 처리
	@Override
	public String getHashPassword(String password) {
		MessageDigest md;
		String hashPassword = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(password.getBytes());
			hashPassword = String.format("%0128x", new BigInteger(1, md.digest()));
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashPassword;
	}

	// 아이디/닉네임 중복체크
	@Override
	public int dupCheck(String column, String value) {
		int row = 0;
		MemberDTO dto = dao.dupCheck(column, value);
		
		if(dto != null) { row = 1; }
		return row;
	}

}
