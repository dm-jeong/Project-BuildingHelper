package com.buildingHelper.domain;

import java.sql.Timestamp;

public class Club 
{
	private Integer c_id; // 클럽 아이디
    private String c_title; // 클럽 이름
    private String c_description; // 클럽 설명
    private Timestamp c_time; // 클럽 등록시간
    private String c_file_name; // 클럽 사진
    private String c_writer_id; // 클럽 등록자
    private int c_address_id; // 클럽의 소속 아파트
    
    public int getC_address_id() {
		return c_address_id;
	}

	public void setC_address_id(int c_address_id) {
		this.c_address_id = c_address_id;
	}

	public String getC_writer_id() {
		return c_writer_id;
	}

	public void setC_writer_id(String c_writer_id) {
		this.c_writer_id = c_writer_id;
	}

	public Club() {
		super();
	}

	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}

	public String getC_title() {
		return c_title;
	}

	public void setC_title(String c_title) {
		this.c_title = c_title;
	}

	public String getC_description() {
		return c_description;
	}

	public void setC_description(String c_description) {
		this.c_description = c_description;
	}

	public Timestamp getC_time() {
		return c_time;
	}

	public void setC_time(Timestamp c_time) {
		this.c_time = c_time;
	}

	public String getC_file_name() {
		return c_file_name;
	}

	public void setC_file_name(String c_file_name) {
		this.c_file_name = c_file_name;
	}
    
	public void setCurrentTime() { // 작성 시간 설정 함수
		this.c_time = new Timestamp(System.currentTimeMillis());
	}

}
