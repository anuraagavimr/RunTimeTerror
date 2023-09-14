package com.code.fury.model;

import java.util.Objects;

import com.code.fury.utils.Status;

public class ProductsInsertionStatus {

	private int noOfProductsImported;
	private Status status;

	public ProductsInsertionStatus() {
		
	}
	
	public ProductsInsertionStatus(int noOfProductsImported, String status) {
		super();
		this.noOfProductsImported = noOfProductsImported;
		this.status = Status.valueOf(status);
	}

	public int getNoOfProductsImported() {
		return noOfProductsImported;
	}
	public void setNoOfProductsImported(int noOfProductsImported) {
		this.noOfProductsImported = noOfProductsImported;
	}
	public String getStatus() {
		return status.toString();
	}
	public void setStatus(String status) {
		this.status = Status.valueOf(status);
	}

	@Override
	public String toString() {
		return "ProductsInsertionStatus [noOfProductsImported=" + noOfProductsImported + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(noOfProductsImported, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductsInsertionStatus other = (ProductsInsertionStatus) obj;
		return noOfProductsImported == other.noOfProductsImported && status == other.status;
	}
	

	
	
	
}