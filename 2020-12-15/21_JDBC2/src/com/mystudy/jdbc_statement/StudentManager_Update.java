package com.mystudy.jdbc_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager_Update {
	public static void main(String[] args) {
		System.out.println("main 시작");
		
		// 1. 드라이버 로딩
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			// 2. db연결하고 Connetion 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			// 3. Statement 문 생성 및 실행(SQL문 - 쿼리문실행)
			stmt = conn.createStatement();
			String id = "2020111";
			String name = "똥개";
			int kor = 50;
			int eng = 40;
			int math = 30;
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE STUDENT ");
			sql.append("SET NAME = '" + name + "', KOR = " + kor + ", ENG = " + eng +
												", MATH = " + math + " ");
			sql.append("WHERE ID = '" + id + "'  ");
			int cnt = stmt.executeUpdate(sql.toString());
			System.out.println(">>>처리건수 : " + cnt);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("[예외] DB연결 실패(connection fail)");
			e.printStackTrace();
		} finally {
				try {
					if(stmt != null) stmt.close();
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
