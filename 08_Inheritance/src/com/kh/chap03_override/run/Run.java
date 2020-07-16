package com.kh.chap03_override.run;

import com.kh.chap03_override.model.vo.Book;

public class Run {

	public static void main(String[] args) {

		Book bk1 = new Book("수학의 정석", "나수학", 10000);
		Book bk2 = new Book("고래 싸움에 새우등 터진다","고래",20000);
		
		System.out.println(bk1.information());
		System.out.println(bk2.information());
		System.out.println(bk1.toString());
		
		Book bk3 = new Book("수학의 정석", "나수학", 10000); // bk1객체랑 동일한 필드값을 가진 bk3객체 생성
		
		System.out.println("bk1과 bk3가 같은 책입니까? "+(bk1 == bk3));
		
		// 레퍼런스간의 동등비교 할 때, equals() 메소드 사용 가능
		System.out.println("bk1과 bk3가 같은 책입니까? "+bk1.equals(bk3));
		
		System.out.println(bk1.getTitle().hashCode());
		System.out.println(bk3.getTitle().hashCode());
	}

}
