package com.kh.chap02_inherit.model.vo;

public class Car extends Vehicle{

	private int tire;
	
	public Car() {
		
	}
	
	public Car(String name, double mileage, String kind, int tire) {
		super(name,mileage,kind);
		this.tire = tire;
	}

	public int getTire() {
		return tire;
	}

	public void setTire(int tire) {
		this.tire = tire;
	}
	
	@Override
	public String information() {
		return super.information()+", ¹ÙÄû ¼ö : "+tire;
	}
	
	@Override
	public void howToMove() {
		System.out.println("³× ¹ÙÄû¸¦ ±¼¸®¸ç ¿òÁ÷ÀÎ´Ù.");
	}
	
}
