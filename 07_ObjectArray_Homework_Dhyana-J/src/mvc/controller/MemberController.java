package mvc.controller;

import java.util.*;

import mvc.model.vo.*;


public class MemberController {
	
	public final int SIZE = 10;
	private int memberCount = 0;
	private Member[] mem = new Member[SIZE];
	
	{
		mem[0] = new Member("user01","pass01","김유신",20,'M',"kim12@naver.com");
		mem[1] = new Member("user02", "pass02", "이순신", 60, 'M', "lee2@naver.com");
		mem[2] = new Member("user03", "pass03", "유관순", 17, 'F', "yo5@hanmail.net");
		mem[3] = new Member("user04", "pass04", "연개소문", 57, 'M', "yeon@gmail.com");
		mem[4] = new Member("user05", "pass05", "신사임당", 45, 'F', "shin@naver.com");
		memberCount = 5;
	}
	
	public int getMemberCount(){
		return memberCount;
	}
	
	public Member[] getMem() {
		return mem;
	}
	
	public Member checkId(String userId) {
		
		for(int i = 0; i<memberCount; i++) {
			
			if(userId.equals(mem[i].getUserId())){
				return mem[i];
			}
	
		}
		
		return null;
	}
	
	public void insertMember(Member m) {
		//객체배열에 객체를 추가하고 memberCount를 1 증가시킨다.
		mem[memberCount++] = m;
	}
	
	public Member searchMember(int menu, String search) {
		
		switch(menu) {
		case 1: 
			for(int i = 0; i<memberCount; i++) {
				
				if(search.equals(mem[i].getUserId())){
					return mem[i];
				}
		
			} return null;
			
		case 2:
			for(int i = 0; i<memberCount; i++) {
				
				if(search.equals(mem[i].getName())){
					return mem[i];
				}
		
			} return null;
			
		case 3:
			for(int i = 0; i<memberCount; i++) {
				
				if(search.equals(mem[i].getEmail())){
					return mem[i];
				}
		
			} return null;
		default : return null;
		}
				
	}
	
	public void updateMember(Member m, int menu, String update) {
		
		switch(menu) {
		case 1: m.setUserPwd(update); break;
		case 2: m.setName(update); break;
		case 3: m.setEmail(update); break;
		}
	}
	
	public void deleteMember(String userId) {
		
		
		for(int i = 0; i<memberCount; i++) {
			
			if(userId.equals(mem[i].getUserId())){
				
				
				for(int j = i; j<memberCount; j++) {
					if(j == memberCount-1) { //마지막 인덱스는 비워줘야한다.
						mem[j] = null;
						//삭제했으므로 멤버카운트를 1 감소시킨다.
						memberCount--;
						return; // 마지막 인덱스 null 채워주면 종료해주자.
					}
					else {
						mem[j] = mem[j+1];
					}
				}
				
				
			}
			
		}
		
	}

}
