package com.buildingHelper.domain;

import java.time.LocalDate;

public class Expense_check {
	
	private int expense_id;  // pk
	
	private int house_id;
	
	private String house_name;
	
	private String house_dong;
	
	private String house_hosu;
	
	private int expense_price;  // 합계
	
	private int e_charge; // 전기요금
	
	private int water_charge; // 수도
	
	private int p_charge; // 공용관리비
	
	private String expense_state;
	
	private int expense_year;  //  db에서는 year타입
	
	private int expense_month;  // db에서는 month타입

	private LocalDate expense_paid;
	
	
	
	public int getExpense_id() {
		return expense_id;
	}

	public void setExpense_id(int expense_id) {
		this.expense_id = expense_id;
	}
	

	public int getHouse_id() {
		return house_id;
	}

	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}

	public String getHouse_name() {
		return house_name;
	}

	public void setHouse_name(String house_name) {
		this.house_name = house_name;
	}

	public String getHouse_dong() {
		return house_dong;
	}

	public void setHouse_dong(String house_dong) {
		this.house_dong = house_dong;
	}

	public String getHouse_hosu() {
		return house_hosu;
	}

	public void setHouse_hosu(String house_hosu) {
		this.house_hosu = house_hosu;
	}

	public int getExpense_price() {
		return expense_price;
	}

	public void setExpense_price(int expense_price) {
		this.expense_price = expense_price;
	}
	
	public int getE_charge() {
		return e_charge;
	}

	public void setE_charge(int e_charge) {
		this.e_charge = e_charge;
	}

	public int getWater_charge() {
		return water_charge;
	}

	public void setWater_charge(int water_charge) {
		this.water_charge = water_charge;
	}

	public int getP_charge() {
		return p_charge;
	}

	public void setP_charge(int p_charge) {
		this.p_charge = p_charge;
	}

	public String getExpense_state() {
		return expense_state;
	}

	public void setExpense_state(String expense_state) {
		this.expense_state = expense_state;
	}

	public int getExpense_year() {
		return expense_year;
	}

	public void setExpense_year(int expense_year) {
		this.expense_year = expense_year;
	}

	public int getExpense_month() {
		return expense_month;
	}

	public void setExpense_month(int expense_month) {
		this.expense_month = expense_month;
	}

	public LocalDate getExpense_paid() {
		return expense_paid;
	}

	public void setExpense_paid(LocalDate expense_paid) {
		this.expense_paid = expense_paid;
	}
	
	
}
