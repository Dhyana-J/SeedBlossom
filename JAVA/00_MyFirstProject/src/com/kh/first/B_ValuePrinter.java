package com.kh.first;

public class B_ValuePrinter { 
	// com.kh.first.B_ValuePrinter
	// �پ��� ������ ������ ����ϴ� ���
	
	/*
	 * 1. Ŭ������ --> �빮�ڷ� ����
	 * 2. ��Ű���� --> �ҹ��ڷ� ����
	 * 3. �޼ҵ�� --> �ҹ��ڷ� ����
	 * 4. ������    --> �ҹ��ڷ� ����
	 * 
	 * ��, �������� �ܾ ���� ��� �빮�ڷ� �������ش�. == ��Ÿǥ���
	 * 
	 *  �׻� �ǹ̸� �����ϸ� �̸��� ��������. �밭 � �ǹ����� �˾ƺ� �� �ֵ���!
	 * */
	public void printValue() {
		
		// 1. ���� ��� --> ����ǥ ���� 
		System.out.println(123);
		System.out.println(1.11);
		
		// 2. ����(�� ����)
		System.out.println('a');
		System.out.println('#');
		
		// 3. ���ڿ�(���� ����)
		System.out.println("a#eiojfwe");
		System.out.println("�ѱ۵� �ȴ�!");
		System.out.println("��ĭ\n�Ʒ���");
		
		// 4. ������ ��� ���� ��� ����! 
		System.out.println(3+4);
		System.out.println(3-4);
		System.out.println(4/2);
		System.out.println(4*2);
		System.out.println(1.23-0.23);
		System.out.println(1.23-0.12);//0.11 
		//��ǻ�Ϳ����� �Ǽ��� ������ ������ ���� �� �ִ�.
		
		// 5. ���ڿ� ���ڵ� ���� ���� / ���ڸ��� ��ǻ�Ͱ� �ν��ϴ� ������ ���ڰ��� �ִ�.
		System.out.println('a'+ 1); //a�� 97
		System.out.println('A'+ 1); //A�� 65
		System.out.println('!'+ 1);
		System.out.println('��'+ 1);
		
		// 6. ���ڿ�("")�� �� ���� ������ ���� ���갡��. ������� ���ڿ��̴�.
		System.out.println("������"+'��'+5+"��ĥ�ȱ�"+'~');
		
	}
	
	// ���ڿ��� ���ڰ��� ���� ���� ��ɿ� �޼ҵ�
	public void sumStringNum() {
		System.out.println(9 + 9); // 18
		System.out.println(9 + "9"); // "99"
		System.out.println("9" + 9); // "99"
		
		//������ ->�������� ����ȴ�. ���� ���� ������ ���ڿ� ������ ���ڿ�ȭ�ȴ�.
		System.out.println(9 + 9 + "9"); //189
		System.out.println(9 + "9" + 9); //999
		System.out.println("9" + 9 + 9); //999
		System.out.println("9"+ (9 + 9)); //918
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
