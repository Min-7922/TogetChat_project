package com.min.togetChat.component;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileComponent {
	
	@Value("${file.upload-dir}")
	private String realPath;

	
	// 파일 1개 첨부
	public String getImage(MultipartFile file) throws IllegalStateException, IOException {
		
		checkAndCreateDirectory();
		
		// 첨부한 file의 이름
		String fileName = file.getOriginalFilename();
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String fileExt = fileName.substring(fileName.lastIndexOf("."));
		
		String newFileName = uuid + fileExt;
		
		// File.separator : 파일의 구분자
		// OS마다 구분자가 다르지만 이 함수를 사용하면 알아서 환경에 맞게 구분자를 넣어줌
		String saveFileName = realPath + File.separator + newFileName;
		
		// 파일 저장
		File saveFile = new File(saveFileName);
		file.transferTo(saveFile);
		
		return newFileName;
	}

	private void checkAndCreateDirectory() {
		// 지정한 경로를 기반으로 File 객체 생성
 		File savePath = new File(realPath);
 		
 		// 만약 지정한 경로에 폴더가 없는경우
 		if(!savePath.exists()) {	// 해당 경로가 존재하지않으면
 			savePath.mkdirs();		// 폴더를 생성
 		}
	}
	
	// 경로에서 이미지 삭제하기
	public void deleteImage(String imagePath) {
		File file = new File(imagePath);
		
		if(file.exists()) {
			file.delete();
		}
		
	}
	
	

}
