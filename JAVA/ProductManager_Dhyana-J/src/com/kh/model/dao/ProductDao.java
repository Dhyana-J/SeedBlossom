package com.kh.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.model.vo.Product;

import static com.kh.common.JDBCTemplate.*;

public class ProductDao {

	private Properties prop = new Properties();

	//ProductService.java에서 new ProductDao() 할 때마다 query파일에서 데이터를 읽어오도록 한다.
	//이는 ProductDao.java의 함수 호출 시 반복적으로 prop.load해주는 수고를 줄여준다.
	public ProductDao() {
		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> selectList(Connection conn){

		ArrayList<Product> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectList");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				list.add(new Product(rset.getString("PRODUCT_ID"),
						rset.getString("P_NAME"),
						rset.getInt("PRICE"),
						rset.getString("DESCRIPTION"),
						rset.getInt("STOCK")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertProduct(Connection conn,Product p) {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertProduct");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getpId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDsc());
			pstmt.setInt(5, p.getStock());

			result = pstmt.executeUpdate();

			if(result>0) {
				commit(conn);
			}else {
				rollback(conn);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public int updateProduct(Connection conn, Product p) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateProduct");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getpName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setString(3, p.getDsc());
			pstmt.setInt(4, p.getStock());
			pstmt.setString(5, p.getpId());

			result = pstmt.executeUpdate();

			if(result>0) {
				commit(conn);
			}else {
				rollback(conn);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteProduct(Connection conn, String productId) {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteProduct");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);

			result = pstmt.executeUpdate();

			if(result>0) {
				commit(conn);
			}else {
				rollback(conn);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Product> searchProduct(Connection conn, String name){

		ArrayList<Product> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchProduct");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");

			rset = pstmt.executeQuery();

			while(rset.next()) {
				list.add(new Product(rset.getString("PRODUCT_ID"),
						rset.getString("P_NAME"),
						rset.getInt("PRICE"),
						rset.getString("DESCRIPTION"),
						rset.getInt("STOCK")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

}
