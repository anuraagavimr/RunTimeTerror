package com.code.fury.dao;

import com.code.fury.model.Employee;
import com.code.fury.exceptions.EntityNotFoundException;


public interface EmployeeDao {
	
	Employee login(int id, String password) throws EntityNotFoundException;


}
