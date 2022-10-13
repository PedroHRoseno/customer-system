package com.customersystem.poc.repositories;

import com.customersystem.poc.models.AddressModel;
import com.customersystem.poc.models.CustomerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressModel, UUID> {

    public Page<AddressModel> findByCustomer(UUID id, Pageable pageable);

    List<AddressModel> findByCustomer(CustomerModel customer);

    @Query(
            value = "SELECT address FROM AddressModel address WHERE address.customer.id = :id and address.isMainAddress = '1'"
    )
    Optional<AddressModel> findIsMainAddress(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE AddressModel address SET address.isMainAddress = '0' WHERE address.customer.id = :id"
    )
    void unmarkMainAddress(@Param("id") UUID id);
}
