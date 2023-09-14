package com.code.fury;

import java.util.Date;
import java.util.List;

import com.code.fury.model.DetailedQuote;
import com.code.fury.model.Employee;
import com.code.fury.model.Invoice;
import com.code.fury.model.Order;
import com.code.fury.model.Product;
import com.code.fury.model.ProductsInsertionStatus;
import com.code.fury.services.CustomerService;
import com.code.fury.services.EmployeeService;
import com.code.fury.services.InvoiceGenerator;
import com.code.fury.utils.ProductCategory;

public class Main {

	private static final Date Date = null;

	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		
		// This method displays the quote details based on customer id.
		String quote = customerService.displayQuote(101);
		System.out.println("Displaying the Quotes Generated"+quote);
		
		System.out.println("Updated Quote Details"+ customerService.changeQuoteStatus(101));
		
		
		String order = customerService.displayOrder(101);
		System.out.println("Order Details"+order);
		
		Invoice invoice = customerService.showInvoice(101);
		System.out.println("Invoices Generated"+invoice);
		
		DetailedQuote quotes = customerService.displayAllQuoteDetails("101");
		System.out.println("All the Quotes"+quotes);
		//Calculating GST
		double rate = customerService.gstCalculation(500);
		System.out.println("Rate after gst calculation - " + rate);
		//Shipping Charges
		double shippingCost = customerService.shippingCostCalculation(LEVEL1,700,300);
		System.out.println("Shipping - " + shippingCost);
		
		//Daily updating the invoice 
		employeeService.scheduleDailyTask();
		
		List<Order> approvedOrdersList = InvoiceGenerator.retrieveApprovedOrdersFromDatabase(Date previousDay);// add date here
		for(Order eachOrder : approvedOrderList) {
			System.out.println(eachOrder);
		}
		
		System.out.println(invoiceGenerator.updateDatabaseWithInvoice(invoice));
		
		
		EmployeeService service = new EmployeeService();
		
		Employee detail = service.login(101, "abc@123");
		System.out.println(detail.toString());
		
		List<Order> ordersList = service.getOrders();
		
		for(Order eachOrder : ordersList) {
			System.out.println(eachOrder);
		}
		
		ProductsInsertionStatus products = service.importProducts("LEVEL1");
		System.out.println(products);
		//Products Data
		List<Product> productData =  service.getProductData();
		
		for(Product eachProduct : productData) {
			System.out.println(eachProduct);
		}
		System.out.println("Products"+productData);
		//Customer Details
		String customer = service.getCustomer(101);
		System.out.println("Customer Details"+customer);
		
		// Schedule the task to run every day at 12:00 AM
		System.out.println("Generating invoices for today...");
		invoiceGenerator.scheduleDailyTask();
		
		

		
	}
	
}
