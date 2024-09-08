package com.min.togetChat.entity;

import lombok.Getter;
import lombok.Setter;

//	IDX         NOT NULL NUMBER         
//	CATEGORYIDX NOT NULL NUMBER         
//	TITLE       NOT NULL VARCHAR2(1000) 
//	CONTENT              VARCHAR2(4000) 
//	IMAGE                VARCHAR2(4000)

@Setter
@Getter
public class ProgramDTO {
	
	private int idx;
	private int cateogryIdx;
	private String title;
	private String content;
	private String image;

}
