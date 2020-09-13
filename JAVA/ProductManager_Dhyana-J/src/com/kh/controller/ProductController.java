package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {

	//상품 전체 조회
	public void selectList() {

		ArrayList<Product> list = new ProductService().selectList();

		if(list.isEmpty()) {
			new ProductMenu().displayNoData("상품이 없습니다.");
		}else {
			new ProductMenu().displayProductList(list);
		}
		
	}

	//상품 추가
	public void insertProduct(Product p) {
		
		if(new ProductService().insertProduct(p)>0) {
			new ProductMenu().displaySuccess("\n상품 추가 성공!!");
		}else {
			new ProductMenu().displayFail("\n상품 추가 실패!!");
		}

	}

	//상품 id로 찾아 업데이트
	public void updateProduct(Product p) {
		
		if(new ProductService().updateProduct(p)>0) {
			new ProductMenu().displaySuccess("\n상품 수정 성공!!");
		}else {
			new ProductMenu().displayFail("\n상품 수정 실패!!");			
		}

	}

	//상품 id로 찾아 삭제
	public void deleteProduct(String productId) {
		if(new ProductService().deleteProduct(productId)>0) {
			new ProductMenu().displaySuccess("\n상품 삭제 성공!!");
		}else {
			new ProductMenu().displayFail("\n상품 삭제 실패!!");			
		}

	}

	//상품 이름으로 검색
	public void searchProduct(String name) {
		
		ArrayList<Product> list = new ProductService().searchProduct(name);
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData("상품이 없습니다.");
		}else {
			new ProductMenu().displayProductList(list);
		}
	}


}
