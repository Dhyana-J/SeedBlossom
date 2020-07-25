package com.hw1.model.dao;

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

	public void fileSave() throws NoSuchElementException{ // ��ĳ�� �Է°����� ctrl+z ���� ��� �����Լ��� ���� ����������

		// StringBuilder ���� �� ����ϴ� ���� -> https://hardlearner.tistory.com/288 
		StringBuilder sb = new StringBuilder();

		while(true) {

			System.out.println("���Ͽ� ������ ������ �ݺ��ؼ� �Է��ϼ���(exit�Է��ϸ� ���� �Է� ��) : ");
			String input = sc.nextLine();

			if(input.toUpperCase().equals("EXIT")) {//�Է����� exit�Ǵ� EXIT ������ �Է� ����.
				sb.deleteCharAt(sb.length()-1);//exit�Է��� ��� ������ �࿡ ���Ե� ���๮�ڸ� �����.
				break;
			}else {
				sb.append(input); //sb�� �Է°� �߰� �� �ݺ��� �����!
				sb.append("\n"); //���� �Է� �� �������ش�.

			}
		}//while�� �Է� ����.

		System.out.print("�����Ͻðڽ��ϱ�?(y/n) : ");
		char yN = ' ';
		yN = sc.nextLine().toUpperCase().charAt(0);

		if(yN=='Y') {//�Է����� y�Ǵ� Y������ ��� ���Ͽ� ���� ����

			System.out.print("������ ���ϸ��� �Է��ϼ��� : ");
			String fileName = null;
			fileName = sc.nextLine();
			fileName = cutTxt(fileName); //Ȯ���� �Է� �� �����ش�.

			//			BufferedWriter / FileWriter Ȱ���
			//			�Ʒ� ��ũ ����.. ��ũ ���̷�?..
			//			https://marshallslee.tistory.com/entry/%EC%9E%90%EB%B0%94IO-%ED%85%8D%EC%8A%A4%ED%8A%B8-%ED%8C%8C%EC%9D%BC-%EC%83%9D%EC%84%B1%ED%95%98%EA%B3%A0-%ED%8C%8C%EC%9D%BC%EC%97%90-%ED%85%8D%EC%8A%A4%ED%8A%B8-%EC%93%B0%EA%B8%B0-2-FileWriter-%EB%B0%8F-BufferedWriter%ED%99%9C%EC%9A%A9

			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName+".txt"))){//bw.close�ڵ����� ���ش�.

				bw.write(sb.toString()); //�Է¹޾Ҵ� ������ ���Ͽ� ������.
				System.out.println(fileName+".txt ���Ͽ� ���������� �����Ͽ����ϴ�.");

			}catch(IOException e) {
				e.printStackTrace();
			}

		}
		else {//�Է����� n�Ǵ� N������ ��� �޴��� ���ư���
			System.out.println("�ٽ� �޴��� ���ư��ϴ�.");
			return; //fileSave() ����.
		}

	}

	public String cutTxt(String fileName){//���ϸ� �Է� �� ����ڰ� txtȮ���ڱ��� �Է������� Ȯ���ڴ� �����ִ� �Լ�

		if(fileName.contains(".txt")) {
			System.out.println("Ȯ���� �Է¾��ص��ſ�");
			//			return fileName.replaceAll(".txt",""); //������ : ���ϸ� .txt�ְ�; ���ִ´�.

			//�����ذ��ϱ�
			//Ȯ���ڴ� �׻� ���ϸ� ���� ��ġ�ϹǷ�, ���ϸ��� �������� ���ϸ� ���ڿ� 0,1,2,3��°�� t x t . �̷��� �� ���ڰ� ���Եȴ�. �̰� ������.
			StringBuffer sb = new StringBuffer(fileName); //���ڿ��� ������ ���� StringBuffer�̿�.
			fileName = sb.reverse().toString();
			fileName = fileName.replaceFirst("txt.",""); //ó������ ������ txt.�� ������ �����ش�.
			sb = new StringBuffer(fileName);

			return sb.reverse().toString(); //�ٽ� ����� ���󺹱��Ѱ� ����������.
		}
		else {
			System.out.println("���ϸ��� �� �Է��߱���");
			return fileName;
		}
	}


	public void fileOpen() throws NoSuchElementException{

		System.out.print("������ ���� �̸� : ");
		String fileName = sc.nextLine();
		fileName = cutTxt(fileName);

		try(BufferedReader br = new BufferedReader(new FileReader(fileName+".txt"))){ //�˾Ƽ� stream.close()���ش�.

			String line; //�о���� ���� �� �� ������ ���ڿ�
			while((line = br.readLine())!=null) { //�о���� ������ null�� �ƴϸ� ���. 
				System.out.println(line);
			}

		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	public void fileEdit() throws NoSuchElementException{

		System.out.print("������ ���� �̸� : ");
		String fileName = sc.nextLine();
		fileName = cutTxt(fileName);

		//�о���� ���� ���� ���. fileOpen()�� ���� ����.
		try(BufferedReader br = new BufferedReader(new FileReader(fileName+".txt"))){ //�˾Ƽ� stream.close()���ش�.

			String line; //�о���� ���� �� �� ������ ���ڿ�
			while((line = br.readLine())!=null) { //�о���� ������ null�� �ƴϸ� ���. 
				System.out.println(line);
			}

		}catch(IOException e) {
			e.printStackTrace();
		}

		//���Ͽ� ���� �߰�. fileSave()�޼ҵ�� ���� ����.
		StringBuilder sb = new StringBuilder();

		while(true) {

			System.out.println("���Ͽ� �߰��� ������ �Է��ϼ���(exit�Է��ϸ� ���� �Է� ��) : ");
			String input = sc.nextLine();

			if(input.toUpperCase().equals("EXIT")) {//�Է����� exit�Ǵ� EXIT ������ �Է� ����.
				sb.deleteCharAt(sb.length()-1);//exit�Է��� ��� ������ �࿡ ���Ե� ���๮�ڸ� �����.
				break;
			}else {
				sb.append("\n"); //������ ���̹Ƿ� ������ ���뿡�� ���� �ϰ� ������ �־�����.
				sb.append(input); //sb�� �Է°� �߰� �� �ݺ��� �����!
			}
			
		}//while�� �Է� ����.

		System.out.print("���� ������ ���Ͽ� �߰��Ͻðڽ��ϱ�?(y/n) : ");
		char yN = ' ';
		yN = sc.nextLine().toUpperCase().charAt(0);

		if(yN=='Y') {//�Է����� y�Ǵ� Y������ ��� ���Ͽ� ���� ����

			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName+".txt",true))){//bw.close�ڵ����� ���ش�. true���� �߰��ؼ� ���� �߰����� ����.

				bw.write(sb.toString()); //�Է¹޾Ҵ� ������ ���Ͽ� ������.
				System.out.println(fileName+".txt ������ ������ ����Ǿ����ϴ�.");

			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}

		}
		else {//�Է����� n�Ǵ� N������ ��� �޴��� ���ư���
			System.out.println("�ٽ� �޴��� ���ư��ϴ�.");
			return; //fileSave() ����.
		}



	}




}
