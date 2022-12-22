package com.exe;

import java.util.Scanner;

import com.user.UserService;

public class AdminMenu {
	Scanner sc = new Scanner(System.in);
	UserService us = new UserService();
	int menu;
	
	
	public void adminswitch() {
		System.out.println("───────────────────────── 회 원 관 리 ─────────────────────────");
		System.out.println("  1. 회원조회 | 2. 회원등록 | 3.회원수정 | 4. 회원삭제 | 5. 뒤로가기");
		System.out.println("─────────────────────────────────────────────────────────────");
		System.out.printf("회원관리 >>");
		menu = Integer.parseInt(sc.nextLine());
		
		userManage:
			switch (menu) {
		case 1: //회원관리
			userLookup();
			break;
		case 2: //회원등록
			us.insertUser();
			break;
		case 3: //회원수정
			System.out.println("──────────────────────────────────────── 회원정보수정 ─────────────────────────────────────────");
			System.out.println("  1. 비밀번호수정 | 2. 등급수정 | 3. 전화번호수정 | 4. 대출횟수(월)수정 | 5. 대출횟수(총)수정 | 6. 전체수정");
			System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("정보수정 >> ");
			menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1: 
				us.updateUserPw();
				break;
			case 2: 
				us.updateUserRank();
				break;
			case 3: 
				us.updateUserNum();
				break;
			case 4: 
				us.updateUserCO();
				break;
			case 5: 
				us.updateUserTotalCO();
				break;
			case 6: 
				us.updateUserInfo();
				break;
			default:
				System.out.println("⚡경고⚡ 번호를 잘못입력했습니다.");
				break;
			}
			break;
		case 4: //회원삭제
			us.deletuser();			
			break;
		case 5: //뒤로가기
			break userManage;
		default:
			System.out.println("⚡경고⚡ 번호를 잘못입력했습니다.");
			break;
		}
	}
	
	private void userLookup() {
		int menu = 0;
		
		System.out.println(" 1. 전체 조회 | 2. 단일 조회");
		System.out.printf("조회 >>");
		menu = Integer.parseInt(sc.nextLine());

		switch (menu) {
		case 1:
			us.getUserList();
			break;
		case 2:
			us.getUser();
			break;
		default:
			System.out.println("⚡경고⚡ 번호를 잘못입력했습니다.");
			break;
		}
		
		
		
	}
	
	
	
	
	
	
}
