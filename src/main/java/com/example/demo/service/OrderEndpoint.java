package com.example.demo.service;

import com.example.demo.business.IllegalOrderTotalException;
import com.example.demo.business.MissingDetailsException;
import com.example.demo.business.OrderService;
import com.example.demo.persistence.Customer;
import com.example.demo.persistence.CustomerRepository;
import com.example.demo.persistence.Order;
import com.example.demo.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api")
public class OrderEndpoint {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    /**
     * only testing reasons
     * @return all orders ith and ID smaller than 10
     */
    @RequestMapping(path = "orders", method = RequestMethod.GET)
    public List<Order> getOrders() {
        return orderRepository.getAllByIdIsLessThan((long) 10);
    }

    @RequestMapping(path = "order/{orderId}", method = RequestMethod.GET)
    public OrderElementMessage getOrder(@PathVariable(name = "orderId") Long orderId) {

        try {
            Order c = orderRepository.findById(orderId).get();
            return new OrderElementMessage(c);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
    }

    @RequestMapping(path = "orders/{customerId}", method = RequestMethod.GET)
    public List<OrderElementMessage> getOrders(@PathVariable(name = "customerId") Long customerId) {
        try {
            Customer c = customerRepository.findById(customerId).get();
            // obtain the list of orders for this customer and map them into appropriate
            // messages for sending them to the client
            return c.getOrders().stream().map(OrderElementMessage::new).collect(Collectors.toList());

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

    @RequestMapping(path = "order/{orderId}/actions/repeatOrder", method = RequestMethod.POST)
    public ResponseEntity<OrderElementMessage> repeatOrder(@PathVariable(name = "orderId") Long orderId,
                                                           @RequestParam(name = "timestamp") Long timestamp) {
        try {
            Order orig = orderRepository.findById(orderId).get();
            Customer c = customerRepository.findByOrder(orig.getId());
            Order o = orderService.createNewOrder(c, orig.getOrderSummary(), orig.getOrderTotal(),
                    new Date(timestamp));

            o = orderRepository.save(o);
            c = customerRepository.save(c);

            return ResponseEntity.ok(new OrderElementMessage(o));
        } catch (MissingDetailsException | IllegalOrderTotalException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getClass().getName());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @RequestMapping(path = "order/{orderId}/actions/cancelOrder", method = RequestMethod.POST)
    public ResponseEntity<Void> cancelOrder(@PathVariable(name = "orderId") Long orderId) {
        try {
            Order orig = orderRepository.findById(orderId).get();

            if (orderService.cancelOrder(orig)) {
                orderRepository.save(orig);
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
