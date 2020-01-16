package com.example.demo.service;

import com.example.demo.persistence.Customer;
import com.example.demo.persistence.CustomerRepository;
import com.example.demo.persistence.Order;
import com.example.demo.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class CustomerEndpoint {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping(path = "/customer", produces = "application/json")
    public Customer getCustomerById(@RequestParam(value = "email") String email) {
        return customerRepository.findByEmail(email);
    }

    /*
    @RequestMapping(path = "customer", method = RequestMethod.GET)
    public Customer getCustomerByOrder(@RequestParam(name = "id") Long id) {
        return customerRepository.findByOrder(id);
    }
     */
}
