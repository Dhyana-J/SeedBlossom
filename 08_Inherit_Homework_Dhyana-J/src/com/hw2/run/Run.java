package com.hw2.run;
import com.hw2.model.vo.*;

public class Run {

	public static void main(String[] args) {
		
		Circle[] c = new Circle[2];
		Rectangle[] r = new Rectangle[2];
		
		c[0] = new Circle(1,2,3);
		c[1] = new Circle(3,3,4);
		
		r[0] = new Rectangle(-1,-2,5,2);
		r[1] = new Rectangle(-2,5,2,8);
		
		System.out.println("==== circle ====");
		for(int i = 0; i<c.length; i++) {
			c[i].draw();
		}
		System.out.println("==== rectangle ====");
		for(int i = 0; i<r.length; i++) {
			r[i].draw();
		}
		
		
	}
	
}
