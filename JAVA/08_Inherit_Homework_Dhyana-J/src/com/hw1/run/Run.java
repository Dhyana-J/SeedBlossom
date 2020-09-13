package com.hw1.run;
import java.util.Scanner;

import com.hw1.model.vo.*;

public class Run {

	public static void main(String[] args) {

		Student[] s = new Student[3];
		s[0] = new Student("홍길동",20,178.2,70.0,1,"정보시스템공학과");
		s[1] = new Student("김말똥",21,187.3,80.0,2,"경영학과");
		s[2] = new Student("강개순",23,167.0,45.0,4,"정보통신공학과");
		
		//학생 정보 모두 출력
		for(int i = 0; i<s.length; i++) {
			System.out.println(s[i].information());
		}
		
		Employee[] e = new Employee[10];
//		e[0] = new Employee("박보검",28,180.3,72.0,100000000,"영업부");
//		e[1] = new Employee("강동원",40,182.0,76.0,200000000,"기획부");
		
		int count = 0; // 객체 추가될 때 마다 1씩 증가
		
		//사용자에게 입력받아 생성자 매개변수에 대입해줄 값들
		String name;
		int age;
		double height;
		double weight;
		int salary;
		String dept;
		
		while(true) {
			
			
			Scanner sc = new Scanner(System.in);
			System.out.print("이름을 입력하세요 : ");
			name = sc.nextLine();
			System.out.print("나이를 입력하세요 : ");
			age = sc.nextInt();
			System.out.print("키를 입력하세요 :");
			height = sc.nextDouble();
			System.out.print("몸무게를 입력하세요 : ");
			weight = sc.nextDouble();
			System.out.print("연봉을 입력하세요 : ");
			salary = sc.nextInt();
			System.out.print("부서를 입력하세요 : ");
			sc.nextLine();
			dept = sc.nextLine();
			
			e[count] = new Employee(name,age,height,weight,salary,dept);
			count++;
			
			if(count==10){
			 System.out.println("저장소가 가득 찼어요");
			 break;
			}
			
			
			System.out.print("계속 추가하시겠습니까?(y/n) : ");
			String input = sc.nextLine().toUpperCase();
			if(input.equals("N")) {
				break;
			}
			

		}
		
		//사원 정보 모두 출력
		for(int i = 0; i<count; i++) {
			System.out.println(e[i].information());
		}
		
		
	}

}
