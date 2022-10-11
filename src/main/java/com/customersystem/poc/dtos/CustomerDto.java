package com.customersystem.poc.dtos;

import com.customersystem.poc.models.AddressModel;
import com.customersystem.poc.models.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;

import javax.persistence.Persistence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    private AddressDto addressDto;

}
