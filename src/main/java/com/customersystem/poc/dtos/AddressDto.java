package com.customersystem.poc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Builder
public class AddressDto {

    public boolean isMainAddress() {
        return isMainAddress;
    }

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
    
    private boolean isMainAddress;

    public AddressDto(String streetName, String number, String district, String city, String postalCode, String state, String identifier, boolean isMainAddress) {
        this.streetName = streetName;
        this.number = number;
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.customerIdentifier = identifier;
        this.isMainAddress = isMainAddress;
    }


}
