package com.buildingHelper.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Msg;
import com.buildingHelper.domain.Msg_group;
import com.buildingHelper.domain.Resident;


public interface Resident_account_service {
	Resident read_resident_information(String r_login_id);
	void update_resident_information(Resident resident, HttpSession session);
	List<Resident> read_list_of_my_house_management(int r_house_id);
	
	void update_move_resident(String r_login_id);
	void update_move_all_resident_by_house_id(House house, Resident resident, HttpSession session);
	
	void update_resident_session(Resident resident, HttpSession session);
	
	boolean create_send_message(Resident resident, Msg msg);
	List<Msg> read_list_of_msg(Address address, House house);
	Msg read_msg_detail(String msg_id, Address address, House house);
	
	boolean create_send_group_message(Resident resident, Msg_group msg_group);
	List<Msg_group> read_list_of_msg_group(Address address, House house);
	Msg_group read_msg_group_detail(String msg_group_id, Address address, House house);
}
