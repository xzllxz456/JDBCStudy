package com.mystudy.jdbc2_prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentManager_Delete {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			String id = "2020111";
			StringBuilder aa = new StringBuilder();
			aa.append("DELETE FROM STUDENT WHERE ID = ?");
			pstmt = conn.prepareStatement(aa.toString());
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "처리된건 ");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(conn !=null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
	}

}
