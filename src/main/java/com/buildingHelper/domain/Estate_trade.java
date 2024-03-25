package com.buildingHelper.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class Estate_trade {
	
	private int estate_trade_id;
	
	private int address_id;
	
	private String r_login_id;
	
	private String building_type;
	
	private String sales_status;
		
	private String title;
	
	private LocalDateTime reg_date;
	
	private String reg_date_time_diff;  // 몇 일 전, 몇 시간 전 양식 list 에 사용 - db에없는요소
	
	private String description;
	
	private Integer board_count=0;
	
	private MultipartFile img_name;

	private String file_name;
	
	private String address_api; // 매물 주소 
	
	private Date move_in; // 이주가능일
	
	private LocalDateTime update_date;  // 글 수정 일
	
	private String update_date_time_diff;  // update_date를 전처리(몇일전,몇시간전) - db에는없는요소
	
	private String modified_address;    // address_api를 문자열라이브러리로 수정함 - db에는없는요소

	public int getEstate_trade_id() {
		return estate_trade_id;
	}

	public void setEstate_trade_id(int estate_trade_id) {
		this.estate_trade_id = estate_trade_id;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	
	public String getR_login_id() {
		return r_login_id;
	}

	public void setR_login_id(String r_login_id) {
		this.r_login_id = r_login_id;
	}

	public String getBuilding_type() {
		return building_type;
	}

	public void setBuilding_type(String building_type) {
		this.building_type = building_type;
	}

	public String getSales_status() {
		return sales_status;
	}

	public void setSales_status(String sales_status) {
		this.sales_status = sales_status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getReg_date() {
		return reg_date;
	}

	public void setReg_date(LocalDateTime reg_date) {
		this.reg_date = reg_date;
	}
		
	public String getReg_date_time_diff() {
		return reg_date_time_diff;
	}

	public void setReg_date_time_diff(String reg_date_time_diff) {
		this.reg_date_time_diff = reg_date_time_diff;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBoard_count() {
		return board_count;
	}

	public void setBoard_count(Integer board_count) {
		this.board_count = board_count;
	}

	public MultipartFile getImg_name() {
		return img_name;
	}

	public void setImg_name(MultipartFile img_name) {
		this.img_name = img_name;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getAddress_api() {
		return address_api;
	}

	public void setAddress_api(String address_api) {
		this.address_api = address_api;
	}

	public Date getMove_in() {
		return move_in;
	}

	public void setMove_in(Date move_in) {
		this.move_in = move_in;
	}

	public LocalDateTime getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}

	public String getUpdate_date_time_diff() {
		return update_date_time_diff;
	}

	public void setUpdate_date_time_diff(String update_date_time_diff) {
		this.update_date_time_diff = update_date_time_diff;
	}

	public String getModified_address() {
		return modified_address;
	}

	public void setModified_address(String modified_address) {
		this.modified_address = modified_address;
	}

	
	
	
	
}

