package com.exe;

import java.util.Scanner;

import com.book.BookService;
import com.checkout.CheckoutService;
import com.notice.NoticeService;
import com.user.User;
import com.user.UserDAO;
import com.user.UserMyService;
import com.user.UserService;

public class ExeApp {
	Scanner sc = new Scanner(System.in);
	UserService us = new UserService();
	AdminMenu am = new AdminMenu();
	NoticeService ns = new NoticeService();
	BookService bs = new BookService();
	CheckoutService cs = new CheckoutService();
	UserMyService ums = new UserMyService();
	public ExeApp() {
		start();
	}
	
	private void start() {
		int menu = 0;
		boolean run = true;
		while (run) {
			//로그인 되었을 때 메뉴
			if(UserService.userInfo != null) {
				if(UserService.userInfo.getUserRank().equals("A")) {
					System.out.println("================================================= 관 리 자 모 드 =================================================");
					System.out.println("  1. 회원관리 | 2. 열람실관리 | 3.오프라인대출/반납 | 4. 도서관리 | 5. 신청도서관리 | 6. 도서관홈페이지 | 7. 로그아웃");
					System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.printf("관리자 모드>>");
					menu = Integer.parseInt(sc.nextLine());
					System.out.println();
					switch (menu) {
					case 1:
						am.adminswitch();
						break;
					case 2:
						
						break;
					case 3:
						
						break;
					case 4:
						
						break;
					case 5:
						
						break;
					case 6:
						
						break;
					case 7:
						System.out.println("로그아웃 완료");
						us.logout();
						break;
					default:
						System.out.println("경고 번호를 잘못입력했습니다.");
						break;
					}
				}else {
					System.out.println("========================================= 메 인 페 이 지 ===========================================");
					System.out.println("  1. 공지사항 | 2.  도서조회 | 3. 열람실예약 | 4. 도서대출/열람 | 5. 마이페이지 | 6. 로그아웃");
					System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.printf("메인 페이지>>");
					menu = Integer.parseInt(sc.nextLine());
					switch (menu) {
					case 1:
						ns.getNotice();
						break;
					case 2:
						bs.getBook();
						break;
					case 3:
						System.out.println("열람실 공사로 이용이 불가합니다.");
						break;
					case 4:
						cs.Checkout();
						break;
					case 5:
						ums.userMylist();
						break;
					case 6:
						System.out.println("안녕히 가세요");
						us.logout();
						break;
					default:
						System.out.println("경고 번호를 잘못입력했습니다.");
						break;
					}
				
				}
			}
			else if (UserService.userInfo == null) {
				System.out.println("============== 도 서 관  ==============");
				System.out.println("1. 로그인 | 2. 회원 가입 | 3. 종료");
				System.out.println("──────────────────────────────────────");
				System.out.printf(">>");
				menu = Integer.parseInt(sc.nextLine());
				
				switch (menu) {
				case 1:
					us.login();
					break;
				case 2:
					System.out.println("회원가입이 불가합니다.");
					break;
				case 3:
					System.out.println("프로그램 종료");
					run = false;
					break;
				default:
					System.out.println("경고 번호를 잘못입력했습니다.");
					break;
				}
				
			}			
		}
	}
	
	
}
