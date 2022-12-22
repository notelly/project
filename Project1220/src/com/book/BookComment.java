package com.book;

public class BookComment {
//	USER_ID              VARCHAR2(20)  
//	BOOK_CODE            VARCHAR2(11)  
//	COMMENT_NUM NOT NULL NUMBER(10)    
//	WRITE_DATE           DATE          
//	COMMENT_CON NOT NULL VARCHAR2(900) 
//	USER_STAR   NOT NULL NUMBER(1) 
	
	private String userId;
	private String bookCode;
	private int commentNum;
	private String writeDate;
	private String commentCon;
	private int userStar;
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getCommentCon() {
		return commentCon;
	}
	public void setCommentCon(String commentCon) {
		this.commentCon = commentCon;
	}
	public int getUserStar() {
		return userStar;
	}
	public void setUserStar(int userStar) {
		this.userStar = userStar;
	}
	
	
	
}
