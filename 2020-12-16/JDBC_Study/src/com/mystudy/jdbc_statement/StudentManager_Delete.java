package com.mystudy.jdbc_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager_Delete {
public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe", "mystudy", "mystudypw");
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder();
			//String name = "홍길동111";
			//sql.append("DELETE FROM STUDENT WHERE NAME = '" + name + "' ");
			String id = "2020111";
			sql.append("DELETE FROM STUDENT WHERE ID = '" + id + "' ");
			
			int cnt = stmt.executeUpdate(sql.toString());
			System.out.println("삭제 수: " + cnt);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] : 드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("[예외] : DB연결 실패");
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