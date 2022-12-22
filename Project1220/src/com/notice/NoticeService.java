package com.notice;

import java.util.List;
import java.util.Scanner;

public class NoticeService {
	Scanner sc = new Scanner(System.in);
	Notice notice = new Notice();

	public void getNotice() {
		boolean run = true;
		NoticeDAO.getInstance().page = 1;
		while (run) {
			System.out.println("========================= 공 지 사 항 =========================");
			System.out.println("번호\t제목\t\t\t\t작성일");
			System.out.println("────────────────────────────────────────────────────────────");
			getNoticeList();
			System.out.println("────────────────────────────────────────────────────────────");
			System.out.print("\t\t\t");
			System.out.print(NoticeDAO.getInstance().page);
			System.out.printf("/");
			pageCount();
			System.out.println();
			System.out.println("────────────────────────────────────────────────────────────");
			System.out.println("1. 상세보기 | 2. 이전페이지 | 3. 다음페이지 | 4. 홈으로");
			System.out.println("────────────────────────────────────────────────────────────");
			System.out.printf("선택 >");
			int menu = Integer.parseInt(sc.nextLine());
			boolean flag = true;
			switch (menu) {
			case 1:
				while (flag) {
					System.out.println("조회할 게시물 번호를 입력하세요.");
					System.out.println("종료하시려면 exit 를 입력하세요. > ");
					String postNum = sc.nextLine();

					if (postNum.toLowerCase().equals("exit")) {
						System.out.println("종료");
						flag = false;
					} else {
						notice = NoticeDAO.getInstance().getpostContent(postNum);
						if (notice == null) {
							System.out.println("해당 게시물 번호는 없는 게시물입니다.");
						} else {
							System.out.println(notice.getPostNum() + "\t" + notice.getPostTitle() + "\t"
									+ notice.getPostDate() + "\n" + notice.getPostCon());
							System.out.println("");
						}
					}
				}
				break;
			case 2:
				NoticeDAO.getInstance().pageback();
				break;
			case 3:
				NoticeDAO.getInstance().pageforward();
				break;
			case 4:
				run = false;
				break;
			default:
				System.out.println("경고 번호를 잘못입력했습니다.");
				break;
			}
		}

	}

	public void getNoticeList() {
		List<Notice> list = NoticeDAO.getInstance().getNotice();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(
					list.get(i).getPostNum() + "\t" + list.get(i).getPostTitle() + "\t" + list.get(i).getPostDate());
		}
	}


	public void pageCount() {
		NoticeDAO.getInstance().pageCount();
	}

}
