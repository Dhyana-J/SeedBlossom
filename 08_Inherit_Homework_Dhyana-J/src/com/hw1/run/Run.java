package com.hw1.run;
import java.util.Scanner;

import com.hw1.model.vo.*;

public class Run {

	public static void main(String[] args) {

		Student[] s = new Student[3];
		s[0] = new Student("ȫ�浿",20,178.2,70.0,1,"�����ý��۰��а�");
		s[1] = new Student("�踻��",21,187.3,80.0,2,"�濵�а�");
		s[2] = new Student("������",23,167.0,45.0,4,"������Ű��а�");
		
		//�л� ���� ��� ���
		for(int i = 0; i<s.length; i++) {
			System.out.println(s[i].information());
		}
		
		Employee[] e = new Employee[10];
//		e[0] = new Employee("�ں���",28,180.3,72.0,100000000,"������");
//		e[1] = new Employee("������",40,182.0,76.0,200000000,"��ȹ��");
		
		int count = 0; // ��ü �߰��� �� ���� 1�� ����
		
		//����ڿ��� �Է¹޾� ������ �Ű������� �������� ����
		String name;
		int age;
		double height;
		double weight;
		int salary;
		String dept;
		
		while(true) {
			
			
			Scanner sc = new Scanner(System.in);
			System.out.print("�̸��� �Է��ϼ��� : ");
			name = sc.nextLine();
			System.out.print("���̸� �Է��ϼ��� : ");
			age = sc.nextInt();
			System.out.print("Ű�� �Է��ϼ��� :");
			height = sc.nextDouble();
			System.out.print("�����Ը� �Է��ϼ��� : ");
			weight = sc.nextDouble();
			System.out.print("������ �Է��ϼ��� : ");
			salary = sc.nextInt();
			System.out.print("�μ��� �Է��ϼ��� : ");
			sc.nextLine();
			dept = sc.nextLine();
			
			e[count] = new Employee(name,age,height,weight,salary,dept);
			count++;
			
			System.out.print("��� �߰��Ͻðڽ��ϱ�?(y/n) : ");
			String input = sc.nextLine().toUpperCase();
			if(input.equals("N")) {
				break;
			}
			

		}
		
		//��� ���� ��� ���
		for(int i = 0; i<count; i++) {
			System.out.println(e[i].information());
		}
		
		
	}

}
