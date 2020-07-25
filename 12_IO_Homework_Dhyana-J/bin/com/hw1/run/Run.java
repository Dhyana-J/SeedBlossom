package com.hw1.run;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.hw1.model.dao.FileDao;

public class Run {

	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(System.in);
		
		FileDao fd = new FileDao();
		
		//반복문을 통한 메뉴 실행
		while(true) {
			System.out.println();
			System.out.println("***** MyNote *****");
			System.out.println("1. 노트 새로 만들기");
			System.out.println("2. 노트 열기");
			System.out.println("3. 노트 열어서 수정하기");
			System.out.println("4. 노트 목록 보기");
			System.out.println("5. 끝내기");
			System.out.print("번호를 입력하세요 : ");
			
			try {
				String input = sc.nextLine();

				switch(input) {
				case "1": fd.fileSave();break; 
				case "2": fd.fileOpen();break;
				case "3": fd.fileEdit();break;
				case "4": fd.printFileList(); break;
				case "5": System.out.println("프로그램을 종료합니다."); return;
				default: System.out.println("잘못 누르셨습니다. 1~4 사이 숫자를 입력하세요");
				}
			}
			catch(NoSuchElementException e) { //혹시나 입력값이 이상하면 예외처리. 입력값으로 ctrl+z를 눌렀다든가?
				System.out.println("\n강제종료를 입력했습니다. 프로그램을 종료합니다.");
				System.out.println("예외 내용 : ");
				e.printStackTrace();
				System.exit(0);//프로그램을 바로 종료시켜버린다.
//				exit 사용법 참고 : https://coding-factory.tistory.com/526
//				return; //함수(메소드)를 종료시킨다.
//				exit과 return의 차이? main함수가 종료된 이후의 절차가 어떻게될까? 
			}
			
		}
		
	}
}
