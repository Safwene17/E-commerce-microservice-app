package org.example.ecommerce.entites;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Validated
@Embeddable

public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
