package org.example.order.payment;

import org.example.order.customer.CustomerResponse;
import org.example.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String customerReference,
        CustomerResponse response
) {
}
