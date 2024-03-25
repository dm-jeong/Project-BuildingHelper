package com.buildingHelper.domain;

import java.sql.Timestamp;

public class Club_board 
{
	private Integer cb_id; // 글 id
	private Integer cb_c_id; // 게시판이 속한 클럽 id
	private String cb_title; // 글 제목
	private String cb_description; // 글 내용
	private Timestamp cb_time; // 글 작성 시간
	private String cb_file_name; // 업로도된 파일 이름
	private String cb_writer_id; // 글 작성자 id
	private int cb_count; // 글 조회수
	
	public int getCb_count() {
		return cb_count;
	}

	public void setCb_count(int cb_count) {
		this.cb_count = cb_count;
	}

	public Club_board() {
		super();
	}

	public Integer getCb_id() {
		return cb_id;
	}

	public void setCb_id(Integer cb_id) {
		this.cb_id = cb_id;
	}
	
	public Integer getCb_c_id() {
		return cb_c_id;
	}

	public void setCb_c_id(Integer cb_c_id) {
		this.cb_c_id = cb_c_id;
	}

	public String getCb_title() {
		return cb_title;
	}

	public void setCb_title(String cb_title) {
		this.cb_title = cb_title;
	}

	public String getCb_description() {
		return cb_description;
	}

	public void setCb_description(String cb_description) {
		this.cb_description = cb_description;
	}

	public Timestamp getCb_time() {
		return cb_time;
	}

	public void setCb_time(Timestamp cb_time) {
		this.cb_time = cb_time;
	}

	public String getCb_file_name() {
		return cb_file_name;
	}

	public void setCb_file_name(String cb_file_name) {
		this.cb_file_name = cb_file_name;
	}

	public String getCb_writer_id() {
		return cb_writer_id;
	}

	public void setCb_writer_id(String cb_writer_id) {
		this.cb_writer_id = cb_writer_id;
	}
	
	public void setCurrentTime() { // 작성 시간 설정 함수
		this.cb_time = new Timestamp(System.currentTimeMillis());
	}
}
