package com.mystudy.jdbc2_prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManager_Select {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			// 2. db연결하고 Connetion 객체 생성

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			// 3. Statement 문 생성 및 실행(SQL문 - 쿼리문실행)
//			conn.createStatement(); 
			String sql = ""  
					+ "SELECT ID, NAME, KOR, ENG, MATH "
					+ "FROM STUDENT "
					+ "WHERE ID = ? "
					+ "ORDER BY ID ASC";
			pstmt = conn.prepareStatement(sql);  // SQL 실행 문장 요정
			pstmt.setString(1, "2020111");		//실행시킬 SQL에 값 매칭
			rs = pstmt.executeQuery();			// 준비된 SQL 실행 요청
			while(rs.next()) {
				String str = "";
				str += rs.getString("ID") + "\t";
				str += rs.getString("NAME") + "\t";
				str += rs.getInt("KOR") + "\t";
				str += rs.getInt("ENG") + "\t";
				str += rs.getInt("MATH") + "\t";

				System.out.println(str);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(pstmt != null)
					pstmt.close();
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
		}
	}
}
