package com.kh.first; // ��Ű�� �����!

public class A_MethodPrinter {
	//class���� �빮��, method���� �ҹ��ڷ� �����ؾ��Ѵ�.
	
	// ��� ���� == �޼ҵ� 
	// main �޼ҵ�� Ŭ�������� ���� �ʿ䰡 ����. ��������Ʈ�� ��� 1���� �ݵ�� �־����
	
	// �Ϲݸ޼ҵ� public void �޼ҵ��(){} 
	public void mA() {
		System.out.println("mA�� ȣ��Ǿ���");
		mB();
		mC();
	}
	
	public void mB() {
		System.out.println("mB�� ȣ��Ǿ����ϴ�");
	}
	
	public void mC() {
		System.out.println("mC�� ȣ��Ǿ��ҿ�");
	}

}
