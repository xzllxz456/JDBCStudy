package com.mystudy.jdbc2_prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager_Update {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"mystudy", "mystudypw");
			String id = "2020111";
			String name = "홍길동444";
			int kor = 91;
			int eng = 10;
			int math = 20;
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE STUDENT ");
			sql.append("SET NAME = ?, KOR = ?, ENG = ?, MATH = ? ");
			sql.append("WHERE ID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			int i = 1;
			pstmt.setString(i++, name);
			pstmt.setInt(i++, kor);
			pstmt.setInt(i++, eng);
			pstmt.setInt(i++, math);
			pstmt.setString(i++, id);

			
			int cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
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