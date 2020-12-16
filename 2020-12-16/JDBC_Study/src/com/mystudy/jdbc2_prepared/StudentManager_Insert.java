package com.mystudy.jdbc2_prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager_Insert {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"mystudy", "mystudypw");
			String id = "2020111";
			String name = "홍길동1";
			int kor = 99;
			int eng = 80;
			int math = 70;
//			StringBuilder sql = new StringBuilder();
//			sql.append("INSERT INTO STUDENT (ID, NAME, KOR, ENG, MATH) ");
//			sql.append("VALUES (?, ?, ? , ? , ?)");
			String sql = ""
					+ "INSERT INTO STUDENT (ID, NAME, KOR, ENG, MATH) "
					+" VALUES (?, ?, ? , ? , ?)";
			System.out.println("sql : " + sql);
			pstmt = conn.prepareStatement(sql.toString());
			// 값 설정 : ? (바인드 변수) 에 값을 설정
//			pstmt.setString(1, id);
//			pstmt.setString(2, name);
//			pstmt.setInt(3, kor);
//			pstmt.setInt(4, eng);
//			pstmt.setInt(5, math);
			int i = 1;
			pstmt.setString(i++, id);
			pstmt.setString(i++, name);
			pstmt.setInt(i++, kor);
			pstmt.setInt(i++, eng);
			pstmt.setInt(i++, math);
			
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "처리된건");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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