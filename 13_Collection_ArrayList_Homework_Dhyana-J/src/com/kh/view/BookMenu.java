package com.kh.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.kh.controller.BookManager;
import com.kh.model.vo.Book;

public class BookMenu {

	private Scanner sc = new Scanner(System.in);
	private BookManager bm = new BookManager();

	public BookMenu() {}

	public void mainMenu() {
		while(true) {
			System.out.println();
			System.out.println("*** 도서 관리 프로그램 ***");
			System.out.println("1. 새 도서 추가");
			System.out.println("2. 도서 삭제");
			System.out.println("3. 도서 검색 출력");
			System.out.println("4. 전체 출력");
			System.out.println("0. 끝내기");
			System.out.print("메뉴 번호 선택 : ");
			String menu = sc.nextLine();

			switch(menu) {
			case "1": insertBook();break;
			case "2": deleteBook();break;
			case "3": searchBook();break;
			case "4": selectList();break;
			case "0": System.out.println("프로그램 종료"); return;
			default: System.out.println("잘못 눌렀습니다.");
			}
		}

	}

	public void insertBook() {
		//도서제목 입력
		System.out.print("\n도서 제목 : ");
		String title = sc.nextLine();

		//도서 장르 입력
		int category = 0; //입력받을 도서 장르 저장하는 변수
		while(true) {
			try {
				Scanner input = new Scanner(System.in);// 이거 안하고 전역 sc쓰면 무한루프에빠짐..

				System.out.println("");
				System.out.print("도서 장르 (1:인문 / 2:자연과학 / 3:의료 / 4:기타) : ");
				//				category = sc.nextInt(); //이거쓰면 예외발생 후 무한루프돈다. 
				category = input.nextInt();
				input.nextLine();//버퍼비움

				switch(category) {
				case 1:break;
				case 2:break;
				case 3:break;
				case 4:break;
				default://1~4 이외 숫자 입력 시 다시입력받자.
					System.out.println("1~4사이 숫자를 입력하세요!");
					continue;
				}
				break; //정상입력된 경우 반복문 빠져나간다.

			}catch(InputMismatchException e){ //예외발생 시 종료시킨다.
				System.out.println("숫자를 입력하세요!");
			}
			continue; //숫자 이외의 값 입력한 경우 다시 입력받는다.
		}

		//도서 저자 입력
		System.out.print("도서 저자 : ");
		String author = sc.nextLine();

		bm.insertBook(new Book(title,category,author));

	}

	public void deleteBook() {
		
		//삭제할 도서 번호 입력
		int bNo = 0; //삭제할 도서 번호 저장할 변수
		while(true) {//숫자 이외값 입력시 예외처리. 다시입력받는다.
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("도서 번호 : ");
				bNo = input.nextInt();
				input.nextLine();//버퍼비우자
				break;//정상입력시 반복문 종료
			}catch(InputMismatchException e) {
				System.out.println("숫자를 입력하세요!!");
			}
		}
		
		if(bm.deleteBook(bNo)==1) { // return값 1인 경우
			System.out.println("성공적으로 삭제");
		}
		else {// return값 0인경우
			System.out.println("삭제할 도서가 존재하지 않습니다.");
		}
	}

	public void searchBook() {

		System.out.print("도서 제목 : ");
		String title = sc.nextLine();

		if(bm.searchBook(title).isEmpty()) {//searchList비어있을 경우
			System.out.println("검색 결과가 존재하지 않습니다.");
		}
		else {//searchList출력
			for(Book e : bm.searchBook(title)) {
				System.out.println(e);
			}
		}
	}

	public void selectList() {
		if(bm.selectList().isEmpty()) {//selectList비어있을경우
			System.out.println("도서 목록이 존재하지 않습니다.");
		}
		else {
			for(Book e : bm.selectList()) {
				System.out.println(e);
			}
		}
	}

}
