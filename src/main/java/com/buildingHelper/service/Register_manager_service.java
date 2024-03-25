package com.buildingHelper.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;

public interface Register_manager_service {
	void create_register_link_unlimited(Register_link register_manager_link,int r_house_id);
	List<Register_link> read_register_link_unlimited(Register_link register_link, Address address, HttpServletRequest request);
	List<Register_link> read_register_link_recent_5_links(Address address, HttpServletRequest request);
	
	String read_house_name(String house_id);

	
	int read_check_has_address_id(int address_id);
	int read_house_address_id_by_house_id(int house_id);
	List<Resident> read_resident_information_by_office(Resident resident, Address address);
}
