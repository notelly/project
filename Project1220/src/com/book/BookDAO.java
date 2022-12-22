package com.book;

import java.util.ArrayList;
import java.util.List;

import com.common.DAO;

public class BookDAO extends DAO {
	int page = 1;
	int bPageCount;
	int pageCount;
	// 도서조회
	// 1. 통합도서조회 | 2. 희망도서신청
	private static BookDAO bookDao = null;

	private BookDAO() {

	}

	public static BookDAO getInstance() {
		if (bookDao == null) {
			bookDao = new BookDAO();
		}
		return bookDao;
	}

	// 1. 도서조회
	// 전체조회
	public List<Book> getBookList() {
		List<Book> list = new ArrayList<>(page);
		int start = 1 + (page - 1) * 5;
		int end = 5 * page;
		Book book = null;

		try {
			conn();
			String sql = "SELECT * FROM (SELECT ROWNUM AS num, ob. * FROM (SELECT * FROM book ORDER BY stock_date DESC) ob) WHERE num BETWEEN ? AND ? ORDER BY num";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				book = new Book();
				
				book.setListnum(rs.getInt("num"));
				book.setBookName(rs.getString("book_name"));
				book.setBookAuth(rs.getString("book_author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubliyear(rs.getString("publi_year"));
				book.setStockdate(rs.getString("stock_date"));
				book.setBookRCode(rs.getString("book_rCode"));
				book.setBookCode(rs.getString("book_code"));
				book.setBooktype(rs.getString("book_type"));
				book.setCheckoutNum(rs.getInt("checkout_num"));
				book.setStarAvg(rs.getInt("star_avg"));
				book.setOnOffInfo(rs.getString("onoff_info"));
				list.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}

	public Book pageCount() {
		Book book = null;

		try {
			conn();
			String sql = "SELECT COUNT(book_name) as bookcount FROM book";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				book = new Book();
				book.setPageCount(rs.getInt("bookcount"));
				bPageCount = book.getPageCount();
				
				if(bPageCount%5 == 0) {
					System.out.println(book.getPageCount()/5);
					pageCount = (book.getPageCount())/5;
				}else {
					System.out.println((book.getPageCount()/5)+1);
					pageCount = (book.getPageCount())/5+1;
				}			
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return book;
	}

	public void pageback() {
		if (page == 1) {
			System.out.println("❌첫페이지 입니다.❌");
		} else {
			page--;
		}
	}

	public void pageforward() {
		if (page == pageCount) {
			System.out.println("❌마지막 페이지 입니다.❌");
		} else {
			page++;
		}
	}
	
	//도서정보출력 >> 상세보기
	public Book getBookInfo(String name) {
		Book book = null;
		
		try {
			conn();
			String sql = "SELECT * FROM book WHERE book_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new Book();

				book.setBookName(rs.getString("book_name"));
				book.setBookAuth(rs.getString("book_author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubliyear(rs.getString("publi_year"));
				book.setStockdate(rs.getString("stock_date"));
				book.setBookCode(rs.getString("book_rCode"));
				book.setBookRCode(rs.getString("book_code"));
				book.setBooktype(rs.getString("book_type"));
				book.setCheckoutNum(rs.getInt("checkout_num"));
				book.setStarAvg(rs.getInt("star_avg"));
				book.setOnOffInfo(rs.getString("onoff_info"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return book;
	}
	

	// 단일조회 (도서검색)
	// 1. 도서 제목 | 2. 저자 | 3. 도서유형
	// 1.도서제목
	public Book getBookName(String name) {
		Book book = null;

		try {
			conn();
			String sql = "SELECT * FROM book WHERE book_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new Book();

				book.setBookName(rs.getString("book_name"));
				book.setBookAuth(rs.getString("book_author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubliyear(rs.getString("publi_year"));
				book.setStockdate(rs.getString("stock_date"));
				book.setBookCode(rs.getString("book_rCode"));
				book.setBookRCode(rs.getString("book_code"));
				book.setBooktype(rs.getString("book_type"));
				book.setCheckoutNum(rs.getInt("checkout_num"));
				book.setStarAvg(rs.getInt("star_avg"));
				book.setOnOffInfo(rs.getString("onoff_info"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return book;
	}

	// 2. 저자
	public Book getBookAuth(String auth) {
		Book book = null;

		try {
			conn();
			String sql = "SELECT * FROM book WHERE book_author = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, auth);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new Book();

				book.setBookName(rs.getString("book_name"));
				book.setBookAuth(rs.getString("book_author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubliyear(rs.getString("publi_year"));
				book.setStockdate(rs.getString("stock_date"));
				book.setBookCode(rs.getString("book_rCode"));
				book.setBookRCode(rs.getString("book_code"));
				book.setBooktype(rs.getString("book_type"));
				book.setCheckoutNum(rs.getInt("checkout_num"));
				book.setStarAvg(rs.getInt("star_avg"));
				book.setOnOffInfo(rs.getString("onoff_info"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return book;
	}

	// 3. 도서유형
	public Book getBookCode(String code) {
		Book book = null;

		try {
			conn();
			String sql = "SELECT * FROM book WHERE book_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new Book();

				book.setBookName(rs.getString("book_name"));
				book.setBookAuth(rs.getString("book_author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubliyear(rs.getString("publi_year"));
				book.setStockdate(rs.getString("stock_date"));
				book.setBookCode(rs.getString("book_rCode"));
				book.setBookRCode(rs.getString("book_code"));
				book.setBooktype(rs.getString("book_type"));
				book.setCheckoutNum(rs.getInt("checkout_num"));
				book.setStarAvg(rs.getInt("star_avg"));
				book.setOnOffInfo(rs.getString("onoff_info"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return book;
	}

}
