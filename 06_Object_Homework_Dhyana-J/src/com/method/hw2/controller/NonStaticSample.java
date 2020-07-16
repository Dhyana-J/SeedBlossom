package com.method.hw2.controller;

public class NonStaticSample {
	
//	���޹��� ������ŭ �迭 ũ�� �Ҵ�, ������ �� ��� �� �ּҰ� ����
	public int[] intArrayAllocation(int length) {
		
		int[] arr = new int[length];
		for(int i = 0; i<arr.length; i++) {
			arr[i] = (int)(Math.random()*100+1);
		}
		
		return arr;
	}
	
//	���޹��� �迭 ȭ�鿡 ���
	public void display(int[] arr) {
		for(int i = 0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
//	���޹��� �迭, �ε������� ���� ������ �ε��� ��ȯ
	public void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
//	���޹��� �迭 ������������ ���. (swap(), display() �̿�)
	public void sortDescending(int[] arr) {
		for(int i = 0; i<arr.length; i++) {
			for(int j = 0; j<i; j++) {
				if(arr[i]>arr[j]) swap(arr, i, j);
			}
		}
		display(arr);
	}
	
//	���޹��� �迭 �������� ���. (swap(), display() �̿�)
	public void sortAscending(int[] arr) {
		for(int i = 0; i<arr.length; i++) {
			for(int j = 0; j<i; j++) {
				if(arr[i]<arr[j]) swap(arr, i, j);
			}
		}
		display(arr);
	}
	
//	���޹��� ���ڿ��� ���޹��� ���ڰ� ���ԵǾ��ִ� ���� ����
	public int countChar(String str, char c) {
		int count = 0;
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i)==c) count++;
		}
		return count;
	}
	
//	���޹��� �� ���� ���� �� ���� ������ ū �� ������ �������� ���� ���� ����
	public int totalValue(int num1, int num2) {
		int sum = 0;
		
//		num1�� num2���� ���� ���� �� �� �ֵ��� ��������.
		if(num2<num1) {
			int temp = 0;
			temp = num1;
			num1 = num2;
			num2 = temp;
		}
		
//		���޹��� �� ���� ���Ե��� �ʾƾ��ϹǷ�, ���ۼ�+1���� ����-1���� ���ϸ� �ȴ�.
		for(int i = num1+1; i<num2; i++) {
			sum+=i;
		}
		return sum;
	}
	
//	���ڿ��� �ε����� ���޹޾� �ش� �ε��� ���� ����
	public char pCharAt(String str, int index) {
		return str.charAt(index);
	}
	
//	���޹��� �� ���� ���ڿ��� �ϳ��� ���ڿ��� ���� ����
	public String pConcat(String str1, String str2) {
		return str1+str2;
	}
	

}
