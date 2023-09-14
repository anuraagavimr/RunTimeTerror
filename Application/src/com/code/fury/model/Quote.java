package com.code.fury.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quote {
	private String orderDate;
	private String customerId;
	private String shippingCost;
	private String orderValue;
	private List<Product> products;
	//default arg constructor
	public Quote() {
		super();
	
	}
	public Quote(String orderDate, String customerId, String shippingCost, String orderValue) {
		super();
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.shippingCost = shippingCost;
		this.orderValue = orderValue;
		this.products=new ArrayList();
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(String shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}



	@Override
	public int hashCode() {
		return Objects.hash(customerId, orderDate, orderValue, shippingCost);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quote other = (Quote) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(orderDate, other.orderDate)
				&& Objects.equals(orderValue, other.orderValue) && Objects.equals(shippingCost, other.shippingCost);
	}
	@Override
	public String toString() {
		return "Quote [orderDate=" + orderDate + ", customerId=" + customerId + ", shippingCost=" + shippingCost
				+ ", orderValue=" + orderValue + "]";
	}
	public List<Product> getProducts() {
		return this.products;
		
	}

}
