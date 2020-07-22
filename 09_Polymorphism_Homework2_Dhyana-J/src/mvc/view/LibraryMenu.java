package mvc.view;

import java.util.Scanner;

import mvc.controller.LibraryManager;
import mvc.model.vo.Book;
import mvc.model.vo.Member;

public class LibraryMenu {
	
	private LibraryManager lm = new LibraryManager();
	private Scanner sc = new Scanner(System.in);
	
	
	/**
	 * 회원 이름,나이,성별을 입력받아 Member객체 생성 후
	 * 해당 객체를 LibraryManager의 insertMember 메소드로 전달한다.
	 */
	public void mainMenu() {
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();//버퍼비워주자
		System.out.print("성별(F/M) : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);
		
		//성별 잘못입력한 경우
		//exception handling 필요
		if(gender!='F'&&gender!='M') {
			System.out.println("잘못 입력했습니다. 프로그램을 종료합니다.");
			return;
		}
		
		//이름, 나이, 성별을 키보드로 입력 받은 후 Member 객체 생성
		Member m = new Member(name,age,gender,0);
		
		//LibraryManager의 insertMember() 메소드에 전달
		lm.insertMember(m); 
		
		//메뉴 무한반복 실행
		while(true) {
			
			System.out.println("");
			System.out.println("==== 메뉴 ====");
			System.out.println("1. 마이페이지");
			System.out.println("2. 도서 전체 조회");
			System.out.println("3. 도서 검색");
			System.out.println("4. 도서 대여하기");
			System.out.println("0. 프로그램 종료하기");
			
			System.out.print("\n메뉴를 선택하세요 : ");
			byte input = sc.nextByte(); // exception handling 필요
			sc.nextLine();//버퍼비워주자.
			
			System.out.println();
			switch(input) {
			case 1: System.out.println(lm.myInfo().toString()); break;
			case 2:	selectAll(); break;
			case 3:	searchBook(); break;
			case 4:	rentBook(); break;
			case 0:	System.out.println("프로그램을 종료합니다."); return;
			default: System.out.println("잘못 누르셨습니다."); continue;
			}
			
		}
		
		
		
		
	}
	
	public void selectAll() {
		
		Book[] bList = lm.selectAll();
		
		for(int i = 0; i<bList.length; i++) {
			System.out.println(i+"번도서 : "+bList[i].toString());
		}
		
	}
	
	public void searchBook() {
		
		System.out.println("검색할 제목 키워드 : ");
		String keyword = sc.nextLine();
		Book[] searchList = lm.searchBook(keyword);
		
		for(Book e : searchList) {
			if(e == null) break; //배열의 내용이 null이면 반복 중지.
			System.out.println(e.toString());
		}
		
	}
	
	public void rentBook() {
		//도서 대여를 위해 도서번호 알아야함
		lm.selectAll();
		
		System.out.println("대여할 도서 번호 선택 : ");
		int num = sc.nextInt();
		sc.nextLine();//버퍼비운다.
		int result = lm.rentBook(num);
		
		switch(result) {
		case 0: System.out.println("성공적으로 대여되었습니다."); break;
		case 1: System.out.println("나이 제한으로 대여 불가합니다."); break;
		case 2: System.out.println("성공적으로 대여되었습니다. 요리학원 쿠폰이 발급되었습니다.");
		 		System.out.println("마이페이지를 통해 확인하세요"); break;
		}
		
		
	}
	
	

}
