package com.kh.first; //이 클래스가 어디에 위치하는지 알려주는 구문이다. 반드시 필요!

// 주석~ 소스코드로 인식하지 않아요! 코드에 대한 설명 작성 시 씁니다.

/*
 * 이런 식으로도
 * 가능하다.
 * */

public class HelloWorld {

	// function(함수or기능) == Method(메소드)
	// 하나의 클래스 안에 여러 개의 메소드 생성 가능
	// Program 실행 시 가장 먼저 실행되는 메소드가 main (실행 메소드)
	
	public static void main(String[] args) //프로그램 실행 시 가장 먼저 실행되는 메소드
	{
		//화면에 실행 결과를 출력시키고 싶을 때 사용한다.(print, println, printf)
		System.out.println("Hello World :)"); //println 수행하고 줄바꿔준다.
		System.out.print("aaa"); //줄바꿈 안해준다.
		System.out.printf("bbb"); //이것도 마찬가지로 줄바꿈 안해준다.
		System.out.printf("ccc"); 
	
		// 각 클래스마다 역할별로 나눠서 관리를 할 것이다.
		// 각 클래스 안의 코드들도 기능별로 그룹화해 메소드를 따로 작성한다.
		
		
	}
	

}
