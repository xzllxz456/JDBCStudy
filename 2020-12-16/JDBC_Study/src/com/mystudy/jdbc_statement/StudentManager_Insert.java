package com.mystudy.jdbc_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager_Insert {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"mystudy", "mystudypw");
			stmt = conn.createStatement();
			String id = "2020111";
			String name = "홍길동1";
			int kor = 99;
			int eng = 80;
			int math = 70;
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO STUDENT (ID, NAME, KOR, ENG, MATH) ");
			sql.append("VALUES ('" + id + "', '" + name + "', " + kor +", " + eng + ", " 
								+ math + ") ");
			
			stmt.executeUpdate(sql.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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