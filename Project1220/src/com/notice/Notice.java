package com.notice;

public class Notice {
	/*	POST_NUM   NOT NULL NUMBER(10)     
		POST_DATE           DATE           
		POST_TITLE NOT NULL VARCHAR2(50)   
		POST_CON   NOT NULL VARCHAR2(3000)*/
	
	//필드
	private int postNum;
	private String postDate;
	private String postTitle;
	private String postCon;
	private int postCount;
	private int pageCount;
	
	//메소드
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostCon() {
		return postCon;
	}
	public void setPostCon(String postCon) {
		this.postCon = postCon;
	}
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	
	
}
