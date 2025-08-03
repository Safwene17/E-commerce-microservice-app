package org.example.order.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.order.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,

        String reference,

        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method cannot be null")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer ID cannot be null")
        @NotEmpty(message = "Customer ID cannot be empty")
        @NotBlank(message = "Customer ID cannot be blank")
        String customerId,

        @NotEmpty(message = "you should at least purchase one product")
        List<PurchaseRequest> products
) {
}
