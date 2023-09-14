package com.code.fury.model;

import java.util.List;

public class Company {
	private String companyName;
	private Address companyAddress;
	private String companyCity;
	private long companyGST;
	List<Employee> listOfEmployees;
	
	//constructor without arguments
	public Company() {
		super();
		
	}

	
	//constructor with fields
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Address getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(Address companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public long getCompanyGST() {
		return companyGST;
	}

	public void setCompanyGST(long companyGST) {
		this.companyGST = companyGST;
	}

	public List<Employee> getListOfEmployees() {
		return listOfEmployees;
	}

	public void setListOfEmployees(List<Employee> listOfEmployees) {
		this.listOfEmployees = listOfEmployees;
	}

	
	
	//overriding - hashcode and equals of super class Object
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyAddress == null) ? 0 : companyAddress.hashCode());
		result = prime * result + ((companyCity == null) ? 0 : companyCity.hashCode());
		result = prime * result + (int) (companyGST ^ (companyGST >>> 32));
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((listOfEmployees == null) ? 0 : listOfEmployees.hashCode());
		return result;
	}
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (companyAddress == null) {
			if (other.companyAddress != null)
				return false;
		} else if (!companyAddress.equals(other.companyAddress))
			return false;
		if (companyCity == null) {
			if (other.companyCity != null)
				return false;
		} else if (!companyCity.equals(other.companyCity))
			return false;
		if (companyGST != other.companyGST)
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (listOfEmployees == null) {
			if (other.listOfEmployees != null)
				return false;
		} else if (!listOfEmployees.equals(other.listOfEmployees))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", companyAddress=" + companyAddress + ", companyCity="
				+ companyCity + ", companyGST=" + companyGST + ", listOfEmployees=" + listOfEmployees + "]";
	}
	
	
}