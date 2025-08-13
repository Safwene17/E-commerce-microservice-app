package org.example.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import org.example.ecommerce.entites.Address;

public record CustomerRequest(
        Integer id,
        @NotNull(message = "Firstname cannot be null") String firstname,
        @NotNull(message = "Lastname cannot be null") String lastname,
        @NotNull(message = "Email cannot be null") String email,
        Address address
) {}