package com.customersystem.poc.models;

import com.customersystem.poc.models.enums.PersonType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_CUSTOMER")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CustomerModel implements Serializable {
    private static final long seriaLVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private PersonType personType;
    @Column(nullable = false, unique = true, length = 14)
    private String identifier;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;

    public CustomerModel(PersonType personType, String identifier, String email, String phoneNumber) {
        this.personType = personType;
        this.identifier = identifier;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerModel that = (CustomerModel) o;
        return id != null && Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
