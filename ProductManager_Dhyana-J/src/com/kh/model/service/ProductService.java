package com.kh.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;

public class ProductService {

	public ArrayList<Product> selectList(){

		Connection conn = getConnection();

		ArrayList<Product> list = new ProductDao().selectList(conn);

		close(conn);

		return list;

	}

	public int insertProduct(Product p) {

		Connection conn = getConnection();

		int result = new ProductDao().insertProduct(conn, p);

		close(conn);

		return result;
	}

	public int updateProduct(Product p) {

		Connection conn = getConnection();

		int result = new ProductDao().updateProduct(conn,p);

		close(conn);

		return result;
	}

	public int deleteProduct(String productId) {

		Connection conn = getConnection();

		int result = new ProductDao().deleteProduct(conn,productId);

		close(conn);
		
		return result;
	}
	
	public ArrayList<Product> searchProduct(String name){
		
		Connection conn = getConnection();
		
		ArrayList<Product> list = new ProductDao().searchProduct(conn,name);
		
		close(conn);
		
		return list;
	}

}
