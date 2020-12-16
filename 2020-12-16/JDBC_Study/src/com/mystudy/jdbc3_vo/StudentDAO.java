package com.mystudy.jdbc3_vo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

// DAO(Data Access Object)  데이터 관련 작업 처리용 클래스 (DB연동)-xxxDAO, xxxDao
public class StudentDAO {
	final String DRIVER = "oracle.jdbc.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USER = "mystudy";
	final String PASSWORD = "mystudypw";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public StudentDAO() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// id값을 받아서 DB에서 데이터 조회 후
	// 결과값을 StudentVO에 담아서 리턴하는 메소드 
	public StudentVO selectId(String id) {
		StudentVO student = null;
		//DB연동작업(SELECT)
		try {
			// 2. db연결하고 connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//3.sql실행을 위한 준비
			String sql = ""
					+ "SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG "
					+ "		FROM STUDENT "
					+ "	WHERE ID = ? ";	
			// 3-1 sql을 전달하고 실행준비 요청
			pstmt = conn.prepareStatement(sql);
			// 3-2 sql 문장의 ? 위치에 값 설정
			pstmt.setString(1, id);
		
			// 3-3 sql 문장 실행 요청
			rs = pstmt.executeQuery();
			
			// 4. sql 실행결과 처리 작업
			if(rs.next()) {
				student = new StudentVO();
				// student.setId(rs.getString(1));
				student.setId(rs.getString("ID"));
				student.setName(rs.getString("NAME"));
				student.setKor(rs.getInt("KOR"));
				student.setEng(rs.getInt("ENG"));
				student.setMath(rs.getInt("MATH"));
				student.setTot(rs.getInt("TOT"));
				student.setAvg(rs.getDouble("AVG"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return student;
	}
	// 전달받은 StudentVO 데이터를 DB에 입력
	public int insertStudent(StudentVO student) {
		int result = 0;
		// db연동작업 : vo 데이터 DB에 입력
		try {
			// 2. db연결하고 connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//3.sql실행을 위한 준비
			String sql = ""
						+ "INSERT INTO STUDENT "
						+ "		(ID, NAME, KOR, ENG, MATH, TOT, AVG) "
						+ "	VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, student.getId());
			pstmt.setString(i++, student.getName());
			pstmt.setInt(i++, student.getKor());
			pstmt.setInt(i++, student.getEng());
			pstmt.setInt(i++, student.getMath());
			pstmt.setInt(i++, student.getTot());
			pstmt.setDouble(i++, student.getAvg());
			
			// sql 실행 요청
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}

	// VO를 전달 받아 DB 데이터 수정 (리턴 :  처리건수 리턴)
	public int updateData(StudentVO student) {
		int result = 0;
		// db연동 작업 : VO데이터로 테이블 데이터 수정처리
		try {
			// 2. db연결하고 connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 3. statement 객체 생성해서 실행 요청
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE STUDENT ");
			sql.append("	SET KOR = ? ");
			sql.append("	, ENG = ? ");
			sql.append("	, MATH = ? ");
			sql.append("	, TOT = ? ");
			sql.append("	, AVG = ? ");
			sql.append(" WHERE ID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			int i = 1;
			pstmt.setInt(i++, student.getKor());
			pstmt.setInt(i++, student.getEng());
			pstmt.setInt(i++, student.getMath());
			pstmt.setInt(i++, student.getTot());
			pstmt.setDouble(i++, student.getAvg());
			pstmt.setString(i++, student.getId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}

		return result;
	}
	
	private void close(Connection conn, PreparedStatement pstmt) {
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
	// id를 전달받아 DB데이터를 삭제 (리턴 : 처리건수)
	public int deleteData(String id) {
		int result = 0;
		// db연동 작업 : VO데이터로 테이블 데이터 수정처리
		try {
			// 2. db연결하고 connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 3. statement 객체 생성해서 실행 요청
			String sql = "DELETE FROM STUDENT WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}

		return result;
	}
	
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		close(conn, pstmt);
	}
}
