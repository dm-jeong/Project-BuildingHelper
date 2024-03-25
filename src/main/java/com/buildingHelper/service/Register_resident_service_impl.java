package com.buildingHelper.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.buildingHelper.domain.House;
import com.buildingHelper.domain.Register_link;
import com.buildingHelper.domain.Resident;
import com.buildingHelper.repository.Register_resident_repository;

@Service
public class Register_resident_service_impl implements Register_resident_service {
	
	@Autowired
	private Register_resident_repository register_resident_repository;
	
	@Override
	public Register_link read_register_url_link_unlimited(String register_url) {
		return register_resident_repository.read_register_url_link_unlimited(register_url);
	}
	
	
	@Override
	public void create_register_url_link_process(Resident resident, String register_url) {
		register_resident_repository.create_register_url_link_process(resident, register_url);
	}


	@Override
	public House read_and_create_house_if_table_not_exist(Resident resident, String register_url) {
		return register_resident_repository.read_and_create_house_if_table_not_exist(resident, register_url);
	}


	@Override
	public void create_register_resident(Resident resident, House house_check) {
		register_resident_repository.create_register_resident(resident, house_check);
	}
}
