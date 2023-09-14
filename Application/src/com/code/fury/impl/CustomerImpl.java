package com.code.fury.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.code.fury.dao.CustomerDao;
import com.code.fury.exceptions.EntityNotFoundException;
import com.code.fury.model.Customer;

public class CustomerImpl implements CustomerDao {

private Connection con;
	

	
	public CustomerImpl() {
	super();
}


	public CustomerImpl(Connection con) {
		super();
		this.con = con;
	}
	
	public static final String FINDBYID ="select * from customer_table where customer_id=?";


	@Override
	public String getCustomer(int customerId) throws EntityNotFoundException {
		ResultSet resultSet=null;
		Customer customer = new Customer();
		 try(PreparedStatement pstmt = con.prepareStatement(FINDBYID);) {
			pstmt.setInt(1, customerId);

			 resultSet = pstmt.executeQuery();
			if (resultSet.next()) {

				customer.setCustomerId(resultSet.getInt("id"));
				customer.setName(resultSet.getString("customer_name"));
				customer.setGstNumber(resultSet.getString("gst_number"));
				customer.setAddress(resultSet.getString("address"));
				customer.setCity(resultSet.getString("city"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhone(resultSet.getString("phone"));
				customer.setPinCode(resultSet.getString("pincode"));

				return customer.toString();
				} else
				return new String("");

		} catch (SQLException ex) {

			ex.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

			throw new EntityNotFoundException("Customer Not found",LocalDateTime.now(),"ERR102");
	}

	
	
	

}
