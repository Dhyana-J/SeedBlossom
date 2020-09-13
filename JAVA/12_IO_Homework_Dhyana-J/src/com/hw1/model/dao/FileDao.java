package com.hw1.model.dao;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileDao {

	Scanner sc = new Scanner(System.in);

	public FileDao() {}
	
	public void printFileList() {//수정가능한 파일 리스트 출력해주는 함수.
		
		File dir = new File("C:\\Users\\magho909\\Desktop\\KH\\KH_workspace\\12_IO_Homework_Dhyana-J"); //여기 디렉토리에는 본인 컴퓨터 프로젝트폴더 경로 입력!
		String[] files = dir.list();

		for(String e : files) {
			if(e.contains(".txt"))System.out.println(e); //디렉토리의 파일들 중 .txt확장자만 출력해준다.
		}
	}

	public void fileSave() throws NoSuchElementException{ // 스캐너 입력값으로 ctrl+z 들어온 경우 메인함수로 예외 던져버리기

		// StringBuilder 사용법 및 사용하는 이유 -> https://hardlearner.tistory.com/288 
		StringBuilder sb = new StringBuilder();

		while(true) {

			System.out.println("파일에 저장할 내용을 반복해서 입력하세요(exit입력하면 내용 입력 끝) : ");
			String input = sc.nextLine();

			if(input.toUpperCase().equals("EXIT")) {//입력으로 exit또는 EXIT 들어오면 입력 종료.
				sb.deleteCharAt(sb.length()-1);//exit입력한 경우 마지막 행에 삽입된 개행문자를 지운다.
				break;
			}else {
				sb.append(input); //sb에 입력값 추가 후 반복문 재실행!
				sb.append("\n"); //내용 입력 후 개행해준다.

			}
		}//while끝 입력 종료.

		System.out.print("저장하시겠습니까?(y/n) : ");
		char yN = ' ';
		yN = sc.nextLine().toUpperCase().charAt(0);

		if(yN=='Y') {//입력으로 y또는 Y들어오는 경우 파일에 저장 수행

			System.out.print("저장할 파일명을 입력하세요 : ");
			String fileName = null;
			fileName = sc.nextLine();
			fileName = cutTxt(fileName); //확장자 입력 시 지워준다.

			//			BufferedWriter / FileWriter 활용법
			//			아래 링크 참고.. 링크 왜이래?..
			//			https://marshallslee.tistory.com/entry/%EC%9E%90%EB%B0%94IO-%ED%85%8D%EC%8A%A4%ED%8A%B8-%ED%8C%8C%EC%9D%BC-%EC%83%9D%EC%84%B1%ED%95%98%EA%B3%A0-%ED%8C%8C%EC%9D%BC%EC%97%90-%ED%85%8D%EC%8A%A4%ED%8A%B8-%EC%93%B0%EA%B8%B0-2-FileWriter-%EB%B0%8F-BufferedWriter%ED%99%9C%EC%9A%A9

			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName+".txt"))){//bw.close자동으로 해준다.

				bw.write(sb.toString()); //입력받았던 내용을 파일에 써주자.
				System.out.println(fileName+".txt 파일에 성공적으로 저장하였습니다.");

			}catch(IOException e) {
				e.printStackTrace();
			}

		}
		else {//입력으로 n또는 N들어오는 경우 메뉴로 돌아가자
			System.out.println("다시 메뉴로 돌아갑니다.");
			return; //fileSave() 종료.
		}

	}

	public String cutTxt(String fileName){//파일명 입력 시 사용자가 txt확장자까지 입력했으면 확장자는 지워주는 함수

		if(fileName.contains(".txt")) {
			System.out.println("확장자 입력안해도돼여");
			//			return fileName.replaceAll(".txt",""); //문제점 : 파일명에 .txt넣고싶어도 못넣는다.

			//문제해결하기
			//확장자는 항상 파일명 끝에 위치하므로, 파일명을 뒤집으면 파일명 문자열 0,1,2,3번째에 t x t . 이렇게 네 문자가 오게된다. 이걸 지우면됨.
			StringBuffer sb = new StringBuffer(fileName); //문자열을 뒤집기 위해 StringBuffer이용.
			fileName = sb.reverse().toString();
			fileName = fileName.replaceFirst("txt.",""); //처음으로 나오는 txt.이 있으면 지워준다.
			sb = new StringBuffer(fileName);

			return sb.reverse().toString(); //다시 뒤집어서 원상복구한거 리턴해주자.
		}
		else {
			System.out.println("파일명을 잘 입력했군요");
			return fileName;
		}
	}


	public void fileOpen() throws NoSuchElementException{

		System.out.print("오픈할 파일 이름 : ");
		String fileName = sc.nextLine();
		fileName = cutTxt(fileName);

		try(BufferedReader br = new BufferedReader(new FileReader(fileName+".txt"))){ //알아서 stream.close()해준다.

			String line; //읽어들인 내용 한 줄 저장할 문자열
			while((line = br.readLine())!=null) { //읽어들인 내용이 null이 아니면 출력. 
				System.out.println(line);
			}

		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	public void fileEdit() throws NoSuchElementException{

		System.out.print("수정할 파일 이름 : ");
		String fileName = sc.nextLine();
		fileName = cutTxt(fileName);

		//읽어들인 파일 내용 출력. fileOpen()과 거의 같다.
		try(BufferedReader br = new BufferedReader(new FileReader(fileName+".txt"))){ //알아서 stream.close()해준다.

			String line; //읽어들인 내용 한 줄 저장할 문자열
			while((line = br.readLine())!=null) { //읽어들인 내용이 null이 아니면 출력. 
				System.out.println(line);
			}

		}catch(IOException e) {
			e.printStackTrace();
		}

		//파일에 내용 추가. fileSave()메소드와 거의 같다.
		StringBuilder sb = new StringBuilder();

		while(true) {

			System.out.println("파일에 추가할 내용을 입력하세요(exit입력하면 내용 입력 끝) : ");
			String input = sc.nextLine();

			if(input.toUpperCase().equals("EXIT")) {//입력으로 exit또는 EXIT 들어오면 입력 종료.
				sb.deleteCharAt(sb.length()-1);//exit입력한 경우 마지막 행에 삽입된 개행문자를 지운다.
				break;
			}else {
				sb.append("\n"); //수정할 것이므로 기존의 내용에서 개행 하고 내용을 넣어주자.
				sb.append(input); //sb에 입력값 추가 후 반복문 재실행!
			}
			
		}//while끝 입력 종료.

		System.out.print("변경 내용을 파일에 추가하시겠습니까?(y/n) : ");
		char yN = ' ';
		yN = sc.nextLine().toUpperCase().charAt(0);

		if(yN=='Y') {//입력으로 y또는 Y들어오는 경우 파일에 저장 수행

			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName+".txt",true))){//bw.close자동으로 해준다. true값을 추가해서 내용 추가모드로 열자.

				bw.write(sb.toString()); //입력받았던 내용을 파일에 써주자.
				System.out.println(fileName+".txt 파일의 내용이 변경되었습니다.");

			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}

		}
		else {//입력으로 n또는 N들어오는 경우 메뉴로 돌아가자
			System.out.println("다시 메뉴로 돌아갑니다.");
			return; //fileSave() 종료.
		}



	}




}
