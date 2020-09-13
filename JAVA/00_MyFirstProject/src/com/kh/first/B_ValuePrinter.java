package com.kh.first;

public class B_ValuePrinter { 
	// com.kh.first.B_ValuePrinter
	// 다양한 종류의 값들을 출력하는 기능
	
	/*
	 * 1. 클래스명 --> 대문자로 시작
	 * 2. 패키지명 --> 소문자로 시작
	 * 3. 메소드명 --> 소문자로 시작
	 * 4. 변수명    --> 소문자로 시작
	 * 
	 * 단, 여러개의 단어가 있을 경우 대문자로 구분해준다. == 낙타표기법
	 * 
	 *  항상 의미를 생각하며 이름을 지어주자. 대강 어떤 의미인지 알아볼 수 있도록!
	 * */
	public void printValue() {
		
		// 1. 숫자 출력 --> 따옴표 없이 
		System.out.println(123);
		System.out.println(1.11);
		
		// 2. 문자(한 글자)
		System.out.println('a');
		System.out.println('#');
		
		// 3. 문자열(여러 글자)
		System.out.println("a#eiojfwe");
		System.out.println("한글도 된다!");
		System.out.println("한칸\n아래로");
		
		// 4. 연산한 결과 값도 출력 가능! 
		System.out.println(3+4);
		System.out.println(3-4);
		System.out.println(4/2);
		System.out.println(4*2);
		System.out.println(1.23-0.23);
		System.out.println(1.23-0.12);//0.11 
		//컴퓨터에서의 실수값 연산은 오차가 생길 수 있다.
		
		// 5. 문자와 숫자도 연산 가능 / 문자마다 컴퓨터가 인식하는 고유한 숫자값이 있다.
		System.out.println('a'+ 1); //a는 97
		System.out.println('A'+ 1); //A는 65
		System.out.println('!'+ 1);
		System.out.println('가'+ 1);
		
		// 6. 문자열("")과 그 외의 값들은 덧셈 연산가능. 결과값은 문자열이다.
		System.out.println("가나다"+'라'+5+"육칠팔구"+'~');
		
	}
	
	// 문자열과 숫자간의 덧셈 연산 기능용 메소드
	public void sumStringNum() {
		System.out.println(9 + 9); // 18
		System.out.println(9 + "9"); // "99"
		System.out.println("9" + 9); // "99"
		
		//연산은 ->방향으로 진행된다. 숫자 연산 이전에 문자열 있으면 문자열화된다.
		System.out.println(9 + 9 + "9"); //189
		System.out.println(9 + "9" + 9); //999
		System.out.println("9" + 9 + 9); //999
		System.out.println("9"+ (9 + 9)); //918
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
