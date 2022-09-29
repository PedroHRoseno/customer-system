package com.customersystem.poc.dtos;

import com.customersystem.poc.models.enums.PersonType;
import lombok.Data;
import org.aspectj.weaver.ast.Not;

import javax.persistence.Persistence;
import javax.validation.constraints.NotBlank;

@Data
public class CustomerDto {
    @NotBlank
    private PersonType personType;
    @NotBlank
    private String identifier;
    @NotBlank
    private String email;
    @NotBlank
    private String mainAdress;
    @NotBlank
    private String phoneNumber;

    public CustomerDto(PersonType personType, String identifier, String email, String mainAdress, String phoneNumber) {

        this.personType = personType;
        this.identifier = identifier;
        this.email = email;
        this.mainAdress = mainAdress;
        this.phoneNumber = phoneNumber;
    }
}
