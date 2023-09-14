package com.code.fury.services;
	
	import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.code.fury.model.Customer;
import com.code.fury.model.Invoice;
import com.code.fury.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

	public class InvoiceGenerator {

	      
	    static Connection con;

	    public static void scheduleDailyTask() {
	        
	        System.out.println("Task scheduled to run daily at 12:00 AM.");
	        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	        
	        Runnable dailyInvoiceGenerationTask = () -> {
	           
	            generateInvoicesForToday();
	        };
	        
	        long currentTimeMillis = System.currentTimeMillis();
	        long nextDayMillis = TimeUnit.DAYS.toMillis(1);
	        long initialDelay = nextDayMillis - (currentTimeMillis % nextDayMillis);
	        
	        scheduler.scheduleAtFixedRate(
	                dailyInvoiceGenerationTask,
	                initialDelay,
	                TimeUnit.DAYS.toMillis(1),
	                TimeUnit.MILLISECONDS
	            );
	        
	    }

	    public static void generateInvoicesForApprovedOrders() {
	       
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DATE, -1);
	        Date previousDay = calendar.getTime();

	        
	        List<Order> approvedOrders = retrieveApprovedOrdersFromDatabase(previousDay);

	        // Generating invoices and updating the database
	        for (Order order : approvedOrders) {
	            double orderValue = order.getTotalOrderValue();
	            double gstAmount = orderValue * 0.10; //GST (10% of order value)

	            // Creating an invoice record 
	          
	           // Invoice invoice = new Invoice(order, gstAmount);
	            Customer customer=new Customer();
	            ResultSet resultSet=null;
	    		Invoice invoice = new Invoice();
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

	            // Updating the database with the generated invoice
	            updateDatabaseWithInvoice(invoice);

	            System.out.println("Generated invoice for Order ID: " + order.getOrderId());
	        }
	    }

	   

	   
	    	
	    	    private static Connection connection; //  database connection

	    	    public static void updateDatabaseWithInvoice(Invoice invoice) {
	    	        // SQL query to insert the invoice data into the database
	    	        String sql = "INSERT INTO invoices (invoice_number, customer_name, amount) VALUES (?, ?, ?)";

	    	        try {
	    	            // Create a PreparedStatement to execute the SQL query
	    	            PreparedStatement preparedStatement = connection.prepareStatement(sql);

	    	            // Setting the values for the placeholders in the SQL query
	    	            preparedStatement.setInt(1, invoice.getInvoiceId());
	    	            preparedStatement.setString(2, invoice.getCustomerName());
	    	            preparedStatement.setFloat(3, invoice.getTotalGstAmount());

	    	            // Executing the query to insert the invoice into the database
	    	            preparedStatement.executeUpdate();

	    	            // Close the PreparedStatement
	    	            preparedStatement.close();

	    	            System.out.println("Invoice updated in the database: " + invoice.getInvoiceId());
	    	        } catch (SQLException e) {
	    	            // Handle any database-related exceptions here
	    	            e.printStackTrace();
	    	        }
	    	    }

				public static List<Order> retrieveApprovedOrdersFromDatabase(Date date) {
					return orderList;
				}
	    	

	    }

	    
	



