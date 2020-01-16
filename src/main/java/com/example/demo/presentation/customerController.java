package com.example.demo.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/customer")
public class customerController {

    @GetMapping
    String getCustomerList() {
        return "customerList.html";
    }
}
