package org.ecommerce.notification.kafka.order;

public record Customer(
        String firstName,
        String lastName,
        String email
) {
}
