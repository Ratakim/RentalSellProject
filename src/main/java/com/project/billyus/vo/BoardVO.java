package com.project.billyus.vo;

import java.sql.Date;

//---------------------------------------
// public class BoardVO
//---------------------------------------
public class BoardVO {
	
	//---------------------------------------
	// 선언부
	//---------------------------------------
	private int 	article_NO;
	private String 	id;
	private String 	board_Type;
	private String 	title;
	private String 	content;
	private Date	write_Date;
	//---------------------------------------
	// get set
	//---------------------------------------
	public int getArticle_NO() {
		return article_NO;
	}
	public void setArticle_NO(int article_NO) {
		this.article_NO = article_NO;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBoard_Type() {
		return board_Type;
	}
	public void setBoard_Type(String board_Type) {
		this.board_Type = board_Type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWrite_Date() {
		return write_Date;
	}
	public void setWrite_Date(Date write_Date) {
		this.write_Date = write_Date;
	}
	
	//---------------------------------------
	// toString
	//---------------------------------------
	@Override
	public String toString() {
		return "BoardVO [article_NO=" + article_NO + ", id=" + id + ", board_Type=" + board_Type + ", title=" + title
				+ ", content=" + content + ", write_Date=" + write_Date + "]";
	}
	
	
	
	
	
}
