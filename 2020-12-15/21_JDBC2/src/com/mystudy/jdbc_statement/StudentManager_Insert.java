package com.mystudy.jdbc_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager_Insert {

	public static void main(String[] args) {
		/*	0. JDBC 라이브러리 사용 개발환경 설정(jdbc.jar 빌드패스 등록)
		 
		 
	 	1. JDBC 드라이버 로딩
		2. DB연결하고 Connection 객체 생성
		3. Statement 문 생성 및 실행(SQL문 - 쿼리문실행)
		4. 결과물에 대한 처리
			- SELECT : 조회한 데이터 결과값 처리
			- INSERT , UPDATE, DELETE : int  값(처리건수) 처리
		5. 클로징 처리에 의한 자원 반납
	 */
		Connection conn = null;
		Statement stmt = null;
		try {
			//  1. JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			
			//  2. DB연결하고 Connection 객체 생성
	
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"mystudy", "mystudypw");
			
		   //   3. Statement 문 생성 및 실행(SQL문 - 쿼리문실행)
			stmt = conn.createStatement();
			String id = "2020110";
			String name = "홍길동111";
			int kor = 99;
			int eng = 80;
			int math = 70;
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO STUDENT (ID, NAME, KOR, ENG, MATH) ");
			sql.append("VALUES ('" + id + "', '" + name + "', " + kor +", " + eng + ", " 
																			+ math + ") ");
			System.out.println("sql : " + sql.toString());
				
			int cnt = stmt.executeUpdate(sql.toString());
			
			//  4. 결과물에 대한 처리
			System.out.println(">>>입력건수: " + cnt);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null) stmt.close();
				// if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
