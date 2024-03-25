package com.buildingHelper.domain;

public class Msg {
	private int msg_id; /* PK AUTO_INCREMENT */
	
	private String msg_sender_r_login_id; /* VARCHAR(20) FK REFERENCES resident(r_login_id) */
	
	private String msg_sender_house_dong; /* FK 보내는 사람 동 */
	private String msg_sender_house_hosu; /* FK 보내는 사람 호수 */
	
	private String msg_receiver_house_dong; /* FK 받는 사람 동 */
	private String msg_receiver_house_hosu; /* FK 받는 사람 호수 */
	
	private int msg_address_id; /* 같은 아파트 주거민에게만 보낼 수 있게 제한합니다. */
	
	private String msg_title; /* VARCHAR(20) */
	private String msg_content; /* VARCHAR(1000) */
	
	private String msg_sent_time; /* 전송 시간을 저장합니다. */
	private String msg_receiver_read_time; /* 수신 사람이 읽은 시간을 기록합니다. */
	
	public int getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}
	public String getMsg_sender_r_login_id() {
		return msg_sender_r_login_id;
	}
	public void setMsg_sender_r_login_id(String msg_sender_r_login_id) {
		this.msg_sender_r_login_id = msg_sender_r_login_id;
	}
	public String getMsg_sender_house_dong() {
		return msg_sender_house_dong;
	}
	public void setMsg_sender_house_dong(String msg_sender_house_dong) {
		this.msg_sender_house_dong = msg_sender_house_dong;
	}
	public String getMsg_sender_house_hosu() {
		return msg_sender_house_hosu;
	}
	public void setMsg_sender_house_hosu(String msg_sender_house_hosu) {
		this.msg_sender_house_hosu = msg_sender_house_hosu;
	}
	public String getMsg_receiver_house_dong() {
		return msg_receiver_house_dong;
	}
	public void setMsg_receiver_house_dong(String msg_receiver_house_dong) {
		this.msg_receiver_house_dong = msg_receiver_house_dong;
	}
	public String getMsg_receiver_house_hosu() {
		return msg_receiver_house_hosu;
	}
	public void setMsg_receiver_house_hosu(String msg_receiver_house_hosu) {
		this.msg_receiver_house_hosu = msg_receiver_house_hosu;
	}
	public int getMsg_address_id() {
		return msg_address_id;
	}
	public void setMsg_address_id(int msg_address_id) {
		this.msg_address_id = msg_address_id;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	
	
	public String getMsg_receiver_read_time() {
		return msg_receiver_read_time;
	}
	public void setMsg_receiver_read_time(String msg_receiver_read_time) {
		this.msg_receiver_read_time = msg_receiver_read_time;
	}
	public String getMsg_sent_time() {
		return msg_sent_time;
	}
	public void setMsg_sent_time(String msg_sent_time) {
		this.msg_sent_time = msg_sent_time;
	}
	public String getMsg_title() {
		return msg_title;
	}
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}
	
}
