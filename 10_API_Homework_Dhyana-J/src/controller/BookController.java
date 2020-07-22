package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import model.vo.Book;

public class BookController {
   
   private Book[] list = new Book[5];
   {
      list[0] = new Book("자바의 정석", "제임스 고슬링", "나무", new Date(2018-1900, 4-1, 15), 20000);
      list[1] = new Book("여러분들 성공할꺼에요", "보람쌤", "KH", new Date(2019-1900, 6-1, 15), 30000);
      list[2] = new Book("API의 모든것", "강보람", "KH", new Date(2017-1900, 2-1, 13), 15000);
      list[3] = new Book("씨언어 프로그래밍", "김절차", "문학동네", new Date(2016-1900, 8-1, 30), 10000);
   }
   
   public BookController() {}
   
   // 1. 도서 전체 출력 기능 메소드
   public void printAll() {
      
      // list 배열 전체 출력
	   for(Book e : list) {
		   //값이 null이 아닌 것만 출력해준다.
		   if(e!=null) System.out.println(e.toString());
	   }
      
   }
   
   
   
   
   
   // 2. 도서 추가 기능 메소드
   public void insertBook(String newTitle, String newAuthor, String newPublisher, String newDate, String newPrice) {
      
      // 1. 매개변수로 전달받은 newPrice값  ==>      String --> int로 변환 작업
      
      //      내용 작성
	   int price = Integer.parseInt(newPrice);
      
      // --------------------------------------------------------
      // 2. 매개변수로 전달받은 newDate값   ==>       String --> Date로 변환 작업
      //     '-'를 구분자로 StringTokenizer를 이용하여 문자열 분리 후 각각 년,월,일 을 Date에 적용
   
      //      내용 작성
	   StringTokenizer stn = new StringTokenizer(newDate,"-");
	   
	   //토큰에서 년 월 일 순으로 빠짐. 먼저 넣었던게 먼저 빠진다. (FIFO)
	   int year = Integer.parseInt(stn.nextToken()); //년
	   int month = Integer.parseInt(stn.nextToken()); //월
	   int day = Integer.parseInt(stn.nextToken()); //일    변수명 date -> day로 수정
	   
	   Date date = new Date(year-1900,month-1,day); //변수명 convertDate -> date로 수정
      
      // ------------------------------------------------------
      // 3. 나머지 전달받은 값들과 위에서 변환작업을 해준 price와 date값을 가지고
      //     매개변수 생성자를 통해 생성한 값 4번 인덱스에 대입
      list[4] = new Book(newTitle,newAuthor,newPublisher,date,price);
      
   
   }
   
   
   // 3. 도서 출간일 출력 기능 메소드
   public void printBookPublishDate() {
      // 마지막 도서의 출간일 출력
      // "xxxx년 xx월 xx일 출간" 과 같은 패턴으로 출력
      
      // SimpleDateFormat을 이용하여 출력
      
      //     내용 작성
	   //포멧 지정.
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 출간");
	   //마지막 도서의 출간일(Date)을 지정한 포멧으로 문자열화해서 출력
	   System.out.println(sdf.format(list[list.length-1].getPublishDate()));
      
   }
   
   
   // 4. 도서 검색 기능 메소드
   public void searchBook(String searchTitle) {
      
      // 도서 리스트를 전체적으로 조회하면서 (for문 이용)
      // 전달받은 검색명을 "포함한!!" 도서들 출력  => contains() 메소드 활용
	  for(Book e : list) {
		  if(e.getTitle().contains(searchTitle)) {
			  //도서 리스트에서 해당 검색어를 포함한 제목을 가진 도서 정보 출력
			  System.out.println(e.toString());
		  }
	  }
   
      
   }
   
   
   

}
