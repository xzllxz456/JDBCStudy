package com.mystudy.jdbc2_prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManager_Select {

	public static void main(String[] args) {
		/*
		JDBC 이용한 DB 프로그래밍 방법
		0. JDBC 라이브러리 사용 개발환경 설정(jdbc.jar 빌드패스 등록)
		1. JDBC 드라이버 로딩
		2. DB연결하고 Connection 객체 생성
		3. Statement 문 생성 및 실행(SQL문 - 쿼리문실행)
		4. 결과물에 대한 처리
			- SELECT : 조회 데이터 결과값 처리
			- INSERT, UPDATE, DELETE : int 값(처리건수) 처리
		5. 클로징 처리에 의한 자원 반납
		*/		

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. DB연결하고 Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. Statement 문 생성 및 실행(SQL문 - 쿼리문실행)
			String sql = ""
					+ "SELECT ID, NAME, KOR, ENG, MATH "
					+ "FROM STUDENT "
					//+ "WHERE ID = ? "
					+ "ORDER BY ID";
			pstmt = conn.prepareStatement(sql); //SQL 실행 준비 요청
			//pstmt.setString(1, "2020111"); //실행시킬 SQL에 값 매칭
			rs = pstmt.executeQuery(); //준비된 SQL 실행요청
			while (rs.next()) {
				String str = "";
				str += rs.getString("ID") + "\t";
				str += rs.getString("NAME") + "\t";
				str += rs.getInt("KOR") + "\t";
				str += rs.getInt("ENG") + "\t";
				str += rs.getInt("MATH") + "\t";
				System.out.println(str);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}








