package com.method.hw3.run;
import com.method.hw3.controller.*;

public class Run {

	public static void main(String[] args) {
		
		StaticSample.setValue("Java");
		System.out.print("value : "+StaticSample.getValue());
		System.out.println();
		
		StaticSample.toUpper();//value 대문자로 변경
		System.out.print("대문자로 : "+StaticSample.getValue());
		System.out.println();
		
		System.out.print("길이 : "+StaticSample.valueLength());
		System.out.println();
		
		String str = "PROGRAMMING";
		System.out.print(str+" 붙여서 : "+StaticSample.valueConcat(str));
		System.out.println();
		
		int index = 0;
		char before = StaticSample.getValue().charAt(index);
		char after = 'J';
		StaticSample.setChar(index,after);
		System.out.print(before+" => "+after+" : "+StaticSample.getValue());
		

	}

}
