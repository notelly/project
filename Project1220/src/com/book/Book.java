package com.book;

public class Book {
	/*BOOK_NAME    NOT NULL VARCHAR2(200) 
	BOOK_AUTHOR  NOT NULL VARCHAR2(50)  
	PUBLISHER    NOT NULL VARCHAR2(20)  
	PUBLI_YEAR            DATE          
	STOCK_DATE            DATE          
	BOOK_RCODE            VARCHAR2(1)   
	BOOK_CODE    NOT NULL VARCHAR2(11)  
	BOOK_TYPE             VARCHAR2(12)  
	CHECKOUT_NUM          NUMBER(5)     
	STAR_AVG              NUMBER(1)     
	ONOFF_INFO            VARCHAR2(3)*/
	
	private String bookName;
	private String bookAuth;
	private String publisher;
	private String publiyear;
	private String Stockdate;
	private String bookRCode;
	private String bookCode;
	private String booktype;
	private int checkoutNum;
	private int starAvg;
	private String onOffInfo;
	private int pageCount;
	private int listnum;
	
	
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuth() {
		return bookAuth;
	}
	public void setBookAuth(String bookAuth) {
		this.bookAuth = bookAuth;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPubliyear() {
		return publiyear;
	}
	public void setPubliyear(String publiyear) {
		this.publiyear = publiyear;
	}
	public String getStockdate() {
		return Stockdate;
	}
	public void setStockdate(String stockdate) {
		Stockdate = stockdate;
	}
	public String getBookRCode() {
		return bookRCode;
	}
	public void setBookRCode(String bookRCode) {
		this.bookRCode = bookRCode;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBooktype() {
		return booktype;
	}
	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}
	public int getCheckoutNum() {
		return checkoutNum;
	}
	public void setCheckoutNum(int checkoutNum) {
		this.checkoutNum = checkoutNum;
	}
	public int getStarAvg() {
		return starAvg;
	}
	public void setStarAvg(int starAvg) {
		this.starAvg = starAvg;
	}
	public String getOnOffInfo() {
		return onOffInfo;
	}
	public void setOnOffInfo(String onOffInfo) {
		this.onOffInfo = onOffInfo;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getListnum() {
		return listnum;
	}
	public void setListnum(int listnum) {
		this.listnum = listnum;
	}
	
	
	
	
}
