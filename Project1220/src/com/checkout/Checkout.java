package com.checkout;

public class Checkout {
	
	
	/* USER_ID       VARCHAR2(20) 
	BOOK_CODE        VARCHAR2(11) 
	CHECKOUT_INFO    VARCHAR2(1)  
	CHECKOUT_DATE    DATE  */
	
	private String userId;
	private String bookCode;
	private String checkoutInfo;
	private String checkoutDate;
	private String returnDate;
	private int checkCount;
	private String bookName;
	
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
	public String getCheckoutInfo() {
		return checkoutInfo;
	}
	public void setCheckoutInfo(String checkoutInfo) {
		this.checkoutInfo = checkoutInfo;
	}
	public String getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public int getCheckCount() {
		return checkCount;
	}
	public void setCheckCount(int checkCount) {
		this.checkCount = checkCount;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
}
