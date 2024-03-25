package com.buildingHelper.domain;

/*
 * 2024-02-06-화
 * 작성자: 정동민
 * 작성 목적: 
 * 거주인(세대원, 동대표, 관리사무소 직원)의 자료를 담기위한 DTO입니다.
 */

public class Resident {
	private int resident_id; // PK

	private int r_house_id; // FK
	
	private String r_login_id; // UNIQUE 세대원 로그인 아이디입니다.
	private String r_login_pw; // 세대원 로그인 비밀번호입니다.
	private String r_nickname; // UNIQUE 세대원 닉네임입니다.
	private String r_phone_number; // 세대원 전화번호입니다.
	private String r_email; // UNIQUE 세대원 이메일입니다.
	private String r_category; // 세대원, 동대표, 관리소 3가지 중 하나를 나태닙니다.
	
	private Address address;
	private House house;
	
	public int getResident_id() {
		return resident_id;
	}
	public void setResident_id(int resident_id) {
		this.resident_id = resident_id;
	}
	
	public int getR_house_id() {
		return r_house_id;
	}
	public void setR_house_id(int r_house_id) {
		this.r_house_id = r_house_id;
	}
	
	public String getR_login_id() {
		return r_login_id;
	}
	public void setR_login_id(String r_login_id) {
		this.r_login_id = r_login_id;
	}
	public String getR_login_pw() {
		return r_login_pw;
	}
	public void setR_login_pw(String r_login_pw) {
		this.r_login_pw = r_login_pw;
	}
	public String getR_nickname() {
		return r_nickname;
	}
	public void setR_nickname(String r_nickname) {
		this.r_nickname = r_nickname;
	}
	public String getR_phone_number() {
		return r_phone_number;
	}
	public void setR_phone_number(String r_phone_number) {
		this.r_phone_number = r_phone_number;
	}
	public String getR_email() {
		return r_email;
	}
	public void setR_email(String r_email) {
		this.r_email = r_email;
	}
	public String getR_category() {
		return r_category;
	}
	public void setR_category(String r_category) {
		this.r_category = r_category;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
}