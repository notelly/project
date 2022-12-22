package com.checkout;

import java.util.List;
import java.util.Scanner;

import com.user.UserService;

public class CheckoutService {
	Scanner sc = new Scanner(System.in);
	

	public void Checkout() {
		Checkout checkout = new Checkout();
		boolean run = true;
		String bookname = null;
		String id = "";
		String bookcode = "";
		int count = 0;
		while (run) {
			System.out.println("====================== 온라인 대출 / 반납 ======================");
			System.out.println(" 1.온라인 도서 대출 | 2. 대출조회/반납 | 3. 뒤로가기 ");
			System.out.println("───────────────────────────────────────────────────────────────");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.println("─────────────── 온라인 도서 대출 ───────────────");
				System.out.printf("도서 제목 입력 ");
				bookname = sc.nextLine();
				checkout = CheckoutDAO.getInstance().checkright(bookname);
				bookcode = CheckoutDAO.getInstance().bc;
				if (checkout == null) {// 왜 안됐다가 되냐?
					System.out.println("대출 불가한 도서입니다.");
				} else {
					// 유저 아이디를 가지고 오는 방법
					// UserService.userInfo.getUserID()
					//UserService에 userInfo에 로그인한 user에 대한 정보가 다 들어있다.
					//불러오려면 새로운 객체를 만드는게 아니라 class.userInfo로 값을 불러와야한다.
					id = UserService.userInfo.getUserID();
					checkout.setUserId(id);
					checkout = CheckoutDAO.getInstance().checkCount(id);
					count = CheckoutDAO.getInstance().count;
					if (count == 2) {
						System.out.println("2권이상 대출이 불가능합니다.");
					} else {
						checkout.setUserId(id);
						checkout.setBookCode(bookcode);
						int result = CheckoutDAO.getInstance().checkout(checkout);

						if (result == 1) {
							System.out.println("대출 완료");
						} else {
							System.out.println("대출 실패");
						}

					}
					System.out.println("───────────────────────────────────────────");
				}
				break;
			case 2:
				checkReturn();
				break;
			case 3:
				run = false;
				break;
			default:
				System.out.println("경고 번호를 잘못입력했습니다.");
				break;
			}

		}

	}

	public void checkReturn() {
		Checkout checkout = new Checkout();
		boolean flag = true;
		String bookname = null;
		String id = "";
		String bookcode = "";
		while (flag) {
			System.out.println("────────────── 대출 조회/반납 ──────────────");
			System.out.println("  1. 대출조회 | 2. 반납 | 3. 뒤로가기 ");
			System.out.println("─────────────────────────────────────────");
			System.out.println("입력 >> ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.println("──────────────── 대출조회 ────────────────");
				id = UserService.userInfo.getUserID();
				List<Checkout> list = CheckoutDAO.getInstance().ShowCheckoutList(id);
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i).getBookName() + " " + list.get(i).getBookCode() + " "
							+ list.get(i).getCheckoutDate().substring(0, 10) + " "
							+ list.get(i).getReturnDate().substring(0, 10));
				}
				System.out.println("────────────────────────────────────────");
				break;
			case 2:
				System.out.println("────────────── 반 납 ──────────────");
				System.out.println("반납할 책을 입력하세요.");
				bookname = sc.nextLine();
				checkout = CheckoutDAO.getInstance().checkright(bookname);
				bookcode = CheckoutDAO.getInstance().bc;
				if (checkout == null) {// 왜 안됐다가 되냐?
					System.out.println("대출 목록에 없는 도서입니다.");
				} else {
					System.out.println("────────────────────────────────────");
					int result = CheckoutDAO.getInstance().returnBook(bookcode);

					if (result == 1) {
						System.out.println();
					} else {
						System.out.println();
					}
				}
				break;
			case 3:
				flag = false;
				break;
			default:
				System.out.println("경고 번호를 잘못입력했습니다.");
				break;
			}

		}

	}

}
