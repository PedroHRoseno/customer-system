package com.customersystem.poc.services;

import com.customersystem.poc.exceptions.BadRequestException;
import com.customersystem.poc.exceptions.ConflictException;
import com.customersystem.poc.models.CustomerModel;
import com.customersystem.poc.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Optional<CustomerModel> findByIdentifier(String identifier){
        return customerRepository.findByIdentifier(identifier);
    }

    public Page<CustomerModel> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


    public void delete(CustomerModel customerModel) {
        customerRepository.delete(customerModel);
    }

    public boolean existsByIdentifier(String identifier) {
        return customerRepository.existsByIdentifier(identifier);
    }

    public void validateEmail(String email) {
        if(email == null || !email.contains("@")){
            throw new BadRequestException("Invalid email");
        }
        var exists = customerRepository.existsByEmail(email);
        if(exists){
            throw new ConflictException("Email already exists");
        }
    }
}
