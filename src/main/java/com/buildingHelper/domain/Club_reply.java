package com.buildingHelper.domain;

import java.sql.Timestamp;

public class Club_reply 
{
	private Integer cr_id; // 댓글 고유 id
	private Integer cr_cb_id; // 댓글이 소속된 게시글 id
	private String cr_description; // 댓글 내용
	private String cr_writer_id; // 댓글 작성자 id
	private Timestamp cr_time; // 댓글 작성 시간
	
	public Club_reply() {
		super();
	}

	public Integer getCr_id() {
		return cr_id;
	}

	public void setCr_id(Integer cr_id) {
		this.cr_id = cr_id;
	}

	public Integer getCr_cb_id() {
		return cr_cb_id;
	}

	public void setCr_cb_id(Integer cr_cb_id) {
		this.cr_cb_id = cr_cb_id;
	}

	public String getCr_description() {
		return cr_description;
	}

	public void setCr_description(String cr_description) {
		this.cr_description = cr_description;
	}

	public String getCr_writer_id() {
		return cr_writer_id;
	}

	public void setCr_writer_id(String cr_writer_id) {
		this.cr_writer_id = cr_writer_id;
	}

	public Timestamp getCr_time() {
		return cr_time;
	}

	public void setCr_time(Timestamp cr_time) {
		this.cr_time = cr_time;
	}
	
	public void setCurrentTime() { // 작성 시간 설정 함수
		this.cr_time = new Timestamp(System.currentTimeMillis());
	}
}
