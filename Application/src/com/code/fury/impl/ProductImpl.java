package com.code.fury.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.code.fury.dao.ProductDao;
import com.code.fury.exceptions.NoProductsToImportException;
import com.code.fury.model.Product;
import com.code.fury.model.ProductsInsertionStatus;

public class ProductImpl implements ProductDao {

	private Connection connection;

	private PreparedStatement ps;
	private final static String INSERT_PRODUCT = "Insert into product (PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_CATEGORY) values (?,?,?)";
	private final static String GET_ALL_PRODUCTS = "Select * from product";

	ResultSet rs;
	PreparedStatement stmt;

	public ProductImpl(Connection connection) {
		this.connection = connection;
	}

	public ProductImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Product> getAllProduct() {


		stmt = null;
		rs = null;
		List<Product> productList = new ArrayList<Product>();
		try {
			stmt = connection.prepareStatement(GET_ALL_PRODUCTS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();

				product.setProductId(rs.getInt("PRODUCT_ID"));
				product.setPrice(rs.getFloat("PRODUCT_PRICE"));
				product.setName(rs.getString("PRODUCT_NAME"));
				product.setCategory(rs.getString("PRODUCT_CATEGORY"));
				productList.add(product);
			}


			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public ProductsInsertionStatus importProducts(List<Product> productList) throws NoProductsToImportException {
		// TODO Auto-generated method stub
		stmt = null;
		List<Product> productfromDBList = new ArrayList<Product>();
		List<Product> finalProductList = new ArrayList<Product>();
		HashMap<String, Product> productMap = new HashMap<>();

		try {
			ProductsInsertionStatus productsInsertionStatus = new ProductsInsertionStatus();
			stmt = connection.prepareStatement(GET_ALL_PRODUCTS);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("PRODUCT_ID"));
				product.setPrice(rs.getFloat("PRODUCT_PRICE"));
				product.setName(rs.getString("PRODUCT_NAME"));
				product.setCategory(rs.getString("PRODUCT_CATEGORY"));
				productfromDBList.add(product);
			}
			for (Product product : productfromDBList)
				productMap.put(product.getName(), product);

			for (int i = 0; i < productList.size(); i++) {
				if (productMap.containsKey(productList.get(i).getName()))
					continue;
				else
					finalProductList.add(productList.get(i));

			}

			stmt = connection.prepareStatement(INSERT_PRODUCT);
			connection.setAutoCommit(false);

	

			int[] inserted = stmt.executeBatch();
			connection.commit();
			productsInsertionStatus.setNoOfProductsImported(inserted.length);
			if (inserted.length == 0)
				productsInsertionStatus.setStatus("failed");
			else
				productsInsertionStatus.setStatus("completed");

			return productsInsertionStatus;

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
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		throw new NoProductsToImportException("Products not imported successfully");
	}

	private void doRollback(Connection c) {
		try {
			c.rollback();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}