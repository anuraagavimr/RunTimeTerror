package com.code.fury.dao;

import java.util.List;

import com.code.fury.exceptions.NoProductsToImportException;
import com.code.fury.model.Product;
import com.code.fury.model.ProductsInsertionStatus;

public interface ProductDao {

	List<Product> getAllProduct();

	ProductsInsertionStatus importProducts(List<Product> productList) throws NoProductsToImportException;

}