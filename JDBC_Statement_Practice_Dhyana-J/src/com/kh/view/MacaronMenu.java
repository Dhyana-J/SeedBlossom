package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MacaronController;
import com.kh.model.vo.Macaron;

//View : 사용자가 보게 될 첫 화면 담당. 
public class MacaronMenu {
	
	//전역으로 쓸 수 있게 스캐너 생성
	private Scanner sc = new Scanner(System.in);
	
	//전역으로 쓸 수 있게 Controller객체 생성
	private MacaronController mc = new MacaronController(); //생성자 없어도 알아서 만들어줌.
	
	/*사용자가 보게 될 첫 화면*/
	public void mainMenu() {
		String menu; // 왜 String인가? -> 예외처리 피하기 위해.
		
		while(true) {
			System.out.println("안내 : 이 냉장고는 10개의 마카롱 보관이 가능합니다.");
			System.out.println("\n===== 마카롱 미니 냉장고 =====");
			System.out.println("1. 마카롱 보관하기");
			System.out.println("2. 마카롱 목록 보기");
			System.out.println("3. 마카롱 이름으로 찾기");
			System.out.println("4. 마카롱 색깔로 찾기");
			System.out.println("5. 마카롱 변신시키기");
			System.out.println("6. 마카롱 꺼내먹기");
			System.out.println("0. 프로그램 종료");
			System.out.print("번호 선택 : ");
			
			
			menu = sc.nextLine();
			
			switch(menu) {
			case "1": insertMacaron(); break;
			case "2": mc.selectList(); break;
			case "3": mc.selectByMacaronName(inputMacaronName()); break;
			case "4": mc.selectByMacaronColor(inputMacaronColor()); break; 
			case "5": updateMacaron(); break;
			case "6": deleteMacaron(); break;
			case "0": System.out.println("프로그램을 종료합니다~"); return;
			default : System.out.println("예...?\n");
			}
		}
		
	}
	
	/**
	 * 마카롱 냉장고에 보관 메소드
	 * 	 */
	public void insertMacaron() {
		
		
		System.out.println("\n===== 마카롱 보관하기 =====");
		System.out.print("마카롱 이름 입력 : ");
		String name = sc.nextLine();
		System.out.print("색깔 입력 : ");
		String color = sc.nextLine();
		System.out.print("맛 입력 : ");
		String flavor = sc.nextLine();
		
		mc.insertMacaron(new Macaron(name,color,flavor));
		
	}
	
	//결과 출력 메소드
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공! : "+message);
	}
	
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패! : "+message);
	}
	
	public void displayNoData(String message) {
		System.out.println("\n"+message);
	}
	
	
	public void displayMacaron(Macaron m) {
		System.out.println("\n아래와 같은 마카롱을 찾았어요!!\n");
		System.out.println(m);
		System.out.println();
	}
	
	public void displayMacaronList(ArrayList<Macaron> list) {
		System.out.println("\n=== 보관되어있는 마카롱들 === ");
		System.out.println("\n<총 "+list.size()+"개의 마카롱 보관중>");
		for(int i = 0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println();
	}
	
	public String inputMacaronName() {
		System.out.print("마카롱 이름 입력 : ");
		return sc.nextLine();
	}
	
	public String inputMacaronColor() {
		System.out.print("마카롱 색깔 입력 : ");
		return sc.nextLine();
	}
	
	public void updateMacaron() {
		
		System.out.println("\n === 마카롱 변신시키기 === ");
		
		Macaron m = new Macaron();
		
		m.setName(inputMacaronName());
		//이름도 변경할 수 있게끔 하면 어떨까? 이름 겹치면 변경 실패
		System.out.print("변경할 맛 : ");
		m.setFlavor(sc.nextLine());
		
		System.out.print("변경할 색상 : ");
		m.setColor(sc.nextLine());
		
		
		mc.updateMacaron(m);
		
	}
	
	public void deleteMacaron() {
		System.out.println("\n === 마카롱 꺼내먹기 ===");
		System.out.print("꺼내먹을 마카롱 이름 입력 : ");
		
		mc.deleteMacaron(sc.nextLine());
	}
	
	

}
