package com.method.hw2.run;
import com.method.hw2.controller.*;

public class Run {

	public static void main(String[] args) {

		NonStaticSample n = new NonStaticSample();
		
		
		int length = 5; //배열 크기지정 변수
		int[] arr = n.intArrayAllocation(length);
		
		String str1 = "apple"; //input String
		char c = 'p';
		
		
		int num1 = 13; //시작수
		int num2 = 7; //끝수
		
		String str2 = "programming";
		int index = 3;
		
		String str3 = "JAVA";
		
		System.out.printf("- 크기가 %d인 배열의 랜덤값 : ",length);
		n.display(arr);
		System.out.print("- 내림차순 출력 : ");
		n.sortDescending(arr);
		System.out.print("- 오름차순 출력 : ");
		n.sortAscending(arr);
		System.out.println();
		System.out.printf("- %s문자열에 %c의 갯수 : %d",str1,c,n.countChar(str1, c));
		System.out.println();
		System.out.printf("- %d와(과) %d사이 정수들의 합계 : %d",num1,num2,n.totalValue(num1, num2));
		System.out.println();
		System.out.printf("%s 문자열의 %d번 인덱스 문자 : %c",str2,index,n.pCharAt(str2, index));
		System.out.println();
		System.out.printf("%s와 %s을 합친 문자열 : %s",str3,str2,n.pConcat(str3, str2));
		System.out.println();
		
	}

}
