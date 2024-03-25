package com.buildingHelper.repository;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Resident;

public interface Login_repository {
	boolean login(Resident resident);
	String read_category(String r_login_id);
	
	String read_r_house_id_by_r_login_id(String r_login_id);
	boolean read_house_id_existent(String r_building_id);
	
	String read_house_name_by_r_login_id(String r_login_id);

	
	public Address read_address_session(int house_address);

	Resident read_resident_information_when_login(Resident resident_login_id);
	House read_house_information_when_login(Resident resident_house_id);
	Address read_address_information_when_login(House house);
	
}
