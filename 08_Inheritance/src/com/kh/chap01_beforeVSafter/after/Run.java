package com.kh.chap01_beforeVSafter.after;


import com.kh.chap01_beforeVSafter.after.model.vo.Desktop;
import com.kh.chap01_beforeVSafter.after.model.vo.SmartPhone;
import com.kh.chap01_beforeVSafter.after.model.vo.Tv;

public class Run {

	
	public static void main(String[] args) {
		
		Desktop d = new Desktop("삼성","d-01","짱짱데스크탑",1500000,true);
		
		Tv t = new Tv("LG","t-01","얄부리티비",3500000,60);
		
		SmartPhone s = new SmartPhone("Apple","s-01","아이폰SE2",1000000,"SKT");
		
		System.out.println(d.information());
		System.out.println(t.information());
		System.out.println(s.information());
		
		
		
		/*
		 * 상속의 장점
		 * - 보다 적은 양의 코드로 새로운 클래스 작성 가능!
		 * - 코드를 공통적으로 관리하기 때문에 코드의 추가나 변경이 용이하다.
		 * --> 코드의 중복을 제거하여 프로그램의 생산성이나 유지보수에 크게 기여
		 * 
		 * * 상속의 특징
		 * - 클래스간의 상속은 다중상속이 불가하다. 하나의 클래스만 상속 가능하다.
		 * - 아무리 상속이라고 해도, 부모클래스 필드가 private이면 자식이 직접접근 불가하다. protected는 가능.
		 * - 명시되어있지는 않아도, 모든 클래스는 조상 Object 클래스의 후손이다. 태초의 그자, Object!
		 * 
		 */
		

	}

}
