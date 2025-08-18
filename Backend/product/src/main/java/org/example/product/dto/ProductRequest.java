package org.example.product.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Name cannot be null")
        String name,
        @NotNull(message = "Description cannot be null")
        String description,
        @NotNull(message = "Available quantity must be positive")
        Double availableQuantity,
        @NotNull(message = "Price cannot be null")
        BigDecimal price,
        @NotNull(message = "Category ID cannot be null")
        Integer categoryId
) {
}
