package com.kh.chap01_beforeVSafter.after.model.vo;

public class Desktop extends Product{
	
	private boolean allInOne;
	
	public Desktop() {}
	
	
	
	public Desktop(String brand, String pCode, String pName, int price,boolean allInOne) {
		super(brand,pCode,pName,price); //수퍼클래스의 생성자에게 맡깁시다!
		this.allInOne = allInOne;
	}



	public void setAllInOne(boolean allInOne) {
		this.allInOne = allInOne;
	}
	
	public boolean isAllInOne() {
		return allInOne;
	}
	
	//overriding
	public String information() {
		return super.information()+" "+allInOne;
	}

}
