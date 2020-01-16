package com.example.demo.service;

import com.example.demo.persistence.Order;
import java.util.Date;

public class OrderElementMessage {
    private Long id;
    private Date orderDate;
    private int orderTotal;
    private String orderSummary;
    private boolean hasShipments;
    private String state;

    public OrderElementMessage(Order o) {
        id = o.getId();
        orderDate = o.getOrderDate();
        orderSummary = o.getOrderSummary();
        orderTotal = o.getOrderTotal();
        hasShipments = !o.getShipments().isEmpty();
        if (o.getState() != null)
            state = o.getState().name();
        else
            state = "NEW";
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public int getOrderTotal() {
        return orderTotal;
    }
    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }
    public String getOrderSummary() {
        return orderSummary;
    }
    public void setOrderSummary(String orderSummary) {
        this.orderSummary = orderSummary;
    }
    public boolean isHasShipments() {
        return hasShipments;
    }
    public void setHasShipments(boolean hasShipments) {
        this.hasShipments = hasShipments;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
