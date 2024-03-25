package com.buildingHelper.domain;

import java.sql.Timestamp;

public class Share_item 
{
	private Integer share_item_id; // 물건 ID
	private String name; // 물건 이름
	private String s_category; // 분류
	private String s_description; // 설명
	private String s_condition; // 대여 상태
	private String resident_id; // 요청 세대
	private Timestamp s_start_time; // 요청 날짜
	private String s_return_time; // 반납 일자
	private String s_file_name; // 이미지 파일 이름
	private boolean s_request; // 요청 여부
	private boolean s_approval; // 승인 여부
	private int s_address_id; // 물품의 소속 아파트
	
	public String getS_description() {
		return s_description;
	}

	public void setS_description(String s_description) {
		this.s_description = s_description;
	}

	public int getS_address_id() {
		return s_address_id;
	}

	public void setS_address_id(int s_address_id) {
		this.s_address_id = s_address_id;
	}

	public boolean isS_request() {
		return s_request;
	}

	public void setS_request(boolean s_request) {
		this.s_request = s_request;
	}

	public boolean isS_approval() {
		return s_approval;
	}

	public void setS_approval(boolean s_approval) {
		this.s_approval = s_approval;
	}

	public String getS_file_name() {
		return s_file_name;
	}

	public void setS_file_name(String s_file_name) {
		this.s_file_name = s_file_name;
	}

	public Share_item() {
		super();
	}

	public Share_item(Integer share_item_id, String name) {
		super();
		this.share_item_id = share_item_id;
		this.name = name;
	}

	public Integer getShare_item_id() {
		return share_item_id;
	}

	public void setShare_item_id(Integer share_item_id) {
		this.share_item_id = share_item_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getS_category() {
		return s_category;
	}

	public void setS_category(String s_category) {
		this.s_category = s_category;
	}

	public String getS_condition() {
		return s_condition;
	}

	public void setS_condition(String s_condition) {
		this.s_condition = s_condition;
	}

	public String getResident_id() {
		return resident_id;
	}

	public void setResident_id(String resident_id) {
		this.resident_id = resident_id;
	}

	public Timestamp getS_start_time() {
		return s_start_time;
	}

	public void setS_start_time(Timestamp s_start_time) {
		this.s_start_time = s_start_time;
	}

	public String getS_return_time() {
		return s_return_time;
	}

	public void setS_return_time(String s_return_time) {
		this.s_return_time = s_return_time;
	}

	public void setCurrentTime() { // 생성 시간 설정 함수
		this.s_start_time = new Timestamp(System.currentTimeMillis());
	}

}
