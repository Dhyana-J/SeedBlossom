package com.kh.chap03_override.run;

import com.kh.chap03_override.model.vo.Book;

public class Run {

	public static void main(String[] args) {

		Book bk1 = new Book("������ ����", "������", 10000);
		Book bk2 = new Book("�� �ο� ����� ������","��",20000);
		
		System.out.println(bk1.information());
		System.out.println(bk2.information());
		System.out.println(bk1.toString());
		
		Book bk3 = new Book("������ ����", "������", 10000); // bk1��ü�� ������ �ʵ尪�� ���� bk3��ü ����
		
		System.out.println("bk1�� bk3�� ���� å�Դϱ�? "+(bk1 == bk3));
		
		// ���۷������� ����� �� ��, equals() �޼ҵ� ��� ����
		System.out.println("bk1�� bk3�� ���� å�Դϱ�? "+bk1.equals(bk3));
		
		System.out.println(bk1.getTitle().hashCode());
		System.out.println(bk3.getTitle().hashCode());
	}

}
