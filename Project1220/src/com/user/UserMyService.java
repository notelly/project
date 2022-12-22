package com.user;

import java.util.List;
import java.util.Scanner;

import com.book.Book;
import com.book.BookComment;
import com.book.BookCommentDAO;
import com.book.BookDAO;

public class UserMyService {
	Scanner sc = new Scanner(System.in);
	User user = new User();
	UserService us = new UserService();
	BookComment bc = new BookComment();
	Book book = new Book();

	public void userMylist() {
		boolean run = true;
		while (run) {
			System.out.println("=========================== 마이페이지 ==================");
			System.out.println(" 1. 개인정보수정 | 2. 작성댓글조회 | 3.홈페이지로 ");
			System.out.println("───────────────────────────────────────────────────────");
			System.out.printf("입력 >>");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				int result = personalInfo(); // 리턴을 시켜서 내가 선택한 값을 받아오고 그것에 따라
				if (result == 1 || result == 2) { // 상호작용을 다르게 설정해줄수 있다.
					run = false;
				}
				break;
			case 2:
				String id = UserService.userInfo.getUserID();
				List<BookComment> list = BookCommentDAO.getInstance().getCommentMylist(id);
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i).getCommentNum() + "\t" + list.get(i).getBookCode());
				}
				break;
			case 3:
				System.out.println("종료");
				run = false;
				break;
			case 4:
				us.logout();
				break;
			default:
				System.out.println("경고 번호를 잘못입력했습니다.");
				break;
			}
		}
	}

	public int personalInfo() {
		int menu = 0;
		System.out.println("────────────────────────────────────────────");
		System.out.println(" 1. 비밀번호 변경 | 2. 회원탈퇴 | 3. 뒤로가기 ");
		System.out.println("────────────────────────────────────────────");
		System.out.printf("입력");
		menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			updateUserPw();
			break;
		case 2:
			deletUser();
			break;
		case 3:
			break;
		default:
			System.out.println("경고 번호를 잘못입력했습니다.");
			break;
		}
		return menu;
	}

	public void commentlist() {
		boolean srun = true;
		while (srun) {
			System.out.println("─────────────────────────────────────");
			System.out.println(" 1. 상세보기 | 2. 댓글 삭제 | 3.뒤로가기");
			System.out.println("─────────────────────────────────────");
			System.out.println("입력 >>");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				boolean go = true;
				while (go) {
					System.out.println("조회할 댓글의 번호를 입력하세요.");
					System.out.println("종료하시려면 exit 를 입력하세요. > ");
					String num = sc.nextLine();
					if (num.toLowerCase().equals("exit")) {
						System.out.println("종료");
						go = false;
					}else {
						num = String.valueOf(bc.getCommentNum());
						if (num == null) {
							System.out.println("해당 댓글 번호는 없는 번호입니다.");
						}else {
							book = BookDAO.getInstance().getBookCode(bc.getBookCode());
							System.out.println(book.getBookName());
							System.out.println(bc.getCommentNum());
							System.out.println(bc.getCommentCon());
						}
					}
				}
				break;
			case 2:

				break;
			case 3:
				srun = false;
				break;
			default:
				System.out.println("경고 번호를 잘못입력했습니다.");
				break;
			}
		}
	}

	public void updateUserPw() {
		User user = new User();
		String insertPw;
		String pw = UserService.userInfo.getUserPw();
		String id = UserService.userInfo.getUserID(); // user id도 입력
		String newpw;
//		System.out.println(id);
//		System.out.println(pw);
		System.out.println("───────────────────────────────────");
		System.out.printf("기존 비밀번호를 입력하세요. >");
		insertPw = sc.nextLine();
		// pw; //id랑 똑같이 기존에 들어있는 값을 불러옴.
		// 앞에 뭐가 더 있나 그런듯.userInfo.getUserPw();
		if (pw.equals(insertPw)) {
			System.out.println("변경할 비밀번호를 입력하세요.");
			System.out.printf("입력 >> ");
			newpw = sc.nextLine();
			user.setUserID(id);
			user.setUserPw(newpw);
			int result = UserDAO.getInstance().updateUserPw(user);
			System.out.println("변경 완료. 재접속하세요.");
			System.out.println("───────────────────────────────────");
			us.logout();
		} else {
			System.out.println("비밀번호가 틀립니다.");
			System.out.println("비밀번호 변경 불가");
		}

	}

	public void deletUser() {
		String id = UserService.userInfo.getUserID(); // 아이디 받아오기
		int result = UserDAO.getInstance().deletUser(id);

		if (result == 1) {
			System.out.println("탈퇴 완료");
			// 로그아웃해야하나?
		} else {
			System.out.println("탈퇴 불가");
		}

	}

}
