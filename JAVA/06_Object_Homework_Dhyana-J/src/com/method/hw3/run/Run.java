package com.method.hw3.run;
import com.method.hw3.controller.*;

public class Run {

	public static void main(String[] args) {
		
		StaticSample.setValue("Java");
		System.out.print("value : "+StaticSample.getValue());
		System.out.println();
		
		StaticSample.toUpper();//value �빮�ڷ� ����
		System.out.print("�빮�ڷ� : "+StaticSample.getValue());
		System.out.println();
		
		System.out.print("���� : "+StaticSample.valueLength());
		System.out.println();
		
		String str = "PROGRAMMING";
		System.out.print(str+" �ٿ��� : "+StaticSample.valueConcat(str));
		System.out.println();
		
		int index = 0;
		char before = StaticSample.getValue().charAt(index);
		char after = 'J';
		StaticSample.setChar(index,after);
		System.out.print(before+" => "+after+" : "+StaticSample.getValue());
		

	}

}
