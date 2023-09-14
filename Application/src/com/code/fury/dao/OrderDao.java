package com.code.fury.dao;

import java.util.List;
import com.code.fury.model.Order;
import com.code.fury.model.Quote;

public interface OrderDao {

	List<Order> getOrders();

	void insertOrders(Quote newOrder);

	void insertOrderLine(Quote newOrder, String lastOrderId);
	
	public void setQuoteStatus(int orderId);

	String displayQuoteDetails(int customerId);



}