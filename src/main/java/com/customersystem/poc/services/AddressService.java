package com.customersystem.poc.services;

import com.customersystem.poc.models.AddressModel;
import com.customersystem.poc.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public AddressModel save(AddressModel addressModel) {
        return addressRepository.save(addressModel);
    }

    public Page<AddressModel> findAll(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public Optional<AddressModel> findById(UUID id) {
        return addressRepository.findById(id);
    }

    @Transactional
    public void delete(AddressModel addressModel) {
        addressRepository.delete(addressModel);
    }
}
