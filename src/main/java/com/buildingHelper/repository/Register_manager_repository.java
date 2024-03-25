package com.buildingHelper.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;

public interface Register_manager_repository {
	void create_register_link_unlimited(Register_link register_manager_link);

	List<Register_link> read_register_link_unlimited(Register_link register_link, Address address, HttpServletRequest request);
	List<Register_link> read_register_link_recent_5_links(Address address, HttpServletRequest request);
	Resident read_resident_information(HttpServletRequest request);
	String read_house_name(String house_id);
	
	int read_check_has_address_id(int address_id);
	int read_house_address_id_by_house_id(int house_id);
	List<Resident> read_resident_information_by_office(Resident resident, Address address);
	
}
