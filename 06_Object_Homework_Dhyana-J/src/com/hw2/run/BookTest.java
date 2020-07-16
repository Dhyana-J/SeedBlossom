package com.hw2.run;
import com.hw2.model.vo.*;

public class BookTest {
	
	public static void main(String[] args) {
		
		Book b1 = new Book();
		Book b2 = new Book("자바의정석",20000,0.2,"윤상섭");
		
		//각 객체 필드 값 출력
		System.out.println(b1.information());
		System.out.println(b2.information());
		System.out.println("=========================");
		
		//첫 번째 객체 값 수정
		b1.setTitle("C언어");
		b1.setPrice(30000);
		b1.setDiscountRate(0.1);
		b1.setAuthor("홍길동");
		
		System.out.println("수정된 결과 확인");
		System.out.println(b1.information());
		System.out.println("=========================");
		
		System.out.println("도서명 = "+b1.getTitle());
		System.out.printf("할인된 가격 = %d원\n",(int)(b1.getPrice()*(1-b1.getDiscountRate())));
		System.out.println("도서명 = "+b2.getTitle());
		System.out.printf("할인된 가격 = %d원\n",(int)(b2.getPrice()*(1-b2.getDiscountRate())));
		
		
	}

}
