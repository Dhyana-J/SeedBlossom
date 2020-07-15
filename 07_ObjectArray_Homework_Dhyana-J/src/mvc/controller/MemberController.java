package mvc.controller;

import java.util.*;

import mvc.model.vo.*;


public class MemberController {
	
	public final int SIZE = 10;
	private int memberCount = 0;
	private Member[] mem = new Member[SIZE];
	
	{
		mem[0] = new Member("user01","pass01","������",20,'M',"kim12@naver.com");
		mem[1] = new Member("user02", "pass02", "�̼���", 60, 'M', "lee2@naver.com");
		mem[2] = new Member("user03", "pass03", "������", 17, 'F', "yo5@hanmail.net");
		mem[3] = new Member("user04", "pass04", "�����ҹ�", 57, 'M', "yeon@gmail.com");
		mem[4] = new Member("user05", "pass05", "�Ż��Ӵ�", 45, 'F', "shin@naver.com");
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
		//��ü�迭�� ��ü�� �߰��ϰ� memberCount�� 1 ������Ų��.
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
		
		boolean delete = false; //true�� ��� �ݺ��� ��������
		
		for(int i = 0; i<memberCount; i++) {
			
			if(userId.equals(mem[i].getUserId())){
				
				delete = true;
				
				for(int j = i; j<memberCount; j++) {
					if(j == memberCount-1) { //������ �ε����� �������Ѵ�.
						mem[j] = null;
						//���������Ƿ� ���ī��Ʈ�� 1 ���ҽ�Ų��.
						memberCount--;
						return; // ������ �ε��� null ä���ָ� ����������.
					}
					else {
//						mem[j] = new Member(mem[j+1].getUserId(),mem[j+1].getUserPwd()
//								,mem[j+1].getName(),mem[j+1].getAge(),
//								mem[j+1].getGender(),mem[j+1].getEmail());
						mem[j] = mem[j+1];
					}
				}
				
				if(delete == true) break;
				
			}
			
		}
		
	}

}
