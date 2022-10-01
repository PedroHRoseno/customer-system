package com.customersystem.poc.controllers;

import com.customersystem.poc.dtos.CustomerDto;
import com.customersystem.poc.models.CustomerModel;
import com.customersystem.poc.models.enums.PersonType;
import com.customersystem.poc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Object> saveCustomer(@RequestBody @Valid CustomerDto customerDto){
        CustomerModel customerModel = new CustomerModel(PersonType.valueOf(customerDto.getPersonType()),
                customerDto.getIdentifier(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerModel));
    }
}
