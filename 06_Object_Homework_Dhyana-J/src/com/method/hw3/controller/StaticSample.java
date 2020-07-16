package com.method.hw3.controller;

public class StaticSample {
	
	private static String value;

//	static�� ��ü�� ������ �ʿ䰡 �����Ƿ� ������ ����
	
//	getter setter
	public static String getValue() {
		return value;
	}

	public static void setValue(String value) {
		StaticSample.value = value;
	}
	
//	�빮�� A~Z�� �ƽ�Ű�� 65~90 �ҹ��� a~z�� �ƽ�Ű�� 97~122
//	�ҹ���->�빮�� �ٲ��ַ��� �ҹ��� �ƽ�Ű���� 32 ���ָ�ȴ�.
	public static void toUpper() {
		
		String toUpper = ""; //�빮�� ������ ���ڿ�
		for(int i = 0; i<value.length(); i++) {
			//�ҹ����ΰ�� �빮�ڷ� �ٲ�����.
			if(97<=value.charAt(i)&&value.charAt(i)<=122) {
				toUpper += (char)(value.charAt(i)-32); 
			}
			else {
				toUpper += (char)(value.charAt(i));
			}
		}
		value = toUpper; //�빮�ڷ� ������ ���ڿ� ����
	}
	
	public static void setChar(int index, char c ) {
		
		//value���� �迭 �ε����� �� ���ھ� �����Ѵ�.
		char[] load = new char[value.length()];
		String result = ""; //����� ������ ���ڿ�
		
		for(int i = 0; i<load.length;i++) {
			//���޹��� index�� ������ c�� �����Ѵ�.
			if(i==index) {
				load[i]=c;
			}
			//���޹��� index �̿��� ���� ���� ���� �Ȱ��� �����Ѵ�.
			else {
				load[i] = value.charAt(i);
			}
			//����� result string�� �����Ѵ�.
			result+=load[i];
		}
		
		//value���� result���� �����Ѵ�.
		value = result;
		
	}
	
	public static int valueLength() {
		return value.length();
	}
	
	public static String valueConcat(String str) {
		return value+str;
	}
	
	
	

}
