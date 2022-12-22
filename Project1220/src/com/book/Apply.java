package com.book;

public class Apply {
	/*도서신청
	게시글num 	post_num
	신청도서순번 	apply_num 
	도서제목 		appli_name
	도서저자 		apply_author
	도서신청일 	apply_date //신청일 기준으로 정렬
	회원id 		user_id foreign_key
	신청단계코드	apply_step
	대기중, 확인, 입고, 불가(),
	
	APPLY_NUM    NOT NULL NUMBER(10)    
	USER_ID               VARCHAR2(20)  
	APPLI_NAME   NOT NULL VARCHAR2(200) 
	APPLY_AUTHOR NOT NULL VARCHAR2(50)  
	APPLY_DATE            DATE          
	APPLY_STEP            VARCHAR2(1)*/
	
	private int applyNum;
	private String userId;
	private String applyname;
	private String applyauth;
	private String applyDate;
	private String applyStep;
	private String applypubli;
	private int pageCount;
	
		
	
	public int getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getApplyname() {
		return applyname;
	}
	public void setApplyname(String applyname) {
		this.applyname = applyname;
	}
	public String getApplyauth() {
		return applyauth;
	}
	public void setApplyauth(String applyauth) {
		this.applyauth = applyauth;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyStep() {
		return applyStep;
	}
	public void setApplyStep(String applyStep) {
		this.applyStep = applyStep;
	}
	public String getApplypubli() {
		return applypubli;
	}
	public void setApplypubli(String applypubli) {
		this.applypubli = applypubli;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	
}
