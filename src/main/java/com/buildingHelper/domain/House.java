package com.buildingHelper.domain;
/*
 * 상위 ERD: 회원 관리
 * 클래스 이름: House
 * 설명: House(세대)관리를 위한 DTO
 */
public class House {
	private int house_id; // PK
	private int address_id; // FK

	private String house_dong; // 아파트의 101동
	private String house_hosu; // 아파트의 1004호
	
	public int getHouse_id() {
		return house_id;
	}
	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getHouse_dong() {
		return house_dong;
	}
	public void setHouse_dong(String house_dong) {
		this.house_dong = house_dong;
	}
	public String getHouse_hosu() {
		return house_hosu;
	}
	public void setHouse_hosu(String house_hosu) {
		this.house_hosu = house_hosu;
	}
	
}
