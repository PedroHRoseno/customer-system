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
import reactor.core.publisher.Mono;

import javax.print.DocFlavor;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PostalCodeExternalApiService postalCodeExternalApiService;

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
        customerService.decrementAddressCount(addressModel.getCustomer());
    }

    public AddressModel validateAndSaveAddress(AddressModel addressModel){
        Optional<CustomerModel> customer = customerService.findById(addressModel.getCustomer().getId());
        validateIfCustomerExists(customer.get());
        validateMaxAddressToACustomer(customer.get());
        addressModel = validateMainAddress(addressModel);
        validatePostalCode(addressModel.getPostalCode());
        customerService.incrementAddressCount(customer.get());
        return this.save(addressModel);
    }

    private void validatePostalCode(String postalCode){
        Mono<String> response = postalCodeExternalApiService.getPostalCode(postalCode);
        String valueFromMono = response.block();
        assert valueFromMono != null;
        if(valueFromMono.contains("erro")){
            throw new BadRequestException("Invalid postal code");
        }
    }

    public void validateMaxAddressToACustomer(CustomerModel customerModel){
        if (customerModel.getAddressCount() >= 5){
         throw new BadRequestException("limit of registered Addresses reached");
        }
    }

    public void validateIfCustomerExists(CustomerModel customer){
        if (customer == null){
            throw new NotFoundException("Customer not found");
        }
    }

    public AddressModel validateMainAddress(AddressModel addressModel){
        var existsAMainAddress = this.findMainAddress(addressModel.getCustomer().getId());
        if (addressModel.isMainAddress()){
            addressRepository.unmarkMainAddress(addressModel.getCustomer().getId());
        } else if (existsAMainAddress == null) {
            addressModel.setMainAddress(true);
        }
        return addressModel;
    }
    public AddressModel findMainAddress(UUID id){
        Optional<AddressModel> findMainAddress = addressRepository.findIsMainAddress(id);
        if (findMainAddress.isEmpty()){
            return null;
        }
        return findMainAddress.get();
    }
}
