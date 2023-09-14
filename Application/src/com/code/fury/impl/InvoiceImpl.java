package com.code.fury.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.code.fury.dao.InvoiceDao;
import com.code.fury.exceptions.EntityNotFoundException;
import com.code.fury.model.Customer;
import com.code.fury.model.Invoice;
import com.code.fury.model.Order;
import com.code.fury.model.ProductDetails;

public class InvoiceImpl implements InvoiceDao {

	private Connection con;
	
	public static final String FINDBYID ="select * from Invoice where orderDeatils=?";
	
	
	public InvoiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public Invoice displayInvoices(int orderId) {
		
		ResultSet resultSet=null;
		Invoice invoice = new Invoice();
		 try(PreparedStatement pstmt = con.prepareStatement(FINDBYID);) {
			pstmt.setInt(1, orderId);

			 resultSet = pstmt.executeQuery();
			if (resultSet.next()) {

				invoice.setInvoiceId(resultSet.getInt("uniqueInvoiceId"));
				invoice.setInvoiceDate(resultSet.getDate("invoiceDate"));
				invoice.setTotalGstAmount(resultSet.getFloat("totalGSTAmount"));
				invoice.setTotalInvoiceValue(resultSet.getFloat("totalInvoiceValue"));
				invoice.setInvoiceStatus(resultSet.getString("status"));
				invoice.setOrders(resultSet.getInt("orderDetailsId"));
				invoice.setCustomer(resultSet.getString("customerDetails"));
				
				return invoice;
				} else
				return null;

		} catch (SQLException ex) {

			ex.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

			throw new EntityNotFoundException(" No order Id ",LocalDateTime.now(),"ERR109");
	}
}
