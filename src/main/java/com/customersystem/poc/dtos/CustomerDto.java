package com.customersystem.poc.dtos;

import com.customersystem.poc.models.enums.PersonType;
import lombok.Data;
import org.aspectj.weaver.ast.Not;

import javax.persistence.Persistence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CustomerDto {
    @NotBlank
    private String personType;
    @NotBlank
    private String identifier;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;

    public CustomerDto(String personType, String identifier, String email, String phoneNumber) {

        this.personType = personType;
        this.identifier = identifier;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
