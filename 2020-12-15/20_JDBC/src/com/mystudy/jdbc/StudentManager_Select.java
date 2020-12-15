package com.mystudy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager_Select {
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
		
		// 1. JDBC 드라이버 로딩
		try {
			// Class.forName("oracle.jdbc.driver.OracleDriver"); 
			Class.forName("oracle.jdbc.OracleDriver");  // 이름으로 찾아서 jdbc에 연결 
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 로딩 실패");
			e.printStackTrace();
		} 
		
		// 2. DB연결하고 Connection 객체 생성		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"mystudy", "mystudypw");
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@127.0.1:1521:xe",
//					"mystudy", "mystudypw");
		} catch (SQLException e) {
			System.out.println("[예외] DB연결 실패(connection fail)");
			e.printStackTrace();
		}
		
		// 3. Statement 문 생성 및 실행(SQL문 - 쿼리문실행)
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();	// 연결하는 객체
			String sql = ""  
							+ "SELECT ID, NAME, KOR, ENG, MATH "
							+ "FROM STUDENT " 
							+ "ORDER BY ID ASC";
			rs = stmt.executeQuery(sql);
			// 4. 결과에 대한 처리
		while (rs.next()) {
			String str = "";
			str += rs.getString("ID") + "\t";
			str += rs.getString("NAME") + "\t";
			str += rs.getInt("KOR") + "\t";
			str += rs.getInt("ENG") + "\t";
			str += rs.getInt("MATH") + "\t";
			
			System.out.println(str);
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 5. 클로징 처리에 의한 자원 반납
		try {
			if(rs != null)
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(conn);
		System.out.println(">>>main() 끝");
	}
}
