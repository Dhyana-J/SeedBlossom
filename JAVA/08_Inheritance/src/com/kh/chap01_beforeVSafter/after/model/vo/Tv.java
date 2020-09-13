package com.kh.chap01_beforeVSafter.after.model.vo;

public class Tv extends Product{

	private int inch;
	
	public Tv() {
		
	}
	
	public Tv(String brand, String pCode, String pName, int price,int inch) {
		super(brand,pCode,pName,price); //����Ŭ������ �����ڿ��� �ñ�ô�!
		this.inch = inch;
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		this.inch = inch;
	}
	
	//overriding
	public String information() {
		return super.information()+" "+inch;
	}
	
}
