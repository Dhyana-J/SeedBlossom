package mvc.controller;

import mvc.model.vo.AniBook;
import mvc.model.vo.Book;
import mvc.model.vo.CookBook;
import mvc.model.vo.Member;

public class LibraryManager {
	
	private Member mem = null;
	private Book[] bList = new Book[5];
	{
		bList[0] = new CookBook("백종원의 집밥", "백종원", "tvN", true);
		bList[1] = new AniBook("한번 더 해요", "미티", "원모어", 19);
		bList[2] = new AniBook("루피의 원피스", "루피", "japan", 12);
		bList[3] = new CookBook("이혜정의 얼마나 맛있게요", "이혜정", "문학", false);
		bList[4] = new CookBook("최현석 날 따라해봐", "최현석", "소금책", true);
	}
	
	public void insertMember(Member mem) {
		this.mem = mem;
	}
	
	public Member myInfo() {
		return mem;
	}
	
	public Book[] selectAll() {
		return bList;
	}
	
	public Book[] searchBook(String keyword) {
		
		Book[] result = new Book[5];
		int count = 0;
		
		for(int i = 0; i<result.length; i++) {
			//bList 내부 책정보에 keyword와 일치하는 것이 포함되어있다면 실행
			if(bList[i].toString().contains(keyword)) {
				result[count++] = bList[i];
			}
		}
		
		return result;
	}
	
	public int rentBook(int index) {
		
		int result = 0;
		
		//전달받은 index의 bList객체가 실제 AniBook 객체를 참조하고 있고
		if(bList[index] instanceof AniBook) {
			//해당 만화책의 제한 나이와 회원의 나이를 비교해 회원 나이가 적을 경우
			if(mem.getAge()<((AniBook) bList[index]).getAccessAge()) {
				result = 1;  //나이 제한으로 대여 불가
			}
		}
		// '' CookBook객체를 참조하고있고
		else {
			//해당 요리책의 쿠폰유무가 "true"일 경우
			if(((CookBook)bList[index]).isCoupon()) {
				//회원의 쿠폰카운트 1증가 처리 후
				mem.setCouponCount(mem.getCouponCount()+1);
				result = 2; //성공적으로 대여 완료, 요리학원 쿠폰이 발급되었다는 의미
			}
		}
		
		
		return result;
	}

}
