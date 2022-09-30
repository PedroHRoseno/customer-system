package com.customersystem.poc.services;

import com.customersystem.poc.models.CustomerModel;
import com.customersystem.poc.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public CustomerModel save(CustomerModel customerModel) {
        return  customerRepository.save(customerModel);
    }
}
