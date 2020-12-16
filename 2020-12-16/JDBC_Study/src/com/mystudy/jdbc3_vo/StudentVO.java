package com.mystudy.jdbc3_vo;

/*
 	자바빈(Java Bean)
 		-멤버변수(property)의 접근제한자는 private으로 외부에서 접근차단
 		-멤버변수(property) 마다 get/set 메소드 제공
 		-getter/setter 메소드는 public 
 		-get 메소드는 파라미터 없고, set메소드는 하나 이상의 파라미터 존재
 		-멤버변수(property)가 boolean 타입이면 get 메소드 대신 is 메소드 사용 가능
 */

// VO(Value Object) : 값(데이터)을 저장하기 위한 클래스, 객체(xxxVO, xxxVo)
// DTO(Data Transfer Object) : 값을 서로 전달하기 위한 목적(xxxDTO, xxxDto)
// VO를 많이 사용
public class StudentVO {
	private String id;		// 필드, 멤버변수, 인스턴스 변수, 속성
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private double avg;
	
	
	public StudentVO() {}

	
	
	public StudentVO(String id, String name, int kor, int eng, int math) {
		super();
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		computeTotAvh();
	}

	public StudentVO(String id, String name, int kor, int eng, int math, int tot, double avg) {
		super();
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.tot = tot;
		this.avg = avg;
		computeTotAvh();
	}



	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
		computeTotAvh();
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
		computeTotAvh();
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
		computeTotAvh();
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	private void computeTot() {
		tot = kor + eng + math;
	}
	
	private void computeAvg() {
		avg = tot * 100 / 3 / 100.0;
	}
	private void computeTotAvh() {
		computeTot();
		computeAvg();
	}
	@Override
	public String toString() {
		return "StudentVo [id=" + id + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", tot="
				+ tot + ", avg=" + avg + "]";
	}

}
