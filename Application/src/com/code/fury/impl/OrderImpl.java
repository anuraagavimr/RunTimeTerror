package com.code.fury.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.code.fury.dao.OrderDao;
import com.code.fury.exceptions.EntityNotFoundException;
import com.code.fury.model.DetailedQuote;
import com.code.fury.model.Order;
import com.code.fury.model.Product;
import com.code.fury.model.Quote;

public class OrderImpl implements OrderDao{

	private static final String SELECT_ORDERS = "SELECT * FROM orders";
	private static final String INSERT_ORDERS = "INSERT into orders(ORDER_DATE,TOTAL_ORDER_VALUE,SHIPPING_COST,CUSTOMER_ID) VALUES (?,?,?,?)";
	private static final String INSERT_ORDER_LINE = "INSERT into order_line VALUES (?,?,?,?)";
	private static final String GET_ORDER_ID = "SELECT * FROM orders WHERE ORDER_ID = (SELECT MAX(ORDER_ID) FROM orders)";
	private static final String quote = "select ORDER_ID, ORDER_DATE, TOTAL_ORDER_VALUE, SHIPPING_COST from orders where CUSTOMER_ID=? and STATUS='Pending'";
	private static final String quoteDetails = "SELECT orders.ORDER_ID, orders.ORDER_DATE, orders.TOTAL_ORDER_VALUE, orders.SHIPPING_COST, orders.SHIPPING_AGENCY, orders.STATUS, customer.CUSTOMER_ADDRESS_LINE1, customer.CUSTOMER_ADDRESS_CITY, customer.CUSTOMER_ADDRESS_STATE, product.product_name, product.product_price, order_line.quantity from order_line INNER JOIN customer ON order_line.ORDERLINE_CUSTOMER_ID = customer.CUSTOMER_ID inner join orders on orders.order_id = order_line.orderline_order_id inner join product on product.product_id = order_line.orderline_product_id WHERE orders.ORDER_ID=?;";
	private static final String order = "select ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE, STATUS from orders where (STATUS='Approved' or STATUS='Completed') and CUSTOMER_ID=? and current_date() > STATUS_DATE";
	private static final String orderDetails = "Select * from orders where ORDER_ID = ?";
	
	ResultSet resultSet;
	PreparedStatement stmt;
	private Connection connection;

	public OrderImpl() {
		super();
	}

	@Override
	public List<Order> getOrders(){

		stmt = null;
		resultSet = null;
		List<Order> orderList = new ArrayList<Order>();

		try {
			stmt = connection.prepareStatement(SELECT_ORDERS);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				orderList.add(new Order());
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
		return orderList;

	}

	@Override
	public void insertOrders(Quote newOrder) {

		stmt = null;
		try {
			stmt = connection.prepareStatement(INSERT_ORDERS);
			connection.setAutoCommit(false);
			stmt.setDate(1, java.sql.Date.valueOf(newOrder.getOrderDate()));
			stmt.setFloat(2, Float.parseFloat(newOrder.getOrderValue()));
			stmt.setFloat(3, Float.parseFloat(newOrder.getShippingCost()));
			stmt.setString(4, newOrder.getCustomerId());
			int num = stmt.executeUpdate();
			if (num > 0) {
				connection.commit();
				stmt = connection.prepareStatement(GET_ORDER_ID);
				resultSet = stmt.executeQuery();
				String lastOrderId = null;
				if (resultSet.next()) {
					lastOrderId = resultSet.getString("ORDER_ID");
					insertOrderLine(newOrder, lastOrderId);
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void insertOrderLine(Quote newOrder, String orderId) {
		stmt = null;
		try {
			List<Product> orderList = new ArrayList<Product>();
			orderList = newOrder.getProducts();
			stmt = connection.prepareStatement(INSERT_ORDER_LINE);
			connection.setAutoCommit(false);

			for (Product orderItem : orderList) {
				stmt.setString(1, orderId);
				stmt.setString(2, newOrder.getCustomerId());
				stmt.setInt(3, orderItem.getProductId());
				
			}

			int[] inserted = stmt.executeBatch();

			if (inserted.length > 0)
				connection.commit();

		} catch (SQLException e) {
			doRollback(connection);
			try {
				connection.setAutoCommit(true);
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}

			e.printStackTrace();
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

	}

	private void doRollback(Connection c) {
		try {
			c.rollback();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void setQuoteStatus(String orderId) {
		String sql = "update orders set STATUS_DATE=?, STATUS=? where ORDER_ID=?";
		Date date = new Date(2);				
		try {
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(sql);
			stmt.setDate(1, date);			
			stmt.setString(2, "Approved");
			stmt.setString(3, orderId);
			int num = stmt.executeUpdate();
			if (num > 0) {

				stmt = connection.prepareStatement(orderDetails);
				stmt.setString(1, orderId);
				resultSet = stmt.executeQuery();
				Order order = new Order();

				if (resultSet.next()) {
					order.setShippingCost(resultSet.getFloat("SHIPPING_COST"));
					order.setTotalOrderValue(resultSet.getFloat("TOTAL_ORDER_VALUE"));
				}

				double gst = order.getTotalOrderValue() * 0.1f;
				double invoiceValue = order.getShippingCost() + order.getTotalOrderValue() + gst;

				String invoiceCalculation = "insert into invoice (INVOICE_DATE, TOTAL_GST_VALUE, TOTAL_INVOICE_VALUE, INVOICE_STATUS, ORDER_ID) values (current_date(),"
						+ gst + " ," + invoiceValue + ",'Paid','" + orderId + "')";
				stmt = connection.prepareStatement(invoiceCalculation);
				int n = stmt.executeUpdate();
				if (n > 0) {
					connection.commit();
					connection.setAutoCommit(true);
				}
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String displayQuoteDetails(int customerId) {
		Order order = new Order();
		return order.toString();
	}

	
		
	}
