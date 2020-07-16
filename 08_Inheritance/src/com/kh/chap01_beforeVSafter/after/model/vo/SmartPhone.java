package com.kh.chap01_beforeVSafter.after.model.vo;

public class SmartPhone extends Product{

	private String mobileAgency;
	
	public SmartPhone() {
		
	}
	
	public SmartPhone(String brand, String pCode, String pName, int price, String mobileAgency) {
		super(brand,pCode,pName,price); //수퍼클래스의 생성자에게 맡깁시다!
		this.mobileAgency = mobileAgency;
	}

	public String getMobileAgency() {
		return mobileAgency;
	}

	public void setMobileAgency(String mobileAgency) {
		this.mobileAgency = mobileAgency;
	}
	
	//overriding
	public String information() {
		return super.information()+" "+mobileAgency;
	}
	
	
}
