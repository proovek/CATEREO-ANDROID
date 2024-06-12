package com.ironsoft.catereo.helpers;

public class OrderItemModel {
    private String orderId;
    private String orderDate;
    private String orderDateTime;
    private String orderSummaryPrice;
    private String orderStatus;

    // Konstruktor
    public OrderItemModel(String orderId, String orderDate, String orderDateTime, String orderSummaryPrice, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderDateTime = orderDateTime;
        this.orderSummaryPrice = orderSummaryPrice;
        this.orderStatus = orderStatus;
    }

    // Gettery
    public String getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public String getOrderSummaryPrice() {
        return orderSummaryPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    // Settery jeśli są potrzebne
}
