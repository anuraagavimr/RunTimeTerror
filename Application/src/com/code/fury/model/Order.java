package com.code.fury.model;

import java.util.List;

public class Order {
    private int uniqueOrderId;
    private String orderDate;
    private String customerName;
    private String customerShippingAddress;
    private List<String> products;
    private double totalOrderValue;
    private double shippingCost;
    private String shippingAgency;
    private String status;

    public Order() {
        // Default constructor
    }

    public Order(int uniqueOrderId, String orderDate, String customerName, String customerShippingAddress,
                 List<String> products, double totalOrderValue, double shippingCost, String shippingAgency, String status) {
        this.uniqueOrderId = uniqueOrderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.customerShippingAddress = customerShippingAddress;
        this.products = products;
        this.totalOrderValue = totalOrderValue;
        this.shippingCost = shippingCost;
        this.shippingAgency = shippingAgency;
        this.status = status;
    }

    // Getter and Setter methods for each instance variable

    public int getUniqueOrderId() {
        return uniqueOrderId;
    }

    public void setUniqueOrderId(int uniqueOrderId) {
        this.uniqueOrderId = uniqueOrderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerShippingAddress() {
        return customerShippingAddress;
    }

    public void setCustomerShippingAddress(String customerShippingAddress) {
        this.customerShippingAddress = customerShippingAddress;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public double getTotalOrderValue() {
        return totalOrderValue;
    }

    public void setTotalOrderValue(double totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingAgency() {
        return shippingAgency;
    }

    public void setShippingAgency(String shippingAgency) {
        this.shippingAgency = shippingAgency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "uniqueOrderId=" + uniqueOrderId +
                ", orderDate='" + orderDate + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerShippingAddress='" + customerShippingAddress + '\'' +
                ", products=" + products +
                ", totalOrderValue=" + totalOrderValue +
                ", shippingCost=" + shippingCost +
                ", shippingAgency='" + shippingAgency + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}