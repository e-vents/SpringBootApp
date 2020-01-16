package com.example.demo;

import com.example.demo.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class OrderManagementApplication {

    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private ShipmentRepository shipmentRepo;

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementApplication.class, args);
    }

    @PostConstruct
    public void initDemoData() {

        Order o = new Order();
        o.setOrderDate(new Date());
        o.setOrderSummary("first");
        o.setTotal(11);
        o.setState(Order.OrderState.WAIT_ON_PAYMENT);

        Order o2 = new Order();
        o2.setOrderDate(new Date());
        o2.setOrderSummary("second");
        o2.setTotal(12);
        o2.setState(Order.OrderState.CANCELLED);

        Order o3 = new Order();
        o3.setOrderDate(new Date());
        o3.setOrderSummary("third");
        o3.setTotal(13);
        o3.setState(Order.OrderState.COMPLETED);

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(o);
        orders.add(o2);
        orders.add(o3);
        orderRepo.saveAll(orders);

        Shipment s = new Shipment();
        s.setProvider("Snail Mail Inc");
        s.setTrackingNumber("snmi4711");
        s.setSentOn(new Date());
        s = shipmentRepo.save(s);
        o.getShipments().add(s);
        o = orderRepo.save(o);

        Customer c = new Customer();
        c.setFirstName("Franz");
        c.setLastName("Huber");
        c.setEmail("franz@huber.ch");
        c.setStreet("Hopfernstrasse 13");
        c.setOrders(orders);
        c = customerRepo.save(c);
    }
}
