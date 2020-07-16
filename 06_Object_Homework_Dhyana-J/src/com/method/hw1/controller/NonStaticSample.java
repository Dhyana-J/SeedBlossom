package com.method.hw1.controller;

public class NonStaticSample {
	
	public void printLottoNumbers() {
		
		int[] num = new int[6];
		int temp = 0; //�ߺ� �����Ҷ� ���� ����
		
		System.out.print("1. ���� �� : ");
		
		for(int i = 0; i<num.length; i++) {
			
			//������ ����
			num[i] = (int)(Math.random()*45+1);				
			
			for(int j = 0; j<i; j++) {
				//�ߺ��̸� ������ �ٽô���
				if(num[i]==num[j]) {
					i--;
					break;
				}
				//���Ŀ� �޴� ���� �տ� ���� ������ ������ ��ġ ���� (������������)
				if(num[i]<num[j]) {
					temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
			
		}
		
		//�迭�� ��� �� ���
		for(int i = 0; i<num.length; i++) {
			System.out.print(num[i] + " ");
		}
		
		System.out.println();
		
		
	}
	
	public void outputChar(int num, char c){
		
		System.out.printf("2. %c���� %d�� ��� : ",c,num);
		
		for(int i = 0; i<num; i++) {
			System.out.print(c + " ");
		}
		System.out.println();
		
	}
	
	public char alphabette() {
				
		//�ƽ�Ű�ڵ� A~Z -> (65~90) a~z -> (97~122)
		//65~122 ���̿��� �������� �޵�, 91~96 ���� ���� ���ܽ��Ѿ��Ѵ�. 
		int input = 0; 
		while(true) {
			input = (int)(Math.random()*58+65); //65<=random<(58+65=123) �� 65~122����.	
			if(65<=input&&input<=122) {
				if(91<=input&&input<=96) continue; //91~96 ���� ���� �����Ѵ�. Ư�����ڴ�.
				else {
					break; //�ҹ��ڳ� �빮�ڰ��� ���� ��� �ݺ��� ����������.
				}
			}
		}
		
		char c = (char)input; //�ƽ�Ű�ڵ� 10������ char�� ��ȯ.
		return c;
	}
	
	
	public String mySubstring(String str, int index1, int index2) {
		
		String load = ""; //str �Űܳ��� ���ڿ�.
		String result = ""; //����� ������ ���ڿ�.
		
		if(str.equals("")) {
			return null; //���޹��� ���ڿ� ���� ������ null ����.
		}
		else {
			for(int i = 0; i<str.length(); i++ ) {
				load += str.charAt(i); //load���ڿ��� str���ڿ��� �ű��.
			}

			for(int i = index1; i<index2; i++) {
				result += load.charAt(i); //result���ڿ��� ���� ���� load ���ڿ��� �ű��.
			}
			return "4. "+str+"�� "+index1+"�� "+index2+"�� �ε��� ������ �� ��� : "+result;
		}
	}
	
	

}
