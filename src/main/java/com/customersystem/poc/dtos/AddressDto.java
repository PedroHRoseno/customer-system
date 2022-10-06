package com.customersystem.poc.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class AddressDto {

    @NotBlank
    private String streetName;

    @NotBlank
    private String number;

    @NotBlank
    private String district;

    @NotBlank
    private String city;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String state;

    @NotBlank
    private String customerIdentifier;

    public AddressDto(String streetName, String number, String district, String city, String postalCode, String state, String identifier) {
        this.streetName = streetName;
        this.number = number;
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.customerIdentifier = identifier;
    }


}
