package com.customersystem.poc.controllers;

import com.customersystem.poc.dtos.CustomerDto;
import com.customersystem.poc.models.CustomerModel;
import com.customersystem.poc.models.enums.PersonType;
import com.customersystem.poc.services.AddressService;
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
    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity<Object> saveCustomer(@RequestBody @Valid CustomerDto customerDto){
        customerService.validateToPost(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerService.fillValuesInModel(customerDto, "POST")));
    }
    @GetMapping
    public ResponseEntity<Page<CustomerModel>> getAllCustomers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll(pageable));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getOneCustomer(@PathVariable(value = "id") UUID id){
        Optional<CustomerModel> customerModelOptional = customerService.findById(id);
        return customerModelOptional.<ResponseEntity<Object>>map(customerModel -> ResponseEntity.status(HttpStatus.OK).body(customerModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found!!!"));
    }

    @GetMapping("/identifier/{identifier}")
    public ResponseEntity<Object> getOneCustomerByIdentifier(@PathVariable(value = "identifier") String identifier){
        Optional<CustomerModel> customerModelOptional = customerService.findByIdentifier(identifier );
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

        customerService.validateToUpdate(customerDto);

        return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customerService.fillValuesInModel(customerDto, "PUT")));
    }

}
