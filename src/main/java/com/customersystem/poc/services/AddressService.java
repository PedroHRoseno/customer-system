package com.customersystem.poc.services;

import com.customersystem.poc.exceptions.BadRequestException;
import com.customersystem.poc.exceptions.NotFoundException;
import com.customersystem.poc.models.AddressModel;
import com.customersystem.poc.models.CustomerModel;
import com.customersystem.poc.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public AddressModel save(AddressModel addressModel) {
        return addressRepository.save(addressModel);
    }

    public Page<AddressModel> findAll(UUID id, Pageable pageable) {
        if (!(null == id)){
            return addressRepository.findByCustomer(id, pageable);
        }
        return addressRepository.findAll(pageable);
    }

    public Optional<AddressModel> findById(UUID id) {
        return addressRepository.findById(id);
    }

    @Transactional
    public void delete(AddressModel addressModel) {
        addressRepository.delete(addressModel);
    }

    public AddressModel saveAddress(AddressModel addressModel){
        Optional<CustomerModel> customer = customerService.findById(addressModel.getCustomer().getId());
        validateIfCustomerExists(customer);
        validateMaxAddressToACustomer(customer.get());
        customerService.incrementAddressCount(customer.get());
        return this.save(addressModel);
    }

    public void validateMaxAddressToACustomer(CustomerModel customerModel){
        if (customerModel.getAddressCount() >= 5){
         throw new BadRequestException("limit of registered Addresses reached");
        }
    }

    public void validateIfCustomerExists(Optional<CustomerModel> customer){
        if (customer.isEmpty()){
            throw new NotFoundException("Customer not found");
        }
    }

    public void validateMainAddress(AddressModel addressModel){

    }
}
