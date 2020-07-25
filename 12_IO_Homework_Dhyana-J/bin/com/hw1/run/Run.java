package com.hw1.run;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.hw1.model.dao.FileDao;

public class Run {

	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(System.in);
		
		FileDao fd = new FileDao();
		
		//�ݺ����� ���� �޴� ����
		while(true) {
			System.out.println();
			System.out.println("***** MyNote *****");
			System.out.println("1. ��Ʈ ���� �����");
			System.out.println("2. ��Ʈ ����");
			System.out.println("3. ��Ʈ ��� �����ϱ�");
			System.out.println("4. ��Ʈ ��� ����");
			System.out.println("5. ������");
			System.out.print("��ȣ�� �Է��ϼ��� : ");
			
			try {
				String input = sc.nextLine();

				switch(input) {
				case "1": fd.fileSave();break; 
				case "2": fd.fileOpen();break;
				case "3": fd.fileEdit();break;
				case "4": fd.printFileList(); break;
				case "5": System.out.println("���α׷��� �����մϴ�."); return;
				default: System.out.println("�߸� �����̽��ϴ�. 1~4 ���� ���ڸ� �Է��ϼ���");
				}
			}
			catch(NoSuchElementException e) { //Ȥ�ó� �Է°��� �̻��ϸ� ����ó��. �Է°����� ctrl+z�� �����ٵ簡?
				System.out.println("\n�������Ḧ �Է��߽��ϴ�. ���α׷��� �����մϴ�.");
				System.out.println("���� ���� : ");
				e.printStackTrace();
				System.exit(0);//���α׷��� �ٷ� ������ѹ�����.
//				exit ���� ���� : https://coding-factory.tistory.com/526
//				return; //�Լ�(�޼ҵ�)�� �����Ų��.
//				exit�� return�� ����? main�Լ��� ����� ������ ������ ��Եɱ�? 
			}
			
		}
		
	}
}
