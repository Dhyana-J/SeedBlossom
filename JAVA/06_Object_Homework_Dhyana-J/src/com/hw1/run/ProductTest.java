package com.hw1.run;
import com.hw1.model.vo.*;

public class ProductTest {


	public static void main(String[] args) {
		
		// 매개변수 생성자 이용해 3개의 객체 생성
		Product p1 = new Product("ssgnote9","갤럭시노트9","경기도 수원",960000,10.0);
		Product p2 = new Product("lgxnote5","LG스마트폰5","경기도 평택",780000,0.7);
		Product p3 = new Product("ktsnote3","KT스마트폰3","서울시 강남",250000,0.3);
	
		// 객체가 가진 필드 값 출력
		System.out.println(p1.information());
		System.out.println(p2.information());
		System.out.println(p3.information());
		
		System.out.println("=================================================");
		
		//객체들 가격 120만원으로 변경
		p1.setPrice(1200000);
		p2.setPrice(1200000);
		p3.setPrice(1200000);
		
		//부가세율도 0.05로 변경
		p1.setTax(0.05);
		p2.setTax(0.05);
		p3.setTax(0.05);
		
		//객체가 가진 필드값 출력
		System.out.println(p1.information());
		System.out.println(p2.information());
		System.out.println(p3.information());
		
		System.out.println("=================================================");
		
		//각 객체 가격을 실제가격으로 계산해서 출력
		//기존가격 + 부가세
		//price는 int형이고, tax는 double이므로, 강제로 int로 형변환해주자.
		p1.setPrice(p1.getPrice()+(int)(p1.getPrice()*p1.getTax()));
		p2.setPrice(p2.getPrice()+(int)(p2.getPrice()*p2.getTax()));
		p3.setPrice(p3.getPrice()+(int)(p3.getPrice()*p3.getTax()));
		
		System.out.println("상품명 = "+p1.getProductName());
		System.out.println("부가세 포함 가격 = "+p1.getPrice());
		
		System.out.println("상품명 = "+p2.getProductName());
		System.out.println("부가세 포함 가격 = "+p2.getPrice());
		
		System.out.println("상품명 = "+p3.getProductName());
		System.out.println("부가세 포함 가격 = "+p3.getPrice());
		

	}

}
