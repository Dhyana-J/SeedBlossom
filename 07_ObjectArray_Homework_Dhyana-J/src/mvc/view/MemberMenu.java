package mvc.view;

import java.util.Scanner;
import mvc.model.vo.*;
import mvc.controller.MemberController;

public class MemberMenu {
	
	private MemberController mc = new MemberController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		
		while(true) {
			System.out.println();
			System.out.println("===== 회원 관리 메뉴 =====");
			System.out.println("1. 신규 회원 등록");
			System.out.println("2. 회원 정보 검색");
			System.out.println("3. 회원 정보 수정");
			System.out.println("4. 회원 정보 삭제");
			System.out.println("5. 회원 정보 출력");
			System.out.println("9. 프로그램 종료");
			
			System.out.print("원하는 메뉴 입력 : ");
			int input = sc.nextInt();
			System.out.println();
			switch(input) {
			case 1: insertMember(); break;
			case 2:	searchMember(); break;
			case 3:	updateMember(); break;
			case 4:	deleteMember(); break;
			case 5:	printAllMember(); break;
			case 9:	System.out.println("프로그램을 종료합니다."); return;
				default : System.out.println("잘못 누르셨습니다.");
			}
			
		}
		
	}
	
	public void insertMember() {
		//현재 회원 수가 최대 회원 수 넘어선 경우 return
		if(mc.getMemberCount()>mc.SIZE) {
			System.out.println("회원 수가 최대입니다. 더 이상 추가할 수 없습니다.");
			return;
		}
		else {
			String id;
			String pw;
			String name;
			int age;
			char gender;
			String email;
			
			System.out.print("Id 입력 : ");
			id = sc.nextLine();
			
			if(mc.checkId(id)!=null) { //동일한 아이디가 존재하는 경우
				System.out.println("동일한 아이디가 존재합니다. 회원등록 실패");
			}
			else {
				sc.nextLine();
				System.out.print("Pw 입력 : ");
				pw = sc.nextLine();
				System.out.print("이름 입력 : ");
				name = sc.nextLine();
				System.out.print("나이 입력 : ");
				age = sc.nextInt();
				System.out.print("성별 입력 : ");
				sc.nextLine();
				gender = sc.nextLine().charAt(0);
				System.out.print("이메일 입력 : ");
				email = sc.nextLine();
				
				//객체 생성 및 메소드 전달
				mc.insertMember(new Member(id,pw,name,age,gender,email));
				
				System.out.println("회원 등록에 성공했습니다.");
			}
		}
		
		
	}
	
	public void searchMember() {
		
		while(true) {
			
			System.out.println();
			System.out.println("====== 회원 정보 검색 ======");
			System.out.println("1. 아이디로 검색하기");
			System.out.println("2. 이름으로 검색하기");
			System.out.println("3. 이메일로 검색하기");
			System.out.println("9. 이전 메뉴로");
			
			System.out.print("메뉴 선택 : ");
			int menu = sc.nextInt(); //메뉴를 입력받자
			
			if(menu == 1||menu==2||menu==3) {
				System.out.print("검색 내용 : ");
				sc.nextLine(); //버퍼 비워주자.
				String search = sc.nextLine();
			
				if(mc.searchMember(menu, search)==null) {
					System.out.println("검색된 결과가 없습니다.");
				}else {
				//검색된 내용이 포함된 멤버 정보 출력
				System.out.println(mc.searchMember(menu, search).information());
				}
				break;
			}
			else if (menu==9) {
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			}
			else {
				System.out.println("잘못 누르셨습니다.");
			}
		
			
			
			System.out.println("잘못 입력하셨습니다.");
			
			}
			
		
		

	}
	
	public void updateMember() {
		
		while(true) {
			System.out.println("====== 회원 정보 수정 ======");
			System.out.println("1. 비밀번호 수정");
			System.out.println("2. 이름 수정");
			System.out.println("3. 이메일 수정");
			System.out.println("9. 이전 메뉴로");
			
			System.out.print("메뉴 선택 : ");
			int menu = sc.nextInt();
			
			if(menu == 1||menu==2||menu==3) {
				sc.nextLine();//버퍼비워주자
				System.out.print("기존 회원 아이디 : ");
				String userId = sc.nextLine();
				
				Member m = mc.checkId(userId);
				if(m == null) {
					System.out.println("변경할 회원이 존재하지 않습니다.");
				}
				else {
					//기존 정보 출력 후 변경내용 입력받는다.
					System.out.println(m.information());
					System.out.print("변경 내용 : ");
					String update = sc.nextLine();
					mc.updateMember(m, menu, update);
					System.out.println("회원 정보가 변경되었습니다.");
				}
			}
			else if (menu==9) {
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			}
			else {
				System.out.println("잘못 입력했습니다.");
			}
			
			
			
		
		}
	}
		
	
	public void deleteMember() {
		
		sc.nextLine();
		System.out.print("삭제할 회원 아이디 : ");
		String userId = sc.nextLine();
		
		if(mc.checkId(userId)==null) {
			System.out.println("삭제할 회원이 존재하지 않습니다.");
			return;
		}
		
		else {
			System.out.print("정말 삭제하시겠습니까?(y/n):");
			String input = sc.nextLine().toUpperCase();
			
			if(input.equals("Y")) {
				
				mc.deleteMember(userId);
				
				System.out.println("회원 정보가 삭제되었습니다.");
			}
			else {
				System.out.println("취소되었습니다.");
				return;
			}
		}
	}
	
	public void printAllMember() {
		
		for(int i = 0; i<mc.getMemberCount();i++) {
			System.out.println(mc.getMem()[i].information());
		}
		
	}

}
