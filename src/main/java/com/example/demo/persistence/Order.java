package com.example.demo.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "t_order")
public class Order {

    public enum OrderState {
        WAIT_ON_PAYMENT, PAID, SHIPPED, COMPLETED, CANCELLED
    }

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    private int orderTotal;
    private String orderSummary;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Shipment> shipments = new ArrayList<>();

    private OrderState state;

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
    public void setTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }
    public String getOrderSummary() {
        return orderSummary;
    }
    public void setOrderSummary(String orderSummary) {
        this.orderSummary = orderSummary;
    }
    public List<Shipment> getShipments() {
        return shipments;
    }
    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }
    public OrderState getState() {
        return state;
    }
    public void setState(OrderState state) {
        this.state = state;
    }
}
