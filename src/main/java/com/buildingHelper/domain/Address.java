package com.buildingHelper.domain;
/*
 * 2024-02-06-화
 * 작성자: 정동민
 * 작성 목적: 주소에 대한 정보를 담는 객체입니다.
 */

public class Address {

	private int address_id; // PK
	
	private String address; /* VARCHAR(200) */
	
	private String house_name; /* 행복 빌라, 창원아파트 등 */

	
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getHouse_name() {
		return house_name;
	}
	public void setHouse_name(String house_name) {
		this.house_name = house_name;
	}


	
}
