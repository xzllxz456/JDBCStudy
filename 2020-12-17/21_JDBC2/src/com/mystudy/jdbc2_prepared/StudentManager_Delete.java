package com.mystudy.jdbc2_prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager_Delete {

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
		try {
			//1. JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. DB연결하고 Connection 객체 생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. Statement 문 생성 및 실행(SQL문 - 쿼리문실행)
			String id = "2020111";
						
			String sql = "DELETE FROM STUDENT WHERE ID = ? ";
			System.out.println("sql : " + sql);
			pstmt = conn.prepareStatement(sql);
			//값설정 : ?(바인드변수)에 값을 설정
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			
			//4. 결과물에 대한 처리
			System.out.println(">> 처리건수 : " + cnt);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 로딩 실패!!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. 클로징 처리에 의한 자원 반납
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

		System.out.println(">>> main() 끝");
	}

}
