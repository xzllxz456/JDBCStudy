package com.mystudy.jdbc3_vo;

public class StudentDAO_Test {
	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		StudentVO svo = dao.selectId("2020111");
		System.out.println(svo);
		System.out.println("이름 : " + svo.getName());
		System.out.println("평균 : " + svo.getAvg());
		
		
		// 데이터 입력처리
//		StudentVO student = new StudentVO("2020002", "허흥", 10, 20, 30);
//		System.out.println(student);
//		int result = dao.insertStudent(student);
//		System.out.println("처리건수 : " + result);
		//-------------
		// 데이터 수정 처리
		System.out.println("======데이터 수정 ======");
		StudentVO studentUpd = dao.selectId("2020110");
		System.out.println("변경전 : " + studentUpd);
		studentUpd.setKor(95);
		studentUpd.setEng(82);
		studentUpd.setMath(84);
		int updResult = dao.updateData(studentUpd);
		System.out.println("result : " + updResult);
		studentUpd = dao.selectId("2020111");
		System.out.println("변경전후: " + studentUpd);
		
		
		System.out.println("========데이터 삭제 =========");
		int delresult = dao.deleteData("2020110");
		System.out.println("삭제 후" + delresult);
	}
}
