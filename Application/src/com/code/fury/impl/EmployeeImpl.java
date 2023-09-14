package com.code.fury.impl;

import java.sql.Connection;
import com.code.fury.exceptions.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.code.fury.dao.EmployeeDao;
import com.code.fury.model.Employee;

public class EmployeeImpl implements EmployeeDao {
	
	private Connection con;
	


	public EmployeeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeImpl(Connection con) {
		super();
		this.con = con;

	}

	public static final String FINDBYID ="select * from employee_table where employeeId=? and password=?";

		// Authenticating credentials
		@Override
		public Employee login(int id, String password) throws EntityNotFoundException {
			PreparedStatement stmt = null;
			ResultSet resultSet = null;
			Employee employee = new Employee();
			try {
				stmt = con.prepareStatement(FINDBYID);
				stmt.setInt(1, id);
				stmt.setString(2, password);
				resultSet = stmt.executeQuery();
				if (resultSet.next()) {
//					
					employee.setEmployeeId(resultSet.getInt(1));
					employee.setUserName(resultSet.getString(2));
					employee.setPassword(resultSet.getString(3));

					return employee;

				} else {
					return null;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (resultSet != null)
						resultSet.close();
					if (stmt != null)
						stmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			throw new EntityNotFoundException("Employee Not found",LocalDateTime.now(),"ERR101");
		}

}
