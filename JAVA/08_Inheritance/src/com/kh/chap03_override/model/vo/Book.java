package com.kh.chap03_override.model.vo;

public class Book {

	private String title;
	private String author;
	private int price;
	
	
	// �⺻������
//	public Book() {
//		
//	}

	// �Ű�����������
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

	// information �޼ҵ�
	public String information() {
		return "���� : "+title+", ���� : "+author+", ���� : "+price;
	}
	
	@Override
	public String toString() {
		return "�Ⱦ˷�������~";
	}
	
	@Override
	public boolean equals(Object obj) {
		
//		double a = 2.5;
//		int b = 0;
//		b = (int)a; 
//		a = (int)a; -> double a�� int�� �ٲ� ��, �ٽ� double a�� �ִ´�.  
//		System.out.println("b�� " + b);
		
//		obj = (Book)obj; -> �ȵǴ� ����? obj�� Book���� ����ȯ ��, �ٽ� Object�� obj�� �ִ´�. �׷��� �ȵȴ�!
		
//		��� ��쿡 ���� ��ü��� �ϳ���? �ּҰ��� �ؽ��ڵ�� �ʵ尪 �� �� ���� ��� ���� ��ü����. 
//		�״ϱ� �ּҰ��� ���빰�� �� ���ƾ��ؿ�.
		
		Book other = (Book)obj;
		return this.information().equals(other.information()); // other��� (Book)obj ����
		
	}
	
//	Object Ŭ������ �ִ� hashCode �޼ҵ带 ������(�������̵�)
	@Override
	public int hashCode() {
		// bk1.hashCode() --> 
		// bk3.hashCode()
		
		// �ش� ��ü �ʵ尪���� �ϳ��� ���ڿ��� ����, �ش� ���ڿ��� �ؽ��ڵ带 ��������.
		//title + author + price --> ���ڿ�(String)
		
//		�������̵� �� : ObjectŬ���� hashCode�޼ҵ� -> ���� �ּ� ���̽� 10������ ����� ��� ��ȯ
//		�������̵� �� : BookŬ���� hashCode�޼ҵ� -> �� ��ü �ʵ尪 ���̽��� �ؽ��ڵ尪 ��ȯ
		
		return (title+author+price).hashCode();
	}
	
	
}
