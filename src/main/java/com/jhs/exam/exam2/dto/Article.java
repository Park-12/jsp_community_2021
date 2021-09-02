package com.jhs.exam.exam2.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int boardId;
	private int memberId;
	private String title;
	private String body;
	
	private  Map<String, Object> extra = new LinkedHashMap<>();
	private String extra__writerName;
	private boolean extra__actorCanModify;
	private boolean extra__actorCanDelete;
	
	public String getTitleForPrint() {
		return title;
	}
	
	public String getBodySummaryForPrint() {
		return body;
	}
}
