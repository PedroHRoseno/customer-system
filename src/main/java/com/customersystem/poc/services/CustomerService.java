package com.customersystem.poc.services;

import com.customersystem.poc.dtos.CustomerDto;
import com.customersystem.poc.exceptions.BadRequestException;
import com.customersystem.poc.exceptions.ConflictException;
import com.customersystem.poc.models.CustomerModel;
import com.customersystem.poc.models.enums.PersonType;
import com.customersystem.poc.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Optional<CustomerModel> findByIdentifier(String identifier){ return customerRepository.findByIdentifier(identifier); }

    public Page<CustomerModel> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public void delete(CustomerModel customerModel) {
        customerRepository.delete(customerModel);
    }

    public CustomerModel fillValuesInModel(CustomerDto customerDto, String method){
        CustomerModel customerModel = new CustomerModel(PersonType.valueOf(customerDto.getPersonType()),
                customerDto.getIdentifier(),
                customerDto.getName(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber());
        if (method.equals("PUT")){
            Optional<CustomerModel> customerModelOptional = this.findByIdentifier(customerDto.getIdentifier());
            customerModel.setId(customerModelOptional.get().getId());
        }
        return customerModel;
    }

    public void validateToPost(CustomerDto customerDto){
        this.validateIdentifier(customerDto);
        this.validateEmail(customerDto.getEmail());
        this.validateIfExists(customerDto);
    }

    public void validateToUpdate(CustomerDto customerDto){
        validateIdentifier(customerDto);
        validateEmail(customerDto.getEmail());
    }

    public void validateIdentifier(CustomerDto customerDto) {
        if (customerDto.getPersonType().equals("LEGAL_PERSON") && customerDto.getIdentifier().length() != 14){
            throw new BadRequestException("Legal person identifier must have 14 digits.");
        } else if (customerDto.getPersonType().equals("PHYSICAL_PERSON") && customerDto.getIdentifier().length() != 11) {
            throw new BadRequestException("Physical person identifier must have 11 digits.");
        }
    }

    public void validateEmail(String email) {
        if(email == null || !email.contains("@")){
            throw new BadRequestException("Invalid email");
        }
    }

    public void validateIfExists(CustomerDto customerDto){
        Optional<CustomerModel> customerModelOptional = customerRepository.findByIdentifier(customerDto.getIdentifier());
        var existsEmail = customerRepository.existsByEmail(customerDto.getEmail());
        if(existsEmail){
            throw new ConflictException("Email already exists");
        }
        var existsIdentifier = customerRepository.existsByIdentifier(customerDto.getIdentifier());
        if (existsIdentifier){
            throw new ConflictException("Identifier already exists");
        }
    }

    public void incrementAddressCount(CustomerModel customerModel){
        customerModel.setAddressCount(customerModel.getAddressCount() + 1);
        System.out.println(customerModel.getAddressCount());
        this.save(customerModel);
    }

    public void decrementAddressCount(CustomerModel customerModel) {
        customerModel.setAddressCount(customerModel.getAddressCount() - 1);
        this.save(customerModel);
    }
}
