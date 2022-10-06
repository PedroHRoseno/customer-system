package com.customersystem.poc.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_ADDRESS")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AddressModel implements Serializable {

    private static final long seriaLVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String state;

    @ManyToOne(targetEntity = com.customersystem.poc.models.CustomerModel.class)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    public AddressModel(String streetName, String number, String district, String city, String postalCode, String state, @NotBlank CustomerModel customer) {
        this.streetName = streetName;
        this.number = number;
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressModel that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(streetName, that.streetName) && Objects.equals(number, that.number) && Objects.equals(district, that.district) && Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode) && Objects.equals(state, that.state) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, number, district, city, postalCode, state, customer);
    }
}
