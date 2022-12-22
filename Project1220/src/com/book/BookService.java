package com.book;

import java.util.List;
import java.util.Scanner;

import com.user.UserService;

public class BookService {
	Scanner sc = new Scanner(System.in);

	Book book = new Book();
	BookComment bc = new BookComment();
	String name = null;
	String code = null;

	public void getBook() {
		boolean run = true;

		while (run) {
			System.out.println("================== 도서조회 ===================");
			System.out.println(" 1. 통합도서조회 | 2. 희망도서 신청 | 3. 홈으로");
			System.out.println("───────────────────────────────────────────────");
			System.out.printf("입력 >> ");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				boolean flag = true;
				BookDAO.getInstance().page = 1;
				while (flag) {
					System.out.println("──────────────────────────────────");
					System.out.println("\t< 도서 리스트 >");
					getBookList();
					System.out.println("──────────────────────────────────");
					System.out.printf("\t   ");
					System.out.print(BookDAO.getInstance().page);
					System.out.print(" / ");
					pageCount();
					System.out.println();
					System.out.println("────────────────────────────────────");
					System.out.println("1.상세보기 | 2.이전 | 3. 다음| 4. 홈");
					System.out.println("────────────────────────────────────");
					System.out.printf("입력 >");
					int menu = Integer.parseInt(sc.nextLine());
					switch (menu) {
					case 1:
						boolean srun = true;
						while (srun) {
							System.out.println("조회할 도서 제목을 입력하세요.");
							System.out.printf("종료하시려면 exit를 입력하세요. >");
							name = sc.nextLine();
							if (name.equals("exit")) {
								System.out.println("종료");
								srun = false;
							} else {
								book = BookDAO.getInstance().getBookInfo(name);
								if (book == null) {
									System.out.println("해당 도서는 없는 도서 입니다.");
								} else {
									System.out.println("────────────── 도서 정보 출력 ──────────────");
									System.out.println("제목 	: " + book.getBookName());
									System.out.println("저자 	: " + book.getBookAuth());
									System.out.println("출판사 	: " + book.getPublisher());
									System.out.println("유형 	: " + book.getBooktype());
									System.out.println("도서코드 	: " + book.getBookRCode());
									//System.out.println("평균별점 	: " + book.getStarAvg());
									System.out.println("온오프 	: " + book.getOnOffInfo());
									System.out.println("────────────────────────────────────────────");
								//댓글 메뉴 들어감
									getCommentList();
									commentmenu();
										
									}
								}
							}
						break;
					case 2:
						BookDAO.getInstance().pageback();
						break;
					case 3:
						BookDAO.getInstance().pageforward();
						break;
					case 4:
						flag = false;
						break;
					default:
						System.out.println("경고 번호를 잘못입력했습니다.");
						break;
					}
				}
				break;
			case 2:
				applyMenuList();
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

	public void getCommentList() {
		System.out.println("comment");
//		System.out.println(name);
		book = BookDAO.getInstance().getBookInfo(name);
		String bookcode = book.getBookRCode();
//		System.out.println(bookcode);

		List<BookComment> list = BookCommentDAO.getInstance().getCommentList(bookcode);
		for(int i = 0; i <list.size(); i++) {
			System.out.println(list.get(i).getUserId() + "\t 별점 : " +list.get(i).getUserStar());
			System.out.println(list.get(i).getCommentCon());
		}
	}

	public void commentmenu() {
		BookComment bc = new BookComment();
		String id;
		String bookcode;
			System.out.println("─────────────────────────────────");
			System.out.println(" 1. 댓글 작성 | 2. 뒤로가기 ");
			System.out.println("─────────────────────────────────");
			System.out.println("입력 >>");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				book = BookDAO.getInstance().getBookInfo(name);
				bookcode = book.getBookRCode();
				id = UserService.userInfo.getUserID();
				bc.setBookCode(bookcode);
				bc.setUserId(id);
				bc = BookCommentDAO.getInstance().commentRight(bc);
				code = BookCommentDAO.getInstance().code;
				System.out.println(bookcode);
				if (bc == null) {
					System.out.println("도서를 읽은 후 댓글 작성이 가능합니다.");
				}else {
					bc.setUserId(id);
					bc.setBookCode(bookcode);
					System.out.println("댓글 작성");
					System.out.println("내용을 입력하세요. 300자이내");
					String con = sc.nextLine();
					System.out.println("별점");
					int star = Integer.parseInt(sc.nextLine());
					
					bc.setCommentCon(con);
					bc.setUserStar(star);
					
					int result = BookCommentDAO.getInstance().insertCommnet(bc);
					
					if (result == 1) {
						System.out.println("등록 완료");
					} else {
						System.out.println("등록 실패");
					}
				}

				break;
			case 2:
				break;
			default:
				break;
		}
	}
	
	
	
	
	

	public void getBookList() {
		List<Book> list = BookDAO.getInstance().getBookList();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getListnum()+ " " +list.get(i).getBookName() + " " + list.get(i).getBookAuth());
		}
	}

	public void bookListMenu() {

	}

	public void pageCount() {
		BookDAO.getInstance().pageCount();
	}

	
	public void applyMenuList() {

		boolean run = true;
		boolean flag = true;
		while (run) {
			System.out.println("───────────────────────────────────────────");
			System.out.println(" 1.도서신청 | 2.신청 도서 조회 | 3. 뒤로가기");
			System.out.println("───────────────────────────────────────────");
			System.out.printf("입력 >");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				insertApplyBook();
				break;
			case 2:
				flag = true;
				ApplyDAO.getInstance().page = 1;
				while (flag) {
					System.out.println("────────────────────── 신청 도서 조회 ────────────────────────");
					getApplyList();
					System.out.println("───────────────────────────────────────────────────────────");
					System.out.printf("\t\t\t");
					System.out.print(ApplyDAO.getInstance().page);
					System.out.print(" / ");
					applyPageCount();
					System.out.println();
					System.out.println("─────────────────────────");
					System.out.println(" 1.이전 | 2. 다음| 3. 홈");
					System.out.println("─────────────────────────");
					System.out.printf("입력 >");
					int num = Integer.parseInt(sc.nextLine());
					switch (num) {
					case 1:
						ApplyDAO.getInstance().pageback();
						break;
					case 2:
						ApplyDAO.getInstance().pageforward();
						break;
					case 3:
						flag = false;
						break;
					default:
						System.out.println("경고 번호를 잘못입력했습니다.");
						break;

					}
				}
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

	public void applyPageCount() {
		ApplyDAO.getInstance().pageCount();
	}

	// 도서신청
	public void insertApplyBook() {
		Apply apply = new Apply();
		String id = UserService.userInfo.getUserID();
		String name = "";
		String author = "";
		String publisher = "";
		System.out.println("─────────────────────────");
		System.out.println("신청 정보 입력");
		System.out.printf("도서제목 	: ");
		name = sc.nextLine();
		System.out.printf("저자 	: ");
		author = sc.nextLine();
		System.out.printf("출판사 	: ");
		publisher = sc.nextLine();
		System.out.println("─────────────────────────");

		apply.setUserId(id);
		apply.setApplyname(name);
		apply.setApplyauth(author);
		apply.setApplypubli(publisher);

		int result = ApplyDAO.getInstance().insertApplyBook(apply);

		if (result == 1) {
			System.out.println("신청완료");
		} else {
			System.out.println("신청이 불가합니다.");
		}

	}

	// 신청도서조회
	public void getApplyList() {
		List<Apply> list = ApplyDAO.getInstance().getApplyList();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getUserId() + " " + list.get(i).getApplyname() + " "
					+ list.get(i).getApplyauth() + " " + list.get(i).getApplypubli() + " " + list.get(i).getApplyDate()
					+ " " + list.get(i).getApplyStep());
		}
	}
	
	
}
