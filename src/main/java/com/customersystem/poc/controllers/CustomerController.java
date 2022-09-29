package com.customersystem.poc.controllers;

import com.customersystem.poc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = '*', maxAge = 3600)
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


}
