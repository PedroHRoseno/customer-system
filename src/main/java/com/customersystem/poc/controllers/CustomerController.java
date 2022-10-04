package com.customersystem.poc.controllers;

import com.customersystem.poc.dtos.CustomerDto;
import com.customersystem.poc.models.CustomerModel;
import com.customersystem.poc.models.enums.PersonType;
import com.customersystem.poc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @GetMapping
    public ResponseEntity<Page<CustomerModel>> getAllCustomers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCustomer(@PathVariable(value = "id") UUID id){
        Optional<CustomerModel> customerModelOptional = customerService.findById(id);
        return customerModelOptional.<ResponseEntity<Object>>map(customerModel -> ResponseEntity.status(HttpStatus.OK).body(customerModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found!!!"));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCustomer(@PathVariable(value = "id") UUID id){
        Optional<CustomerModel> customerModelOptional = customerService.findById(id);
        if(customerModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
        customerService.delete(customerModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer (@PathVariable(value = "id")  UUID id, @RequestBody @Valid CustomerDto customerDto){
        Optional<CustomerModel> customerModelOptional = customerService.findById(id);
        if(customerModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
        CustomerModel customerModel = new CustomerModel(PersonType.valueOf(customerDto.getPersonType()),
                customerDto.getIdentifier(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber());
        customerModel.setId(customerModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customerModel));
    }

}
