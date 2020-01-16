package com.example.demo.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/order")
public class OrderController {

    @GetMapping
    String getOrderList() {
        return "orderList.html";
    }
}
