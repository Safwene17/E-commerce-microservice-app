package org.example.order.kafka;

import org.example.order.customer.CustomerResponse;
import org.example.order.order.PaymentMethod;
import org.example.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        Optional<List<PurchaseResponse>> products
){

}
