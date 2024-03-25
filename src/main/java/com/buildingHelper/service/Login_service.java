package com.buildingHelper.service;

import javax.servlet.http.HttpServletRequest;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.Resident;

public interface Login_service {
	boolean login(Resident resident, HttpServletRequest request);
	String read_category(String r_login_id);
	String read_r_house_id_by_r_login_id(String r_login_id);
	boolean read_house_id_existent(String r_building_id);

}
