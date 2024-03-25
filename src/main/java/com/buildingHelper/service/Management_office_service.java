package com.buildingHelper.service;

import java.util.List;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;

public interface Management_office_service {
	void update_r_category_in_resident_by_office(String r_login_id);
	void update_r_house_id_into_1_by_office(String r_login_id);
	List<House> read_resident_dong_where_resident_lives_by_office(Address address);
	List<House> read_resident_hosu_where_resident_lives_by_office(Address address, String dong);
	List<Resident> read_resident_detail(Address address, House house);
	void delete_register_link_by_register_link_id(Register_link register_link);
}
