package com.min.togetChat.entity;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

//	IDX       NOT NULL NUMBER         
//	BOARDIDX  NOT NULL NUMBER         
//	WRITER    NOT NULL VARCHAR2(500)  
//	CONTENT   NOT NULL VARCHAR2(2000) 
//	WRITEDATE          DATE 

@Getter
@Setter
public class ReplyDTO {
	
	private int idx;
	private int boardIdx;
	private String writer;
	private String content;
	private Date writeDate;

}
