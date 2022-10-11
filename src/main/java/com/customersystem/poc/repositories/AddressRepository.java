package com.customersystem.poc.repositories;

import com.customersystem.poc.models.AddressModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressModel, UUID> {

    public Page<AddressModel> findByCustomer(UUID id, Pageable pageable);

}
