package com.code.fury.dao;

import com.code.fury.exceptions.EntityNotFoundException;

public interface CustomerDao {
	
	public String getCustomer(int id) throws EntityNotFoundException;

}
