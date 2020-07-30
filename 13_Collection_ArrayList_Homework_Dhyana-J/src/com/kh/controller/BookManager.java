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
		lastNo = bookList.get(bookList.size()-1).getbNo()+1; //마지막 도서번호 +1
		}catch(ArrayIndexOutOfBoundsException e) {
			lastNo = 1;
		}

		book.setbNo(lastNo); //도서번호설정
		
		bookList.add(book);
	}
	
	public int deleteBook(int bNo) {

		int complete = 0; //삭제여부. 1이면 삭제완료.
		for(int i = 0; i<bookList.size(); i++) {
			if(bookList.get(i).getbNo()==bNo) {
				bookList.remove(i);
				i--;//지우면 리스트 사이즈가 줄어든다. 인덱스를 건너뛰지 않도록 i를 줄여주자.
				complete = 1;
			}
		}
		
		return complete;
		
	
	}
	
	public ArrayList<Book> searchBook(String title) {
		ArrayList<Book> searchList = new ArrayList<Book>(); //검색결과 보관할 리스트
		for(Book e : bookList) {
			if(e.getTitle().contains(title)) { //.equals->.contains로 변경함.
				searchList.add(e); //전달받은 제목을포함한 도서를 searchList에 추가
			}
		}
		return searchList;
	}
	
	public ArrayList<Book> selectList(){
		return bookList;
	}
	
	
}
