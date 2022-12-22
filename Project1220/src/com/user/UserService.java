package com.user;

import java.util.List;
import java.util.Scanner;

public class UserService {
	Scanner sc = new Scanner(System.in);

	public static User userInfo = null;

	
	public static void userId() {
		System.out.println(userInfo);
	}
	// 로그인
	public void login() {
		User user = new User();

		String id = "";
		String pw = "";

		System.out.println("────────────로그인────────────");
		System.out.printf("아이디 > ");
		id = sc.nextLine();
		System.out.printf("비밀번호 > ");
		pw = sc.nextLine();

		user.setUserID(id);
		user = UserDAO.getInstance().login(user);

		if (user != null) {
			if (user.getUserPw().equals(pw)) {
				System.out.println(user.getUserID() + "님 로그인 되었습니다.");
				userInfo = user;
			} else {
				System.out.println("비밀번호가 틀립니다.");
			}
		} else {
			System.out.println("아이디가 존재하지 않습니다.");
		}
	}

	// 로그아웃
	public void logout() {
		userInfo = null;
	}

	// 회원조회
	public void getUserList() {
		List<User> list = UserDAO.getInstance().getUserList();
		for (User user : list) {
			System.out.println("아이디 	: " + user.getUserID());
			System.out.println("비밀번호 	: " + user.getUserPw());
			System.out.println("이름 	: " + user.getUserName());
			System.out.println("전화번호 	: " + user.getUserNum());
			System.out.println("회원등급	: " + user.getUserRank());
			//System.out.println("대출횟수(월) : " + user.getMonthCheckout());
			//System.out.println("대출횟수(총) : " + user.getTotalCheckout());
		}
	}

	// 회원조회 단건
	public void getUser() {
		System.out.println("조회할 회원 아이디를 입력해 주세요.");
		System.out.println("입력 > ");
		String id = sc.nextLine();

		User user = UserDAO.getInstance().getUser(id);
		if (user == null) {
			System.out.println("입력하신 아이디는 없는 아이디입니다.");
		} else {
			System.out.println("아이디 	: " + user.getUserID());
			System.out.println("비밀번호 	: " + user.getUserPw());
			System.out.println("이름 	: " + user.getUserName());
			System.out.println("전화번호 	: " + user.getUserNum());
			System.out.println("회원등급	: " + user.getUserRank());
			//System.out.println("대출횟수(월) : " + user.getMonthCheckout());
			//System.out.println("대출횟수(총) : " + user.getTotalCheckout());
		}

	}

	// 회원가입 // 회원등록
	public void insertUser() {
		User user = new User();

		String id = "";
		String pw = "";
		String name = "";
		int age = 0;
		String num = "";

		boolean run = true;
		System.out.println("회원 등록을 위해 정보를 입력하세요.");
		while (run) {
			System.out.printf("아이디 	> ");
			id = sc.nextLine();
			user.setUserID(id);

			user = UserDAO.getInstance().login(user);

			if (id == null) {
				user = new User();
				System.out.println("회원가입 가능한 아이디입니다.");
				user.setUserID(id);
				run = false;
				break;
			} else {
				System.out.println("이미 존재하는 아이디입니다. 다시 입력하세요");
			}
		}
		System.out.printf("비밀번호 	> ");
		pw = sc.nextLine();
		System.out.printf("이름 	> ");
		name = sc.nextLine();
		System.out.printf("나이 	> ");
		age = Integer.parseInt(sc.nextLine());
		System.out.printf("전화번호 	> ");
		num = sc.nextLine();

		user.setUserPw(pw);
		user.setUserName(name);
		user.setUserAge(age);
		user.setUserNum(num);

		int result = UserDAO.getInstance().insertUser(user);

		if (result == 1) {
			System.out.println("🌻🌻도서관 회원이 되신 것을 환영합니다.🌻🌻;");
		} else {
			System.out.println("회원가입에 실패했습니다.");
		}

	}

	// 회원탈퇴삭제
	public void deletuser() {
		System.out.println("아이디를 입력하세요.");
		System.out.println("ID >");
		String id = sc.nextLine();

		int result = UserDAO.getInstance().deletUser(id);

		if (result == 1) {
			System.out.println("탈퇴 완료");
		} else {
			System.out.println("탈퇴 실패");
		}
	}

	// 회원비밀번호
	// updateUserPw()
	public void updateUserPw() {
		User user = new User();

		System.out.println("아이디를 입력하세요 >");
		user.setUserID(sc.nextLine());
		System.out.println("변경할 비밀번호를 입력하세요.");
		user.setUserPw(sc.nextLine());
	}

	// 회원이름수정
	// updateUserName()
	public void updateUserName() {
		User user = new User();

		System.out.println("아이디를 입력하세요 >");
		user.setUserID(sc.nextLine());
		System.out.println("변경할 이름을 입력하세요.");
		user.setUserPw(sc.nextLine());

	}

	// 나이수정
	public void updateUserNum() {
		User user = new User();

		System.out.println("아이디를 입력하세요 >");
		user.setUserID(sc.nextLine());
		System.out.println("변경할 전화번호를 입력하세요.");
		user.setUserNum(sc.nextLine());

	}

	// 등급수정
	public void updateUserRank() {
		User user = new User();

		System.out.println("아이디를 입력하세요 >");
		user.setUserID(sc.nextLine());
		System.out.println("변경할 등급을 입력하세요.");
		user.setUserRank(sc.nextLine());

	}

	// 대출횟수(월)수정
	public void updateUserCO() {
		User user = new User();

		System.out.println("아이디를 입력하세요 >");
		user.setUserID(sc.nextLine());
		System.out.println("월 대출 횟수를 입력하세요.");
		user.setMonthCheckout(Integer.parseInt(sc.nextLine()));

	}

	// 대출횟수(총)수정
	public void updateUserTotalCO() {
		User user = new User();

		System.out.println("아이디를 입력하세요 >");
		user.setUserID(sc.nextLine());
		System.out.println("월 대출 횟수를 입력하세요.");
		user.setTotalCheckout(Integer.parseInt(sc.nextLine()));

	}
	
	public void updateUserInfo() {
		User user = new User();

		System.out.printf("변경할 사람의 아이디를 입력하세요 >");
		user.setUserID(sc.nextLine());
		System.out.println("정보를 입력하세요.");
		System.out.printf("비밀번호 >>");
		user.setUserPw(sc.nextLine());
		System.out.printf("이름. >>");
		user.setUserName(sc.nextLine());
		System.out.printf("전화번호 >>");
		user.setUserNum(sc.nextLine());
		System.out.printf("등급 >>");
		user.setUserRank(sc.nextLine());
		System.out.printf("대출횟수(월) >>");
		user.setMonthCheckout(Integer.parseInt(sc.nextLine()));
		System.out.printf("대출횟수(총) >>");
		user.setTotalCheckout(Integer.parseInt(sc.nextLine()));
	}
	
	

}
