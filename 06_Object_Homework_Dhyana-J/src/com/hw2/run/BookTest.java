package com.hw2.run;
import com.hw2.model.vo.*;

public class BookTest {
	
	public static void main(String[] args) {
		
		Book b1 = new Book();
		Book b2 = new Book("�ڹ�������",20000,0.2,"����");
		
		//�� ��ü �ʵ� �� ���
		System.out.println(b1.information());
		System.out.println(b2.information());
		System.out.println("=========================");
		
		//ù ��° ��ü �� ����
		b1.setTitle("C���");
		b1.setPrice(30000);
		b1.setDiscountRate(0.1);
		b1.setAuthor("ȫ�浿");
		
		System.out.println("������ ��� Ȯ��");
		System.out.println(b1.information());
		System.out.println("=========================");
		
		System.out.println("������ = "+b1.getTitle());
		System.out.printf("���ε� ���� = %d��\n",(int)(b1.getPrice()*(1-b1.getDiscountRate())));
		System.out.println("������ = "+b2.getTitle());
		System.out.printf("���ε� ���� = %d��\n",(int)(b2.getPrice()*(1-b2.getDiscountRate())));
		
		
	}

}
