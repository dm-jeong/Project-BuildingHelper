package com.buildingHelper.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;


public interface Register_resident_service {
	Register_link read_register_url_link_unlimited(String register_url);
		
	void create_register_url_link_process(Resident resident, String register_url);
	
	House read_and_create_house_if_table_not_exist(Resident resident, String register_url);
	void create_register_resident(Resident resident, House house_check);
}
