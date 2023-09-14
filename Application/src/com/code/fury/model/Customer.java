package com.code.fury.model;
import java.util.*;
public class Customer {
	 // Attributes
    private int customerId;
    private String name;
    private String gstNumber;
    private String address;
    private String city;
    private String email;
    private String phone;
    private String pinCode;

    // Constructor

	public Customer() {
		super();
	}

	public Customer(int customerId, String name, String gstNumber, String address, String city, String email,
			String phone, String pinCode) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.gstNumber = gstNumber;
		this.address = address;
		this.city = city;
		this.email = email;
		this.phone = phone;
		this.pinCode = pinCode;
	}


    // Getter and Setter methods

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, city, customerId, email, gstNumber, name, phone, pinCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& customerId == other.customerId && Objects.equals(email, other.email)
				&& Objects.equals(gstNumber, other.gstNumber) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone) && Objects.equals(pinCode, other.pinCode);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", gstNumber=" + gstNumber + ", address="
				+ address + ", city=" + city + ", email=" + email + ", phone=" + phone + ", pinCode=" + pinCode
				+ "]";
	}


}
