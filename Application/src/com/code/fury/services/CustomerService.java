package com.code.fury.services;

import java.time.LocalDateTime;

import com.code.fury.dao.CustomerDao;
import com.code.fury.dao.Dao;
import com.code.fury.dao.InvoiceDao;
import com.code.fury.dao.OrderDao;
import com.code.fury.exceptions.EntityNotFoundException;
import com.code.fury.impl.CustomerImpl;
import com.code.fury.impl.InvoiceImpl;
import com.code.fury.impl.OrderImpl;
import com.code.fury.model.Customer;
import com.code.fury.model.DetailedQuote;
import com.code.fury.model.Invoice;
import com.code.fury.model.Product;
import com.code.fury.utils.ProductCategory;

public class CustomerService {
	
	
	static final int RATE = 10;
	
	
	
	
	

	private CustomerDao cdao;
	private OrderDao orderDao;
	private InvoiceDao invoiceDao;

	// default constructor
	public CustomerService() {
		cdao = new CustomerImpl();
		orderDao = new OrderImpl();
		invoiceDao = new InvoiceImpl();
	}
	
	public String getCustomer(int customerId) throws EntityNotFoundException {
		try {
			return cdao.getCustomer(customerId);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		throw new EntityNotFoundException("Customer Not found",LocalDateTime.now(),"ERR102");
	}

	
	public String displayQuote(int customerId) {
		return orderDao.displayQuoteDetails(customerId);
	}

	public void changeQuoteStatus(int orderId) {
		orderDao.setQuoteStatus(orderId);

	}


	public String displayOrder(int customerId) {
		try {
			return orderDao.displayOrderDetails(customerId);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		throw new EntityNotFoundException("Customer Not Exists",LocalDateTime.now(),"ERR106");
	}

	
	public Invoice showInvoice(int orderId) {
		return invoiceDao.displayOrderInvoice(orderId);
	}

	/*
	 * Method to use OrderDaoImpl object and pass specific order id to display
	 * detailed quote method in OrderDaoImpl and return Array List of type Object
	 * containing objects of type Order and Customer.
	 */
	
	public DetailedQuote displayAllQuoteDetails(String orderId) {
		try {
			return orderDao.displayDetailedQuote(orderId);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new EntityNotFoundException("Order Not found",LocalDateTime.now(),"ERR105");
	}
	
public double gstCalculation(double price) {
		
		double gst=0.0;
		
		gst = (price * RATE)/100;
		return gst;
		
	}
	
	public double shippingCostCalculation(ProductCategory level,double orderValue,double price) {
		
		double shippingCost = 0.0;
		
		if(orderValue > 100000) {
			shippingCost = 0.0;
		}else {
			if(level == ProductCategory.LEVEL_1) {
				shippingCost = 0.05*price;
			}else if(level == ProductCategory.LEVEL_2) {
				shippingCost = 0.03*price;
			}else {
				shippingCost = 0.02*price;
			}
		}
		
		return shippingCost;
	}

}
