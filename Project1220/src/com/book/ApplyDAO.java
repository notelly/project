package com.book;

import java.util.ArrayList;
import java.util.List;

import com.common.DAO;

public class ApplyDAO extends DAO {
	int page = 1;
	int bPageCount;
	int pageCount;

	// 2. 희망도서신청
	private static ApplyDAO applyDao = null;

	private ApplyDAO() {

	}

	public static ApplyDAO getInstance() {
		if (applyDao == null) {
			applyDao = new ApplyDAO();
		}
		return applyDao;
	}

	// 2-1 1.도서신청 | 2. 신청도서조회 | 3. 신청안내
	// 1.도서신청
	public int insertApplyBook(Apply apply) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO bookapply (user_id, appli_name, apply_author, apply_publi) "
					+ "VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, apply.getUserId());
			pstmt.setString(2, apply.getApplyname());
			pstmt.setString(3, apply.getApplyauth());
			pstmt.setString(4, apply.getApplypubli());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

	// 2-2 1. 신청도서조회 | 2. 신청내역조회

	// 1. 신청도서조회
	public List<Apply> getApplyList() {
		List<Apply> list = new ArrayList<>(page);
		int start = 1 + (page - 1) * 5;
		int end = 5 * page;
		Apply apply = null;

		try {
			conn();
			String sql = "SELECT * FROM (SELECT ROWNUM AS num, oa.* FROM (SELECT * FROM bookapply ORDER BY apply_date DESC) oa) WHERE num BETWEEN ? AND ? ORDER BY num";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				apply = new Apply();
				
				apply.setApplyNum(rs.getInt("num"));
				apply.setUserId(rs.getString("user_id"));
				apply.setApplyname(rs.getString("appli_name"));
				apply.setApplyauth(rs.getString("apply_author"));
				apply.setApplyDate(rs.getString("apply_date"));
				apply.setApplyStep(rs.getString("apply_step"));
				apply.setApplypubli(rs.getString("apply_publi"));
				list.add(apply);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}

	// 페이징
	public Apply pageCount() {
		Apply apply = null;

		try {
			conn();
			String sql = "SELECT COUNT(appli_name) as applycount FROM bookapply";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				apply = new Apply();
				apply.setPageCount(rs.getInt("applycount"));
				bPageCount = apply.getPageCount();

				if (bPageCount % 5 == 0) {
					System.out.println(apply.getPageCount() / 5);
					pageCount = (apply.getPageCount()) / 5;
				} else {
					System.out.println((apply.getPageCount() / 5) + 1);
					pageCount = (apply.getPageCount()) / 5 + 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return apply;
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

	// 2.신청내역조회
	public Apply getApplyMyList(String id) {
		Apply apply = null;
		try {
			conn();
			String sql = "SELECT * FROM bookapply WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				apply = new Apply();

				apply.setApplyNum(rs.getInt("apply_num"));
				apply.setApplyname(rs.getString("appli_name"));
				apply.setApplyauth(rs.getString("apply_author"));
				apply.setUserId(rs.getString("user_id"));
				apply.setApplyDate(rs.getString("apply_date"));
				apply.setApplyStep(rs.getString("apply_step"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return apply;
	}
}
