package com.method.hw2.controller;

public class NonStaticSample {
	
//	전달받은 정수만큼 배열 크기 할당, 임의의 값 기록 후 주소값 리턴
	public int[] intArrayAllocation(int length) {
		
		int[] arr = new int[length];
		for(int i = 0; i<arr.length; i++) {
			arr[i] = (int)(Math.random()*100+1);
		}
		
		return arr;
	}
	
//	전달받은 배열 화면에 출력
	public void display(int[] arr) {
		for(int i = 0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
//	전달받은 배열, 인덱스들을 통해 각각의 인덱스 교환
	public void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
//	전달받은 배열 내림차순으로 출력. (swap(), display() 이용)
	public void sortDescending(int[] arr) {
		for(int i = 0; i<arr.length; i++) {
			for(int j = 0; j<i; j++) {
				if(arr[i]>arr[j]) swap(arr, i, j);
			}
		}
		display(arr);
	}
	
//	전달받은 배열 오름차순 출력. (swap(), display() 이용)
	public void sortAscending(int[] arr) {
		for(int i = 0; i<arr.length; i++) {
			for(int j = 0; j<i; j++) {
				if(arr[i]<arr[j]) swap(arr, i, j);
			}
		}
		display(arr);
	}
	
//	전달받은 문자열에 전달받은 문자가 포함되어있는 개수 리턴
	public int countChar(String str, char c) {
		int count = 0;
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i)==c) count++;
		}
		return count;
	}
	
//	전달받은 두 개의 정수 중 작은 값에서 큰 값 사이의 정수들의 합을 구해 리턴
	public int totalValue(int num1, int num2) {
		int sum = 0;
		
//		num1이 num2보다 작은 값이 들어갈 수 있도록 맞춰주자.
		if(num2<num1) {
			int temp = 0;
			temp = num1;
			num1 = num2;
			num2 = temp;
		}
		
//		전달받은 두 수는 포함되지 않아야하므로, 시작수+1부터 끝수-1까지 더하면 된다.
		for(int i = num1+1; i<num2; i++) {
			sum+=i;
		}
		return sum;
	}
	
//	문자열과 인덱스를 전달받아 해당 인덱스 문자 리턴
	public char pCharAt(String str, int index) {
		return str.charAt(index);
	}
	
//	전달받은 두 개의 문자열을 하나의 문자열로 합쳐 리턴
	public String pConcat(String str1, String str2) {
		return str1+str2;
	}
	

}
