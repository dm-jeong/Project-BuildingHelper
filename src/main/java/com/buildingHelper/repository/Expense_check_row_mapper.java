package com.buildingHelper.repository;

import org.springframework.jdbc.core.RowMapper;
import com.buildingHelper.domain.Expense_check;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Expense_check_row_mapper implements RowMapper<Expense_check> {

    @Override
    public Expense_check mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Expense_check expense_check = new Expense_check();   	
    	
    	expense_check.setHouse_id(rs.getInt("house_id"));
    	expense_check.setHouse_dong(rs.getString("house_dong"));
    	expense_check.setHouse_hosu(rs.getString("house_hosu"));
    	expense_check.setExpense_price(rs.getInt("expense_price"));
        expense_check.setExpense_state(rs.getString("expense_state"));
        expense_check.setE_charge(rs.getInt("e_charge"));
        expense_check.setWater_charge(rs.getInt("water_charge"));
        expense_check.setP_charge(rs.getInt("p_charge"));
        
        expense_check.setExpense_year(rs.getInt("expense_year"));
        expense_check.setExpense_month(rs.getInt("expense_month"));
        
        Date expense_paid = rs.getDate("expense_paid");
        if (expense_paid != null) {
        	expense_check.setExpense_paid(expense_paid.toLocalDate());
        }
        
        return expense_check;
    }
}
