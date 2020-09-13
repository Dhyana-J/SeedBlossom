package com.kh.model.vo;

import java.util.ArrayList;

public class Book {
	
	private int bNo;
	private String title;
	private int category;


	private String author;
	
	public Book() {}

	public Book(String title, int category, String author) {
		
		this.title = title;
		this.category = category;
		this.author = author;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public int getCategory() {
		return category;
	}
	
	public void setCategory(int category) {
		this.category = category;
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

	@Override
	public String toString() {
		
		switch(this.category) {
		case 1: return "Book [category= 인문, bNo=" + bNo + ", title=" + title + ", author=" + author + "]";
		case 2: return "Book [category= 자연과학, bNo=" + bNo + ", title=" + title + ", author=" + author + "]";
		case 3: return "Book [category= 의료, bNo=" + bNo + ", title=" + title + ", author=" + author + "]";
		case 4: return "Book [category= 기타, bNo=" + bNo + ", title=" + title + ", author=" + author + "]";
		default : return "Book [category= 불명 bNo=" + bNo + ", title=" + title + ", author=" + author + "]";
		}
	
	}
	
	

}
