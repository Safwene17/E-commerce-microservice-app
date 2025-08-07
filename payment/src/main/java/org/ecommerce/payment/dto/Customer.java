package org.ecommerce.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

//must use @Validated in order to use a complex object like customer in PaymentRequest
@Validated
public record Customer(
        String id,
        @NotNull(message = "Firstname cannot be null")
        String firstname,
        @NotNull(message = "Lastname cannot be null")
        String lastname,
        @NotNull
        @Email(message = "Email should be valid")
        String email
) {
}
