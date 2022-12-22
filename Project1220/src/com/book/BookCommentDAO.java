package com.book;

import java.util.ArrayList;
import java.util.List;

import com.common.DAO;
import com.user.UserDAO;

public class BookCommentDAO extends DAO{
	int page;
	String code;
	
	
	private static BookCommentDAO bookcommentDao = null;

	private BookCommentDAO() {
		page = 1;
	}

	public static BookCommentDAO getInstance() {
		if (bookcommentDao == null) {
			bookcommentDao = new BookCommentDAO();
		}
		return bookcommentDao;
	}
	//댓글 등록 권한 확인 commentRight
	public BookComment commentRight(BookComment bookcomment) {
		BookComment bc = null;
		
		try {
			bc = new BookComment();
			conn();
			String sql = "SELECT * FROM(SELECT * FROM checkout WHERE book_code = ? AND checkout_info = 'R') WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bc.getBookCode());
			pstmt.setString(2, bc.getUserId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bc.setUserId(rs.getString("user_id"));
				bc.setBookCode(rs.getString("book_code"));
				bc.setWriteDate(rs.getString("write_date"));
				bc.setCommentCon(rs.getString("comment_con"));
				bc.setUserStar(rs.getInt("user_star"));
				code = bc.getBookCode();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return bc;
	}
	
	//댓글 등록 insertComment
	public int insertCommnet(BookComment bookcomment) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO bookcomment(user_id, book_code, comment_con, user_star) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookcomment.getUserId());
			pstmt.setString(2, bookcomment.getBookCode());
			pstmt.setString(3, bookcomment.getCommentCon());
			pstmt.setInt(4, bookcomment.getUserStar());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	//댓글 조회 getCommnet
	//대출한 사람, 대출정보
	//댓글내용
	public List<BookComment> getCommentList(String bookcode){
		List<BookComment> list = new ArrayList<>();
		BookComment bookcomment = null;
//		int start = 1+(page-1)*5;
//		int end = 5*page;
		
		try {
			conn();
			String sql = "SELECT * FROM bookcomment WHERE book_code = ?";
//					"SELECT * "
//					+ "FROM (SELECT ROWNUM AS num, obc. * FROM "
//					+ "(SELECT * FROM bookcomment WHERE book_code = ? ORDER BY write_date DESC) obc) "
//					+ "WHERE num BETWEEN 1 AND 5 ORDER BY num";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookcode);
//			pstmt.setInt(2, start);
//			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				bookcomment = new BookComment();
				bookcomment.setUserId(rs.getString("user_id"));
				bookcomment.setBookCode(rs.getString("book_code"));
				bookcomment.setWriteDate(rs.getString("write_date"));
				bookcomment.setCommentCon(rs.getString("comment_con"));
				bookcomment.setUserStar(rs.getInt("user_star"));
				list.add(bookcomment);
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
		
		
	}
	
	//댓글 상세 보기
		public BookComment getCommentInfo(BookComment bookcomment) {
			BookComment bc = new BookComment();
			
			try {
				conn();
				String sql = "SELECT * FROM bookcomment WHERE book_code = ?' AND user_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bc.getBookCode());
				pstmt.setString(2, bc.getUserId());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {			
					bc.setUserId(rs.getString("user_id"));
					bc.setBookCode(rs.getString("book_code"));
					bc.setWriteDate(rs.getString("write_date"));
					bc.setCommentCon(rs.getString("comment_con"));
					bc.setUserStar(rs.getInt("user_star"));
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disconn();
			}		
			return bc;
		}
		
		//작성댓글 조회
		public List<BookComment> getCommentMylist(String id){
			List<BookComment> list = new ArrayList<>();
			BookComment bookcomment = null;
					
			try {
				conn();
				String sql = "SELECT ROWNUM AS num, ob. * FROM (SELECT * FROM bookcomment ORDER BY write_date DESC) ob WHERE user_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					bookcomment = new BookComment();
					bookcomment.setUserId(rs.getString("user_id"));
					bookcomment.setBookCode(rs.getString("book_code"));
					bookcomment.setWriteDate(rs.getString("write_date"));
					bookcomment.setCommentCon(rs.getString("comment_con"));
					bookcomment.setUserStar(rs.getInt("user_star"));
					bookcomment.setCommentNum(rs.getInt("num"));
					list.add(bookcomment);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disconn();
			}
			return list;
		}	
	
//		//댓글 삭제 
//		public int deleteComment(BookComment bookcomment) {
//			int result = 0;
//			try {
//				conn();
//				String sql = "DELECT FROM bookcomment WHERE user_id = ? AND book_code = ?";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, );
//				
//				
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				disconn();
//			}
//			return result;
//		}	
//		
}
