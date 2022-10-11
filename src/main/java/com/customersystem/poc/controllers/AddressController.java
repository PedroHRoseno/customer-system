package com.customersystem.poc.controllers;

import com.customersystem.poc.dtos.AddressDto;
import com.customersystem.poc.models.AddressModel;
import com.customersystem.poc.models.CustomerModel;
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
import javax.websocket.server.PathParam;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Object> saveAddress(@RequestBody @Valid AddressDto addressDto){
        Optional<CustomerModel> customerModel = customerService.findById(UUID.fromString(addressDto.getCustomerIdentifier()));
        if(customerModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        AddressModel addressModel = new AddressModel(
                addressDto.getStreetName(),
                addressDto.getNumber(),
                addressDto.getDistrict(),
                addressDto.getCity(),
                addressDto.getPostalCode(),
                addressDto.getState(),
                customerModel.get(),
                addressDto.isMainAddress()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.saveAddress(addressModel));
    }

    @GetMapping
    public ResponseEntity<Page<AddressModel>> getALlAdresses(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam(required = false) UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findAll(id, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAddress(@PathVariable(value = "id") UUID id){
        Optional<AddressModel> addressModelOptional = addressService.findById(id);
        return addressModelOptional.<ResponseEntity<Object>>map(addressModel -> ResponseEntity.status(HttpStatus.OK).body(addressModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found!!!"));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAddress(@PathVariable(value = "id") UUID id){
        Optional<AddressModel> addressModelOptional = addressService.findById(id);
        if(addressModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adress not found.");
        }
        addressService.delete(addressModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Address deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAdress (@PathVariable(value = "id")  UUID id, @RequestBody @Valid AddressDto addressDto){
        Optional<AddressModel> addressModelOptional = addressService.findById(id);
        if(addressModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found.");
        }
        AddressModel addressModel = new AddressModel();
        addressModel.setId(addressModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(addressService.save(addressModel));
    }

}
