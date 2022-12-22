package com.user;

import java.util.ArrayList;
import java.util.List;

import com.common.DAO;

public class UserDAO extends DAO {

	// 로그인 - login()
	// 회원 등록 - insertUser()
	// 회원 조회 - getUserList()
	// 회원 탈퇴 - deletUser()
	// 회원 수정 - updateUser()
	// 로그아웃 - logout()

	// 싱글톤
	private static UserDAO userDao = null;

	private UserDAO() {

	}

	public static UserDAO getInstance() {
		if (userDao == null) {
			userDao = new UserDAO();
		}
		return userDao;
	}

	// 1.로그인
	public User login(User user) {
		try {
			conn();
			String sql = "SELECT * FROM libraryuser Where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserID(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setUserAge(rs.getInt("user_age"));
				user.setUserNum(rs.getString("user_num"));
				user.setUserRank(rs.getString("user_rank"));
				user.setMonthCheckout(rs.getInt("month_checkout"));
				user.setTotalCheckout(rs.getInt("month_checkout"));
				
			} else {
				user = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return user;
	}
	//관리자로그인
	public User loginAdmin(User user) {
		try {
			conn();
			String sql = "SELECT * FROM libraryuser Where user_id = ? AND user_rank = 'A'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserID(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setUserAge(rs.getInt("user_age"));
				user.setUserNum(rs.getString("user_num"));
				user.setUserRank(rs.getString("user_rank"));
				user.setMonthCheckout(rs.getInt("month_checkout"));
				user.setTotalCheckout(rs.getInt("month_checkout"));
				
			} else {
				user = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return user;
	}
	

	// 회원 등록/회원가입 - insertUser() 
	public int insertUser(User user) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO libraryuser (user_id, user_pw, user_name, user_age, user_num)"
					+ "Values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			pstmt.setInt(4, user.getUserAge());
			pstmt.setString(5, user.getUserNum());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}

		return result;
	}

	// 회원 조회 전체 조회 - getUserList()
	public List<User> getUserList() {
		List<User> list = new ArrayList<>();
		User user = null;

		try {
			conn();
			String sql = "SELECT * FROM libraryuser";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				user = new User();
				user.setUserID(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setUserAge(rs.getInt("user_age"));
				user.setUserNum(rs.getString("user_num"));
				user.setUserRank(rs.getString("user_rank"));
				user.setMonthCheckout(rs.getInt("month_checkout"));
				user.setTotalCheckout(rs.getInt("month_checkout"));
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}

	// 회원 조회 단건조회 - getUser()
	public User getUser(String oneuser) {
		User user = null;

		try {
			conn();
			String sql = "SELECT * FROM libraryuser WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oneuser);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserID(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setUserAge(rs.getInt("user_age"));
				user.setUserNum(rs.getString("user_num"));
				user.setUserRank(rs.getString("user_rank"));
				user.setMonthCheckout(rs.getInt("month_checkout"));
				user.setTotalCheckout(rs.getInt("month_checkout"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return user;
	}

	
	//회원 탈퇴 - deletUser() // 1행이 삭제되었습니다.
	public int deletUser(String id) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM libraryuser WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	//마이페이지에서 회원수정.
	//회원 수정 - updateUserPw() 비밀번호 수정
	public int updateUserPw(User user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE libraryuser SET user_pw = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserPw());
			pstmt.setString(2, user.getUserID());
			
			result = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	
	//이름수정
	public int updateUserName(User user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE libraryuser SET user_pw = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserID());
			
			result = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	//나이수정
	public int updateUserAge(User user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE libraryuser SET user_pw = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getUserAge());
			pstmt.setString(2, user.getUserID());
			
			result = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	//등급수정
	public int updateUserRank(User user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE libraryuser SET user_pw = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserRank());
			pstmt.setString(2, user.getUserID());
			
			result = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	//대출횟수(월)
	public int updateUserCO(User user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE libraryuser SET user_pw = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getMonthCheckout());
			pstmt.setString(2, user.getUserID());
			
			result = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	//대출횟수(총)
	public int updateUserTotalCO(User user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE libraryuser SET user_pw = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getTotalCheckout());
			pstmt.setString(2, user.getUserID());
			
			result = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	
	//관리자페이지에서 회원정보수정
	//updateUser() 전체 수정
	//보류
	public int updateUserInfo(User user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE libraryuser Set user_pw = ?, user_name = ?, user_age =  ?, user_number = ?, user_rank = ?,"+
			 "month_checkout = ?, total_checkout = ?  WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sql);
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			pstmt.setInt(4, user.getUserAge());
			pstmt.setString(5, user.getUserNum());
			pstmt.setString(6, user.getUserRank());
			pstmt.setInt(7, user.getMonthCheckout());
			pstmt.setInt(8, user.getTotalCheckout());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	
	
	
	
}
