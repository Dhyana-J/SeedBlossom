package com.kh.first; // 패키지 선언부!

public class A_MethodPrinter {
	//class명은 대문자, method명은 소문자로 시작해야한다.
	
	// 기능 단위 == 메소드 
	// main 메소드는 클래스마다 있을 필요가 없다. 한프로젝트당 적어도 1개는 반드시 있어야함
	
	// 일반메소드 public void 메소드명(){} 
	public void mA() {
		System.out.println("mA가 호출되었다");
		mB();
		mC();
	}
	
	public void mB() {
		System.out.println("mB가 호출되었습니다");
	}
	
	public void mC() {
		System.out.println("mC가 호출되었소요");
	}

}
