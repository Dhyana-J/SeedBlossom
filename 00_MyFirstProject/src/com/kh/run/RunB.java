package com.kh.run;

import com.kh.first.B_ValuePrinter;


public class RunB {
	// main메소드 (실행메소드)
	public static void main(String[] args) {
		//다른 클래스에 있는 메소드 호출
		// 1) 클래스 생성
		B_ValuePrinter b = new B_ValuePrinter();
		
		// 2) 메소드 호출
//		b.printValue();
		b.sumStringNum();
	}
}


