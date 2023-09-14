package com.code.fury.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.code.fury.dao.CustomerDao;
import com.code.fury.dao.EmployeeDao;
import com.code.fury.dao.OrderDao;
import com.code.fury.dao.ProductDao;
import com.code.fury.exceptions.EntityNotFoundException;
import com.code.fury.exceptions.NoProductsToImportException;
import com.code.fury.impl.CustomerImpl;
import com.code.fury.impl.EmployeeImpl;
import com.code.fury.impl.OrderImpl;
import com.code.fury.impl.ProductImpl;
import com.code.fury.model.Employee;
import com.code.fury.model.Order;
import com.code.fury.model.Product;
import com.code.fury.model.ProductsInsertionStatus;

public class EmployeeService  {

	private EmployeeDao employeeDao;
	private OrderDao orderDao;
	private ProductDao productDao;
	private CustomerDao customerDao;

	public EmployeeService() {

		employeeDao = new EmployeeImpl();
		orderDao = new OrderImpl();
		productDao = new ProductImpl();
		customerDao = new CustomerImpl();

	}
//Authenticating Employee
	public Employee login(int id, String password) throws EntityNotFoundException {

		try {
			return employeeDao.login(id, password);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		throw new EntityNotFoundException("Employee does not exist",LocalDateTime.now(),"ERR107");

	}

	   public List<Order> getOrders() {

		List<Order> orderList = null;

		try {
			orderList = orderDao.getOrders();
			if (orderList.size() != 0)
				return orderList;

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return orderList;


	}

	 public ProductsInsertionStatus importProducts(String productJSON) throws NoProductsToImportException, IOException {

		List<Product> productList = Arrays.asList();
		Set<Product> hSet = new HashSet<Product>(productList);
		List<Product> finaProductsList = new ArrayList<Product>(hSet);

		return productDao.importProducts(finaProductsList);
		

	}

	public List<Product> getProductData() {

		return productDao.getAllProduct();
	}

	public String getCustomer(int id) throws EntityNotFoundException {
		return customerDao.getCustomer(id);
	}

}