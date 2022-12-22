package com.checkout;

import java.util.ArrayList;
import java.util.List;

import com.book.Book;
import com.common.DAO;

public class CheckoutDAO extends DAO {
	String bc;
	int count;
	
	private static CheckoutDAO checkoutDao = null;

	private CheckoutDAO() {
	}

	public static CheckoutDAO getInstance() {
		if (checkoutDao == null) {
			checkoutDao = new CheckoutDAO();
		}
		return checkoutDao;
	}
//	====================================
//	 1. 온라인도서 대출 | 2. 반납 | 3. 대출불가도서열람
//	====================================
//	(1)
//	===========온라인도서 대출===============
//	도서제목 입력 >>
//	오만과 편견
//
//	case1 도서대출가능
//	대출 신청이 완료 되었습니다. >> 마이페이지 가면 도서 열람 가능/온라인도서는 2권까지 대출가능
//	case2 대출불가능
//	대출이 불가능합니다. (반납예정일 : )
//
//	(2) 도서열람
//	일반회원이 누른 경우
//	====================
//	 도서 열람이 불가합니다.
//	====================
//	정회원이 누른 경우
//	============================
//	 1.도서조회 | 2. 도서열람신청 | 3. 나가기(홈페이지로 이동)
//	============================
//	(1)
//	1-2(1)과 동일
//	(2) 도서열람신청
//	1-4(1)과 똑같은 맥락
	
	//Id 불러오기
	public void callId() {
		
	}

	// 대출 자격 조회 
	public Checkout checkright(String bookName) {
		Checkout checkout = null;
		try {
			conn();
			String sql = "SELECT book_code FROM (SELECT * FROM book WHERE onoff_info = 'on') WHERE book_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkout = new Checkout();
				checkout.setBookCode(rs.getString("book_code"));
				bc = checkout.getBookCode();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return checkout;

	}

	// 도서 대출 >> check out table에 등록
	public int checkout(Checkout checkout) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO checkout (user_id, book_code) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkout.getUserId());
			pstmt.setString(2, checkout.getBookCode());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	//대출 횟수
	public Checkout checkCount(String id) {
		Checkout checkout = null;
		try {
			conn();
			String sql = "SELECT COUNT(user_id) as count FROM (SELECT * FROM checkout WHERE user_id = ? ) WHERE checkout_info = 'C' ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				checkout = new Checkout();
				checkout.setCheckCount(rs.getInt("count"));
				count = checkout.getCheckCount();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return checkout;
		
	}
	
	// 반납
	//현재 대출한 내역 보여주기
	public List<Checkout> ShowCheckoutList(String id) {
		List<Checkout> list = new ArrayList<>();
		Checkout checkout = null;
		
		try {
			conn();
			String sql = "SELECT * FROM (SELECT * FROM checkout_view WHERE user_id = ?) WHERE checkout_info = 'C'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				checkout = new Checkout();
				checkout.setUserId(rs.getString("user_id"));
				checkout.setBookName(rs.getString("book_name"));
				checkout.setBookCode(rs.getString("book_code"));
				checkout.setCheckoutInfo(rs.getString("checkout_info"));
				checkout.setCheckoutDate(rs.getString("checkout_date"));
				checkout.setReturnDate(rs.getString("return_date"));
				list.add(checkout);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
		
	}
	//반납 //update 사용
	public int returnBook(String bc) {
		int result = 0;
		
		try {
			conn();
			String sql = "UPDATE checkout SET checkout_info = 'R' WHERE book_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bc);
			
			result = pstmt.executeUpdate();
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
		
	}
	
	// 대출불가도서열람

}
