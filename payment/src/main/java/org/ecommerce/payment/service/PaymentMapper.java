package org.ecommerce.payment.service;

import org.ecommerce.payment.dto.PaymentRequest;
import org.ecommerce.payment.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
