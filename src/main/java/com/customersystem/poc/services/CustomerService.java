package com.customersystem.poc.services;

import com.customersystem.poc.models.CustomerModel;
import com.customersystem.poc.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public CustomerModel save(CustomerModel customerModel) {
        return  customerRepository.save(customerModel);
    }

    public Optional<CustomerModel> findById(UUID id) {
        return customerRepository.findById(id);
    }

    public List<CustomerModel> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public void delete(CustomerModel customerModel) {
        customerRepository.delete(customerModel);
    }
}
