package com.customersystem.poc.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "identifier", nullable = false)
    private String customerIdentifier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressModel that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(streetName, that.streetName) && Objects.equals(number, that.number) && Objects.equals(district, that.district) && Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode) && Objects.equals(state, that.state) && Objects.equals(customerIdentifier, that.customerIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, number, district, city, postalCode, state, customerIdentifier);
    }
}
