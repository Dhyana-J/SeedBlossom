package com.kh.run;

// 해결방법2.
// 이 클래스 파일이랑 같은 패키지에 A_MethodPrinter 클래스가 없으므로 import 해줘야 그 클래스의 method를 쓸 수 있다.
import com.kh.first.A_MethodPrinter;


public class RunA {
	
	public static void main(String[] args) {
		
		System.out.println("잘 작동합니다");
		
		// 다른 클래스에 있는 메소드를 호출하는 방법!
		
		// 1)실행할 메소드가 있는 클래스를 생성(new)
		// [표현법] "클래스명" "사용할 이름" = new "클래스명"();
		A_MethodPrinter a = new A_MethodPrinter();
		// -> 코드를 기술하고 있는 RunA 클래스가 속해있는 패키지에 호출할 클래스가 없으면 에러난다.
		// 해결방법 1. 생성하고자 하는 클래스가 어떤 패키지에 속해있는지 풀 클래스명을 사용하는 방법
		// com.kh.first.A_MethodPrinter a = new com.kh.first.A_MethodPrinter();
		// 사실 이 패키지 명까지 다 들어간 풀 네임이 원래 클래스명이다. 
		// 이런 식으로 해주거나(권장하지 않음. 매번 할 때마다 풀네임 써주기 힘들다.)
		// 해결방법 2. 그 클래스가 속한 경로를 import 해준다.
		
		// 2) 메소드 호출
		// [표현법] "사용할 이름"."해당 클래스에 들어있는 사용하고자 하는 메소드명"();
		a.mA();
//		a.mB();
//		a.mC();
	}

}
