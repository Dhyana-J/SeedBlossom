package com.method.hw2.run;
import com.method.hw2.controller.*;

public class Run {

	public static void main(String[] args) {

		NonStaticSample n = new NonStaticSample();
		
		
		int length = 5; //�迭 ũ������ ����
		int[] arr = n.intArrayAllocation(length);
		
		String str1 = "apple"; //input String
		char c = 'p';
		
		
		int num1 = 13; //���ۼ�
		int num2 = 7; //����
		
		String str2 = "programming";
		int index = 3;
		
		String str3 = "JAVA";
		
		System.out.printf("- ũ�Ⱑ %d�� �迭�� ������ : ",length);
		n.display(arr);
		System.out.print("- �������� ��� : ");
		n.sortDescending(arr);
		System.out.print("- �������� ��� : ");
		n.sortAscending(arr);
		System.out.println();
		System.out.printf("- %s���ڿ��� %c�� ���� : %d",str1,c,n.countChar(str1, c));
		System.out.println();
		System.out.printf("- %d��(��) %d���� �������� �հ� : %d",num1,num2,n.totalValue(num1, num2));
		System.out.println();
		System.out.printf("%s ���ڿ��� %d�� �ε��� ���� : %c",str2,index,n.pCharAt(str2, index));
		System.out.println();
		System.out.printf("%s�� %s�� ��ģ ���ڿ� : %s",str3,str2,n.pConcat(str3, str2));
		System.out.println();
		
	}

}
