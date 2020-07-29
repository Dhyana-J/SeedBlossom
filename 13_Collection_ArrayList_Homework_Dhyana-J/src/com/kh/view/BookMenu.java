package com.kh.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.kh.controller.BookManager;
import com.kh.model.vo.Book;

public class BookMenu {

	private Scanner sc = new Scanner(System.in);
	private BookManager bm = new BookManager();

	public BookMenu() {}

	public void mainMenu() {
		while(true) {
			System.out.println();
			System.out.println("*** ���� ���� ���α׷� ***");
			System.out.println("1. �� ���� �߰�");
			System.out.println("2. ���� ����");
			System.out.println("3. ���� �˻� ���");
			System.out.println("4. ��ü ���");
			System.out.println("0. ������");
			System.out.print("�޴� ��ȣ ���� : ");
			String menu = sc.nextLine();

			switch(menu) {
			case "1": insertBook();break;
			case "2": deleteBook();break;
			case "3": searchBook();break;
			case "4": selectList();break;
			case "0": System.out.println("���α׷� ����"); return;
			default: System.out.println("�߸� �������ϴ�.");
			}
		}

	}

	public void insertBook() {
		//�������� �Է�
		System.out.print("\n���� ���� : ");
		String title = sc.nextLine();

		//���� �帣 �Է�
		int category = 0; //�Է¹��� ���� �帣 �����ϴ� ����
		while(true) {
			try {
				Scanner input = new Scanner(System.in);// �̰� ���ϰ� ���� sc���� ���ѷ���������..

				System.out.println("");
				System.out.print("���� �帣 (1:�ι� / 2:�ڿ����� / 3:�Ƿ� / 4:��Ÿ) : ");
				//				category = sc.nextInt(); //�̰ž��� ���ܹ߻� �� ���ѷ�������. 
				category = input.nextInt();
				input.nextLine();//���ۺ��

				switch(category) {
				case 1:break;
				case 2:break;
				case 3:break;
				case 4:break;
				default://1~4 �̿� ���� �Է� �� �ٽ��Է¹���.
					System.out.println("1~4���� ���ڸ� �Է��ϼ���!");
					continue;
				}
				break; //�����Էµ� ��� �ݺ��� ����������.

			}catch(InputMismatchException e){ //���ܹ߻� �� �����Ų��.
				System.out.println("���ڸ� �Է��ϼ���!");
			}
			continue; //���� �̿��� �� �Է��� ��� �ٽ� �Է¹޴´�.
		}

		//���� ���� �Է�
		System.out.print("���� ���� : ");
		String author = sc.nextLine();

		bm.insertBook(new Book(title,category,author));

	}

	public void deleteBook() {
		
		//������ ���� ��ȣ �Է�
		int bNo = 0; //������ ���� ��ȣ ������ ����
		while(true) {//���� �̿ܰ� �Է½� ����ó��. �ٽ��Է¹޴´�.
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("���� ��ȣ : ");
				bNo = input.nextInt();
				input.nextLine();//���ۺ����
				break;//�����Է½� �ݺ��� ����
			}catch(InputMismatchException e) {
				System.out.println("���ڸ� �Է��ϼ���!!");
			}
		}
		
		if(bm.deleteBook(bNo)==1) { // return�� 1�� ���
			System.out.println("���������� ����");
		}
		else {// return�� 0�ΰ��
			System.out.println("������ ������ �������� �ʽ��ϴ�.");
		}
	}

	public void searchBook() {

		System.out.print("���� ���� : ");
		String title = sc.nextLine();

		if(bm.searchBook(title).isEmpty()) {//searchList������� ���
			System.out.println("�˻� ����� �������� �ʽ��ϴ�.");
		}
		else {//searchList���
			for(Book e : bm.searchBook(title)) {
				System.out.println(e);
			}
		}
	}

	public void selectList() {
		if(bm.selectList().isEmpty()) {//selectList����������
			System.out.println("���� ����� �������� �ʽ��ϴ�.");
		}
		else {
			for(Book e : bm.selectList()) {
				System.out.println(e);
			}
		}
	}

}
