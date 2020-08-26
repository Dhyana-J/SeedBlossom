package com.kh.model.vo;

public class Product {
	
	private String pId;
	private String pName;
	private int price;
	private String dsc;
	private int stock;
	
	public Product() {
		
	}

	public Product(String pId, String pName, int price, String dsc, int stock) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.price = price;
		this.dsc = dsc;
		this.stock = stock;
	}
	

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	
	@Override
	public String toString() {
		return "[ 상품id : " + pId + ", 상품명 : " + pName + ", 가격 : " + price + ", 제품 상세 : " + dsc + ", 재고 수량 : " + stock
				+ " ]";
	}
	
	
	

}
