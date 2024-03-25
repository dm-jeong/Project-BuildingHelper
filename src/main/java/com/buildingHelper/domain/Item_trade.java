package com.buildingHelper.domain;

import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;

public class Item_trade {
	
	private int item_trade_id;
	
	private int address_id;
	
	private String r_login_id;
	
	private String title;
	
	private String category;
    
	private LocalDateTime reg_date;
	
	private String reg_date_time_diff;  // 게시글 등록 차이 main페이지에 사용  - db에는 없는 요소
	
	private int price;
	
	private String description;
	
	private Integer board_count=0;
		
	private MultipartFile img_name;

	private String file_name;
	
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

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getItem_trade_id() {
		return item_trade_id;
	}

	public void setItem_trade_id(int item_trade_id) {
		this.item_trade_id = item_trade_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	

	
	
	
	
	
	
	





	

	
	

	
	
}
