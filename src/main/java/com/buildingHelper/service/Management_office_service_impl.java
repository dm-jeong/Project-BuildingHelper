package com.buildingHelper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingHelper.domain.Address;
import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.repository.Management_office_repository;

@Service
public class Management_office_service_impl implements Management_office_service{
	
	@Autowired
	Management_office_repository management_office_repository;
	
	@Override
	public void update_r_category_in_resident_by_office(String r_login_id) {
		management_office_repository.update_r_category_in_resident_by_office(r_login_id);
	}

	@Override
	public void update_r_house_id_into_1_by_office(String r_login_id) {
		management_office_repository.update_r_house_id_into_1_by_office(r_login_id);
	}

	@Override
	public List<House> read_resident_dong_where_resident_lives_by_office(Address address) {
		return management_office_repository.read_resident_dong_where_resident_lives_by_office(address);
	}

	@Override
	public List<House> read_resident_hosu_where_resident_lives_by_office(Address address, String dong) {
		return management_office_repository.read_resident_hosu_where_resident_lives_by_office(address, dong);
	}

	@Override
	public List<Resident> read_resident_detail(Address address, House house) {
		return management_office_repository.read_resident_detail(address, house);
	}
	
	@Override
	public void delete_register_link_by_register_link_id(Register_link register_link) {
		management_office_repository.delete_register_link_by_register_link_id(register_link);
	}


}
