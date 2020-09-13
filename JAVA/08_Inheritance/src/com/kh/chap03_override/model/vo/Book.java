package com.kh.chap03_override.model.vo;

public class Book {

	private String title;
	private String author;
	private int price;
	
	
	// 기본생성자
//	public Book() {
//		
//	}

	// 매개변수생성자
	public Book(String title, String author, int price) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// information 메소드
	public String information() {
		return "제목 : "+title+", 저자 : "+author+", 가격 : "+price;
	}
	
	@Override
	public String toString() {
		return "안알려주지롱~";
	}
	
	@Override
	public boolean equals(Object obj) {
		
//		double a = 2.5;
//		int b = 0;
//		b = (int)a; 
//		a = (int)a; -> double a를 int로 바꾼 후, 다시 double a에 넣는다.  
//		System.out.println("b는 " + b);
		
//		obj = (Book)obj; -> 안되는 이유? obj를 Book으로 형변환 후, 다시 Object형 obj에 넣는다. 그래서 안된다!
		
//		어느 경우에 동일 객체라고 하나요? 주소값인 해쉬코드와 필드값 둘 다 같은 경우 동일 객체에요. 
//		그니까 주소값과 내용물이 다 같아야해요.
		
		Book other = (Book)obj;
		return this.information().equals(other.information()); // other대신 (Book)obj 가능
		
	}
	
//	Object 클래스에 있는 hashCode 메소드를 재정의(오버라이딩)
	@Override
	public int hashCode() {
		// bk1.hashCode() --> 
		// bk3.hashCode()
		
		// 해당 객체 필드값들을 하나의 문자열로 합쳐, 해당 문자열의 해시코드를 리턴하자.
		//title + author + price --> 문자열(String)
		
//		오버라이딩 전 : Object클래스 hashCode메소드 -> 실제 주소 베이스 10진수로 계산한 결과 반환
//		오버라이딩 후 : Book클래스 hashCode메소드 -> 각 객체 필드값 베이스로 해시코드값 반환
		
		return (title+author+price).hashCode();
	}
	
	
}
