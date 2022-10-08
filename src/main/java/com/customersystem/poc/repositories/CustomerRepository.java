package com.customersystem.poc.repositories;

import com.customersystem.poc.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, UUID> {
    public Optional<CustomerModel> findByIdentifier(String identifier);

    boolean existsByIdentifier(String identifier);

    boolean existsByEmail(String email);
}
