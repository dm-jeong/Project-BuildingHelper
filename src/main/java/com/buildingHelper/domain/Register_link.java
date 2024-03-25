package com.buildingHelper.domain;

import java.time.LocalDateTime;

/*
 * 2024-02-13-화
 * Register_manager_controller에서 생성하는 가입 URL에 대한 DTO
 */

public class Register_link {
	
	
	private int register_link_id; // PK, auto_increment,
	private int register_address_id; // FK
	
	private String register_url_link; // 가입 링크
	private String expired_date; // 링크 만료 날짜 확인
	private boolean register_expired_check;
	
	private String register_house_name;  // 가입할 아파트의 이름
	private String register_house_dong; // 가입할 아파트의 동
	private String register_house_hosu; // 가입할 아파트의 호수
	
	private String register_category;

	public int getRegister_link_id() {
		return register_link_id;
	}

	public void setRegister_link_id(int register_link_id) {
		this.register_link_id = register_link_id;
	}

	public int getRegister_address_id() {
		return register_address_id;
	}

	public void setRegister_address_id(int register_address_id) {
		this.register_address_id = register_address_id;
	}

	public String getRegister_url_link() {
		return register_url_link;
	}

	public void setRegister_url_link(String register_url_link) {
		this.register_url_link = register_url_link;
	}

	public String getExpired_date() {
		return expired_date;
	}

	public void setExpired_date(String expired_date) {
		this.expired_date = expired_date;
	}

	public boolean isRegister_expired_check() {
		return register_expired_check;
	}

	public void setRegister_expired_check(boolean register_expired_check) {
		this.register_expired_check = register_expired_check;
	}

	public String getRegister_house_name() {
		return register_house_name;
	}

	public void setRegister_house_name(String register_house_name) {
		this.register_house_name = register_house_name;
	}

	public String getRegister_house_dong() {
		return register_house_dong;
	}

	public void setRegister_house_dong(String register_house_dong) {
		this.register_house_dong = register_house_dong;
	}

	public String getRegister_house_hosu() {
		return register_house_hosu;
	}

	public void setRegister_house_hosu(String register_house_hosu) {
		this.register_house_hosu = register_house_hosu;
	}

	public String getRegister_category() {
		return register_category;
	}

	public void setRegister_category(String register_category) {
		this.register_category = register_category;
	}

}
