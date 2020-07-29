package com.kh.controller;

import java.util.ArrayList;
import java.util.Collection;

import com.kh.model.vo.Book;

public class BookManager {
	
	private ArrayList<Book> bookList = new ArrayList<>();
	
	public BookManager() {}
	
	public void insertBook(Book book) {
		int lastNo = 0;
		
		try {
		lastNo = bookList.get(bookList.size()-1).getbNo()+1; //������ ������ȣ +1
		}catch(ArrayIndexOutOfBoundsException e) {
			lastNo = 1;
		}

		book.setbNo(lastNo); //������ȣ����
		
		bookList.add(book);
	}
	
	public int deleteBook(int bNo) {

		int complete = 0; //��������. 1�̸� �����Ϸ�.
		for(int i = 0; i<bookList.size(); i++) {
			if(bookList.get(i).getbNo()==bNo) {
				bookList.remove(i);
				i--;//����� ����Ʈ ����� �پ���. �ε����� �ǳʶ��� �ʵ��� i�� �ٿ�����.
				complete = 1;
			}
		}
		
		return complete;
		
	
	}
	
	public ArrayList<Book> searchBook(String title) {
		ArrayList<Book> searchList = new ArrayList<Book>(); //�˻���� ������ ����Ʈ
		for(Book e : bookList) {
			if(e.getTitle().equals(title)) {
				searchList.add(e); //���޹��� ������������ ������ searchList�� �߰�
			}
		}
		return searchList;
	}
	
	public ArrayList<Book> selectList(){
		return bookList;
	}
	
	
}
