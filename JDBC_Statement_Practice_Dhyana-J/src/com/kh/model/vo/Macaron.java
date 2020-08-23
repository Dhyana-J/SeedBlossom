package com.kh.model.vo;

import java.sql.Date;

public class Macaron {
	
	private int macaronNo;
	private String name;
	private String color;
	private String flavor;
	private Date bakedDate;
	
	public Macaron() {
		
	}

	//매개변수 생성자 (전체 변수 포함)
	public Macaron(int macaronNo, String name, String color, String flavor, Date bakedDate) {
		super();
		this.macaronNo = macaronNo;
		this.name = name;
		this.color = color;
		this.flavor = flavor;
		this.bakedDate = bakedDate;
	}
	
	//매개변수 생성자 (제조번호랑 제조일자는 자동으로 sql문으로 넣어줄거다)
	public Macaron(String name, String color, String flavor) {
		super();
		this.name = name;
		this.color = color;
		this.flavor = flavor;
	}
	
	public int getMacaronNo() {
		return macaronNo;
	}

	public void setMacaronNo(int macaronNo) {
		this.macaronNo = macaronNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public Date getBakedDate() {
		return bakedDate;
	}

	public void setBakedDate(Date bakedDate) {
		this.bakedDate = bakedDate;
	}

	@Override
	public String toString() {
		return "제조번호 : "+macaronNo
				+" / 이름 : "+name
				+" / 맛 : "+flavor
				+" / 색 : "+color
				+" / 제조날짜 : "+bakedDate;
	}

	

	
}
