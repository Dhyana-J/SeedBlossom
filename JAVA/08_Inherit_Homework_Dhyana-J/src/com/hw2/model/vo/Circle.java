package com.hw2.model.vo;

public class Circle extends Point{

	private int radius;
	
	public Circle() {
		
	}
	
	public Circle(int x, int y, int radius) {
		super(x,y);
		this.radius = radius;
	}
	
	
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void draw() {
		super.draw();
		System.out.printf("���� : %.1f\n",Math.PI*Math.pow(radius,2)); //Math.pow(�ŵ��������Ҽ�,������ұ�?)
		System.out.printf("�ѷ� : %.1f\n",Math.PI*radius*2);
	}
	
}
