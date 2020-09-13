package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {

	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();


	//메인페이지. 사용자가 처음으로 볼 화면
	public void mainMenu() {

		String menu;

		while(true) {

			System.out.println("\n === 상품 관리 프로그램 ===");
			System.out.println("1. 상품 전체 조회");
			System.out.println("2. 상품 추가");
			System.out.println("3. 상품 수정 (상품 id로 조회하고 수정)");
			System.out.println("4. 상품 삭제 (상품 id로 조회하고 삭제");
			System.out.println("5. 상품 검색 (상품 이름으로 키워드 검색)");
			System.out.println("0. 프로그램 종료");

			System.out.print("메뉴 선택 : ");

			menu = sc.nextLine();

			switch(menu) {
			case "1": pc.selectList(); break;
			case "2": insertProduct(); break;
			case "3": updateProduct(); break;
			case "4": pc.deleteProduct(inputProductId()); break;
			case "5": pc.searchProduct(inputProductName());break;
			case "0": System.out.print("정말 종료하시겠습니까?(y/n)");
			if(sc.nextLine().toUpperCase().equals("Y")) return;
			else break;
			default : System.out.println("다시 입력하시오!");
			}

		}



	}


	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공!!"+message);
	}

	public void displayFail(String message) {
		System.err.println("\n 서비스 요청 실패!!"+message);
	}

	public void displayNoData(String message) {
		System.err.println("\n "+message);
	}

	public void displayProductList(ArrayList<Product> list) {
		System.out.println("\n 조회된 상품 리스트\n");

		for(Product p : list) {
			System.out.println(p);
		}
	}

	public String inputProductId() {
		System.out.print("\n 상품 id 입력 : ");
		return sc.nextLine();
	}

	public String inputProductName() {
		System.out.print("\n 상품 이름 입력 : ");
		return sc.nextLine();
	}


	public void insertProduct() {

		Product p = new Product();

		System.out.println("\n === 상품 추가 === \n");
		System.out.print("상품 id 입력 : ");
		p.setpId(sc.nextLine());
		System.out.print("상품명 입력 : ");
		p.setpName(sc.nextLine());
		System.out.print("상품가격 입력 : ");
		p.setPrice(sc.nextInt());
		sc.nextLine();//버퍼비우자
		System.out.print("상품상세 입력 : ");
		p.setDsc(sc.nextLine());
		System.out.print("상품재고 입력 : ");
		p.setStock(sc.nextInt());
		sc.nextLine();

		pc.insertProduct(p);

	}

	public void updateProduct() {

		System.out.println("\n === 상품 수정 === \n");
		Product p = new Product();

		p.setpId(inputProductId());

		System.out.print("변경할 상품명 : ");
		p.setpName(sc.nextLine());
		System.out.print("변경할 상품가격 : ");
		p.setPrice(sc.nextInt());
		sc.nextLine();
		System.out.print("변경할 상품상세 : ");
		p.setDsc(sc.nextLine());
		System.out.print("변경할 상품재고 : ");
		p.setStock(sc.nextInt());
		sc.nextLine();

		System.out.print("정말 수정하시겠습니까?(y/n) : ");
		String yN = sc.nextLine().toUpperCase();

		if(yN.equals("Y")) pc.updateProduct(p); //수정 실행
		else {
			System.out.println("메뉴로 돌아갑니다.");
			return; //수정 안하고 메뉴로 돌아간다.
		}

	}




}
