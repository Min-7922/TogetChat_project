package com.min.togetChat.entity;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

//	IDX        NOT NULL NUMBER         
//	PROGRAMIDX NOT NULL NUMBER         
//	WRITER     NOT NULL VARCHAR2(500)  
//	TITLE      NOT NULL VARCHAR2(1000) 
//	CONTENT    NOT NULL VARCHAR2(4000) 
//	WRITEDATE           DATE           
//	VIEWCOUNT           NUMBER

@Getter
@Setter
public class BoardDTO {
	
	private int idx;
	private int programIdx;
	private String writer;
	private String title;
	private String content;
	private Date writeDate;
	private int viewCount;
	
	private MultipartFile[] files;

}
