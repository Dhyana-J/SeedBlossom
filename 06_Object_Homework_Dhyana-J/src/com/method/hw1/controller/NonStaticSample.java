package com.method.hw1.controller;

public class NonStaticSample {
	
	public void printLottoNumbers() {
		
		int[] num = new int[6];
		int temp = 0; //중복 제거할때 쓰일 변수
		
		System.out.print("1. 랜덤 값 : ");
		
		for(int i = 0; i<num.length; i++) {
			
			//랜덤값 대입
			num[i] = (int)(Math.random()*45+1);				
			
			for(int j = 0; j<i; j++) {
				//중복이면 랜덤값 다시대입
				if(num[i]==num[j]) {
					i--;
					break;
				}
				//이후에 받는 값이 앞에 받은 값보다 작으면 위치 변경 (오름차순정렬)
				if(num[i]<num[j]) {
					temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
			
		}
		
		//배열에 담긴 값 출력
		for(int i = 0; i<num.length; i++) {
			System.out.print(num[i] + " ");
		}
		
		System.out.println();
		
		
	}
	
	public void outputChar(int num, char c){
		
		System.out.printf("2. %c문자 %d개 출력 : ",c,num);
		
		for(int i = 0; i<num; i++) {
			System.out.print(c + " ");
		}
		System.out.println();
		
	}
	
	public char alphabette() {
				
		//아스키코드 A~Z -> (65~90) a~z -> (97~122)
		//65~122 사이에서 랜덤값을 받되, 91~96 사이 값은 제외시켜야한다. 
		int input = 0; 
		while(true) {
			input = (int)(Math.random()*58+65); //65<=random<(58+65=123) 즉 65~122사이.	
			if(65<=input&&input<=122) {
				if(91<=input&&input<=96) continue; //91~96 사이 값은 제외한다. 특수문자다.
				else {
					break; //소문자나 대문자값이 나온 경우 반복문 빠져나가자.
				}
			}
		}
		
		char c = (char)input; //아스키코드 10진수를 char로 변환.
		return c;
	}
	
	
	public String mySubstring(String str, int index1, int index2) {
		
		String load = ""; //str 옮겨놓을 문자열.
		String result = ""; //결과값 저장할 문자열.
		
		if(str.equals("")) {
			return null; //전달받은 문자열 값이 없으면 null 리턴.
		}
		else {
			for(int i = 0; i<str.length(); i++ ) {
				load += str.charAt(i); //load문자열에 str문자열을 옮긴다.
			}

			for(int i = index1; i<index2; i++) {
				result += load.charAt(i); //result문자열에 범위 내의 load 문자열을 옮긴다.
			}
			return "4. "+str+"의 "+index1+"번 "+index2+"번 인덱스 사이의 값 출력 : "+result;
		}
	}
	
	

}
