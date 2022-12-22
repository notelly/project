package com.notice;

import java.util.ArrayList;
import java.util.List;

import com.common.DAO;

public class NoticeDAO extends DAO {
	int page;
	int bPageCount;
	int pageCount;
	// 홈페이지 보여주기 - showNotice()
	// 5개로 끊어서 보여주기
	// 1.상세보기 | 2. 이전 | 3. 다음 | 4. 뒤로가기
	

	// 싱글톤
	private static NoticeDAO noticeDao = null;
	
	private NoticeDAO() {
		page = 1;
	}
	public static NoticeDAO getInstance() {
		if (noticeDao == null) {
			noticeDao = new NoticeDAO();
		}
		return noticeDao;
	}


	// 공지사항 가지고 오기
	public List<Notice> getNotice() {
		List<Notice> list = new ArrayList<>(page);
		int start = 1 +(page-1)*5;
		int end = 5*page;
		Notice notice = null;
		
		try {
			conn();
			String sql = "SELECT * FROM (SELECT ROWNUM AS num, onotice. * FROM (SELECT * FROM notice ORDER BY post_date DESC) onotice) WHERE num BETWEEN ? AND ? ORDER BY num";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				notice = new Notice();
				notice.setPostNum(rs.getInt("num"));
				notice.setPostDate(rs.getString("post_date"));
				notice.setPostTitle(rs.getString("post_title"));
				notice.setPostCon(rs.getString("post_con"));
				list.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	public Notice pageCount() {
		Notice notice = null;

		try {
			conn();
			String sql = "SELECT COUNT(post_title) as noticecount FROM notice";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				notice = new Notice();
				notice.setPageCount(rs.getInt("noticecount"));
				bPageCount = notice.getPageCount();
				
				if(bPageCount%5 == 0) {
					System.out.println(notice.getPageCount()/5);
					pageCount = (notice.getPageCount())/5;
				}else {
					System.out.println((notice.getPageCount()/5)+1);
					pageCount = (notice.getPageCount())/5+1;
				}			
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return notice;
	}
	// 이전 goBack();
	public void pageback() {
		if (page == 1) {
			System.out.println("첫페이지 입니다.");
		} else {
			page--;
		}
	}
	
	public void pageforward() {
		if (page == pageCount) {
			System.out.println("마지막 페이지 입니다.");
		} else {
			page++;
		}
	}
	//게시물 몇개
	public Notice getPostCount() {
		Notice notice = null;
		try {
			conn();
			String sql = "SELECT COUNT(post_num) post_count FROM notice";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				notice = new Notice();
				notice.setPostCount(rs.getInt("post_count"));
				pageCount = Math.round((notice.getPostCount())/5);
				System.out.println(Math.round((notice.getPostCount())/5));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return notice;
	}
	
	// 페이지 가지고 오기
	public List<Notice> getPage() {
		List<Notice> list = new ArrayList<>();
		Notice notice = null;
		
		try {
			conn();
			String sql = "SELECT * FROM notice";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				notice = new Notice();
				notice.setPostNum(rs.getInt("post_num"));
				notice.setPostDate(rs.getString("post_date"));
				notice.setPostTitle(rs.getString("post_title"));
				notice.setPostCon(rs.getString("post_con"));
				list.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}

	// 공지사항 개시물 개수

	// 공지사항 내용가지고오기
	public Notice getpostContent(String postNum) {
		Notice notice = null;

		try {
			conn();
			String sql = "SELECT * FROM notice WHERE post_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postNum);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				notice = new Notice();
				notice.setPostNum(rs.getInt("post_num"));
				notice.setPostDate(rs.getString("post_date"));
				notice.setPostTitle(rs.getString("post_title"));
				notice.setPostCon(rs.getString("post_con"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return notice;
	}

	// 관리자
	// 공지사항 등록
	public int insertNotice(Notice notice) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO notice Values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice.getPostNum());
			pstmt.setString(2, notice.getPostDate());
			pstmt.setString(3, notice.getPostTitle());
			pstmt.setString(4, notice.getPostCon());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

	// 공지사항 수정 // 내용수정
	public int updateNoticeCon(Notice notice) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE notice SET notice_con = ? WHERE notice_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getPostCon());
			pstmt.setInt(2, notice.getPostNum());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

	public int updateNoticeTitle(Notice notice) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE notice SET notice_Title = ? WHERE notice_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getPostCon());
			pstmt.setInt(2, notice.getPostNum());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

	// 공지사항 삭제
	public int deleteNotice(Notice notice) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM notice WHERE notice_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice.getPostNum());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

}
