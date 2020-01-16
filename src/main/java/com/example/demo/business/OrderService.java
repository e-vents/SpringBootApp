package com.example.demo.business;

import com.example.demo.persistence.Customer;
import com.example.demo.persistence.Order;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    /**
     * Creates a new order for the given customer if business requirements towards
     * the order are fulfilled
     */
    public Order createNewOrder(Customer c, String summary, int total, Date orderDate)
            throws MissingDetailsException, IllegalOrderTotalException {

        // some basic check of parameters
        if (summary == null || summary.length() < 5)
            throw new MissingDetailsException();

        // lets assume that all orders must be greater than 10 CHF and smaller than
        // 10'000 CHF
        if (total < 10)
            throw new IllegalOrderTotalException();

        if (total > 10000)
            throw new IllegalOrderTotalException();

        Order o = new Order();
        o.setOrderDate(orderDate);
        o.setOrderSummary(summary);
        o.setTotal(total);
        c.getOrders().add(o);

        return o;
    }

    /**
     *
     * @param order which cancellation is requested
     * @return if cancellation was successful
     */
    public boolean cancelOrder(Order order) {

       if (order.getState().equals(Order.OrderState.COMPLETED) || !order.getShipments().isEmpty()) {
           return false;
       } else {
           order.setState(Order.OrderState.CANCELLED);
       }
       return true;
    }
}
