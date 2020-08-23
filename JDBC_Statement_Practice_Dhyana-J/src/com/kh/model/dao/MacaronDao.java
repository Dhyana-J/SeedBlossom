package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Macaron;

public class MacaronDao {

	public int insertMacaron(Macaron m) {

		int result = 0;
		Connection conn = null;
		Statement stmt = null;

		String sql = "INSERT INTO MACARON_LIST VALUES(SEQ_MACNO.NEXTVAL"
				+",'"+m.getName()+"'"
				+",'"+m.getColor()+"'"
				+",'"+m.getFlavor()+"',SYSDATE)";

		try {
			//1)JDBC driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				//2)Connection 객체 생성
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MACARON","MACARON");

				//3)Statement 객체 생성
				stmt = conn.createStatement();

				// 4, 5) DB에 SQL문 전달하며 실행 후 결과 받기 (처리된 행 수)
				result = stmt.executeUpdate(sql);

				// 6_2) 트랜잭션 처리
				if(result>0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//stmt 객체를 통해 받은 쿼리 실행 결과값을 result에 저장했으므로, 반환해주자
		return result;
	}



	/**
	 * 마카롱 목록 조회요청 처리함수
	 */
	public ArrayList<Macaron> selectList() {

		ArrayList<Macaron> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; // select 쿼리로 요청하면 결과를 rset에 담는다.

		String sql = "SELECT * FROM MACARON_LIST";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MACARON","MACARON");
				stmt = conn.createStatement();
				rset = stmt.executeQuery(sql); //SELECT 실행 시 executeQuery 사용

				while(rset.next()) {//rset에 마지막 결과값까지 반복

					list.add(new Macaron(rset.getInt("MACARON_NO"),
							rset.getString("NAME"),
							rset.getString("COLOUR"),
							rset.getString("FLAVOR"),
							rset.getDate("BAKED_DATE")));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	public Macaron selectByMacaronName(String name) {
		Macaron m = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MACARON_LIST WHERE NAME = '"+name+"'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MACARON","MACARON");
				stmt = conn.createStatement();
				rset = stmt.executeQuery(sql);

				if(rset.next()) { //검색값이 있을 경우
					m = new Macaron(rset.getInt("MACARON_NO"),
							rset.getString("NAME"),
							rset.getString("COLOUR"),
							rset.getString("FLAVOR"),
							rset.getDate("BAKED_DATE"));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return m;
	}

	public ArrayList<Macaron> selectByMacaronColor(String color){

		ArrayList<Macaron> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MACARON_LIST WHERE COLOUR LIKE('%"+color+"%')";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MACARON","MACARON");
				stmt = conn.createStatement();
				rset = stmt.executeQuery(sql);

				while(rset.next()) {//결과가 여러개일 수도 있음. 그래서 while쓴다.

					list.add(new Macaron(rset.getInt("MACARON_NO"),
							rset.getString("NAME"),
							rset.getString("COLOUR"),
							rset.getString("FLAVOR"),
							rset.getDate("BAKED_DATE")));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	public int updateMacaron(Macaron m) {

		int result = 0;
		Connection conn = null;
		Statement stmt = null;

		String sql = "UPDATE MACARON_LIST SET"
					+" COLOUR = '"+m.getColor()+"',"
					+" FLAVOR = '"+m.getFlavor()+"'"
					+" WHERE NAME = '"+m.getName()+"'";

		try {
			//1)JDBC driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				//2)Connection 객체 생성
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MACARON","MACARON");

				//3)Statement 객체 생성
				stmt = conn.createStatement();

				// 4, 5) DB에 SQL문 전달하며 실행 후 결과 받기 (처리된 행 수)
				result = stmt.executeUpdate(sql);

				// 6_2) 트랜잭션 처리
				if(result>0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//stmt 객체를 통해 받은 쿼리 실행 결과값을 result에 저장했으므로, 반환해주자
		return result;

	}
	
	public int deleteMacaron(String name) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;

		String sql = "DELETE FROM MACARON_LIST WHERE NAME = '"+name+"'";

		try {
			//1)JDBC driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				//2)Connection 객체 생성
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","MACARON","MACARON");

				//3)Statement 객체 생성
				stmt = conn.createStatement();

				// 4, 5) DB에 SQL문 전달하며 실행 후 결과 받기 (처리된 행 수)
				result = stmt.executeUpdate(sql);

				// 6_2) 트랜잭션 처리
				if(result>0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//stmt 객체를 통해 받은 쿼리 실행 결과값을 result에 저장했으므로, 반환해주자
		return result;

		
	}

}
