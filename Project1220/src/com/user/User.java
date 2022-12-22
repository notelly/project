package com.user;

public class User {
	/*user_id varchar2(20) primary key,
	user_pw varchar2(30)NOT NULL,
	user_name varchar2(15) NOT NULL,
	user_age number(3),
	user_num varchar2(13),
	user_rank varchar2(1) default 'N',
	month_checkout number(5) default 0,
	total_checkout number(5) default 0);*/
	
	//필드
	private String userID;
	private String userPw;
	private String userName;
	private int userAge;
	private String userNum;
	private String userRank;
	private int monthCheckout;
	private int totalCheckout;
	
	
	//메소드
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getUserRank() {
		return userRank;
	}
	public void setUserRank(String userRank) {
		this.userRank = userRank;
	}
	public int getMonthCheckout() {
		return monthCheckout;
	}
	public void setMonthCheckout(int monthCheckout) {
		this.monthCheckout = monthCheckout;
	}
	public int getTotalCheckout() {
		return totalCheckout;
	}
	public void setTotalCheckout(int totalCheckout) {
		this.totalCheckout = totalCheckout;
	}
	
	
	
	
	
}
