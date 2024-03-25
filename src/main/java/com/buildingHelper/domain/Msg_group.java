package com.buildingHelper.domain;

public class Msg_group {
	private int msg_group_id; /* PK */

	private String msg_grouop_sender_r_login_id; /* FK */
	private String msg_group_sender_house_dong; /* FK */
	private String msg_group_sender_house_hosu; /* FK */

	private String msg_group_sender_r_category; /* 관리소, 동대표 */


	/* 
		받는 사람의 동을 의미합니다. 
		관리소 -> NULL을 삽입하여 address_id가 같은 모든 사람에게 전송합니다.
        동대표 -> NULL이 아닌 자신의 house_dong 값을 넣어, 해당 동에 거주하는 세대로 메시지를 보냅니다.
	 */
	private String msg_group_receiver_house_dong; 

	private int msg_group_address_id; /* FK */
	private String msg_group_title; /* VARCHAR(20) */
	private String msg_group_content; /* VARCHAR(1000) */

	private String msg_sent_time; /* DATETIME */

	public int getMsg_group_id() {
		return msg_group_id;
	}

	public void setMsg_group_id(int msg_group_id) {
		this.msg_group_id = msg_group_id;
	}

	public String getMsg_grouop_sender_r_login_id() {
		return msg_grouop_sender_r_login_id;
	}

	public void setMsg_grouop_sender_r_login_id(String msg_grouop_sender_r_login_id) {
		this.msg_grouop_sender_r_login_id = msg_grouop_sender_r_login_id;
	}

	public String getMsg_group_sender_house_dong() {
		return msg_group_sender_house_dong;
	}

	public void setMsg_group_sender_house_dong(String msg_group_sender_house_dong) {
		this.msg_group_sender_house_dong = msg_group_sender_house_dong;
	}

	public String getMsg_group_sender_house_hosu() {
		return msg_group_sender_house_hosu;
	}

	public void setMsg_group_sender_house_hosu(String msg_group_sender_house_hosu) {
		this.msg_group_sender_house_hosu = msg_group_sender_house_hosu;
	}

	public String getMsg_group_sender_r_category() {
		return msg_group_sender_r_category;
	}

	public void setMsg_group_sender_r_category(String msg_group_sender_r_category) {
		this.msg_group_sender_r_category = msg_group_sender_r_category;
	}

	public String getMsg_group_receiver_house_dong() {
		return msg_group_receiver_house_dong;
	}

	public void setMsg_group_receiver_house_dong(String msg_group_receiver_house_dong) {
		this.msg_group_receiver_house_dong = msg_group_receiver_house_dong;
	}

	public int getMsg_group_address_id() {
		return msg_group_address_id;
	}

	public void setMsg_group_address_id(int msg_group_address_id) {
		this.msg_group_address_id = msg_group_address_id;
	}

	public String getMsg_group_title() {
		return msg_group_title;
	}

	public void setMsg_group_title(String msg_group_title) {
		this.msg_group_title = msg_group_title;
	}

	public String getMsg_group_content() {
		return msg_group_content;
	}

	public void setMsg_group_content(String msg_group_content) {
		this.msg_group_content = msg_group_content;
	}

	public String getMsg_sent_time() {
		return msg_sent_time;
	}

	public void setMsg_sent_time(String msg_sent_time) {
		this.msg_sent_time = msg_sent_time;
	}
}
