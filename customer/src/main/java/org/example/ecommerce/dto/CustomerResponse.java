package org.example.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import org.example.ecommerce.entites.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
