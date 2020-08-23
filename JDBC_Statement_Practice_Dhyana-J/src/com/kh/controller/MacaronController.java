package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MacaronDao;
import com.kh.model.vo.Macaron;
import com.kh.view.MacaronMenu;

public class MacaronController {
	
	/**
	 * 마카롱 보관 요청 처리해주는 메소드
	 */
	public void insertMacaron(Macaron m) {
		
		if(macaronQuantity()>=10) {
			new MacaronMenu().displayFail("냉장고에 마카롱이 가득 찼습니다.");
			return;
		}
		
		int result = new MacaronDao().insertMacaron(m);
		
		if(result>0) {
			new MacaronMenu().displaySuccess("마카롱 보관 성공!");
		}else {
			new MacaronMenu().displayFail("마카롱 보관 실패!");
		}
		
	}
	
	
	/**
	 * 마카롱 목록 조회 요청 처리 메소드 
	 */
	public void selectList() {
		
		ArrayList<Macaron> list = new MacaronDao().selectList();
		
		if(list.isEmpty()) {
			new MacaronMenu().displayNoData("냉장고에 마카롱이 없습니다");
		}else {
			new MacaronMenu().displayMacaronList(list);
		}
	}
	
	
	/**
	 * @return 마카롱 총 개수 리턴
	 */
	public int macaronQuantity() {
		return new MacaronDao().selectList().size();
	}
	
	
	
	/**
	 * 이름으로 마카롱 검색. 이름은 PK이므로 결과값 한 행뿐임.
	 */
	public void selectByMacaronName(String name) {
		
		Macaron m = new MacaronDao().selectByMacaronName(name);
		
		if(m == null) {
			new MacaronMenu().displayNoData("해당 이름을 가진 마카롱이 없어용\n");
		}
		else {
			new MacaronMenu().displayMacaron(m);
		}
	}
	
	/**
	 * 색으로 마카롱 검색
	 */
	public void selectByMacaronColor(String color) {
		
		ArrayList<Macaron> list = new MacaronDao().selectByMacaronColor(color);
		
		if(list.isEmpty()) {
			new MacaronMenu().displayNoData("해당 이름을 가진 마카롱이 없어용\n");
		}
		else {
			new MacaronMenu().displayMacaronList(list);
		}
	}
	
	public void updateMacaron(Macaron m) {
		int result = new MacaronDao().updateMacaron(m);
		
		if(result>0) {
			new MacaronMenu().displaySuccess("변신 성공!!");
		}else {
			new MacaronMenu().displaySuccess("변신 실패!!");
			
		}
	}
	
	public void deleteMacaron(String name) {
		
		int result = new MacaronDao().deleteMacaron(name);
		
		if(result>0) {
			new MacaronMenu().displaySuccess("으음~ (파파팡! 터지는 맛!)\n 순삭했다.");
		}else {
			new MacaronMenu().displayFail("찾는 마카롱이 없는데요?");
		}
		
	}
	
}
