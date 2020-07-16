package com.method.hw3.controller;

public class StaticSample {
	
	private static String value;

//	static은 객체를 생성할 필요가 없으므로 생성자 생략
	
//	getter setter
	public static String getValue() {
		return value;
	}

	public static void setValue(String value) {
		StaticSample.value = value;
	}
	
//	대문자 A~Z는 아스키값 65~90 소문자 a~z는 아스키값 97~122
//	소문자->대문자 바꿔주려면 소문자 아스키값에 32 빼주면된다.
	public static void toUpper() {
		
		String toUpper = ""; //대문자 저장할 문자열
		for(int i = 0; i<value.length(); i++) {
			//소문자인경우 대문자로 바꿔주자.
			if(97<=value.charAt(i)&&value.charAt(i)<=122) {
				toUpper += (char)(value.charAt(i)-32); 
			}
			else {
				toUpper += (char)(value.charAt(i));
			}
		}
		value = toUpper; //대문자로 변경한 문자열 대입
	}
	
	public static void setChar(int index, char c ) {
		
		//value값을 배열 인덱스에 한 글자씩 저장한다.
		char[] load = new char[value.length()];
		String result = ""; //결과를 저장할 문자열
		
		for(int i = 0; i<load.length;i++) {
			//전달받은 index의 값으로 c를 저장한다.
			if(i==index) {
				load[i]=c;
			}
			//전달받은 index 이외의 값은 기존 값과 똑같이 저장한다.
			else {
				load[i] = value.charAt(i);
			}
			//결과를 result string에 저장한다.
			result+=load[i];
		}
		
		//value값에 result값을 저장한다.
		value = result;
		
	}
	
	public static int valueLength() {
		return value.length();
	}
	
	public static String valueConcat(String str) {
		return value+str;
	}
	
	
	

}
