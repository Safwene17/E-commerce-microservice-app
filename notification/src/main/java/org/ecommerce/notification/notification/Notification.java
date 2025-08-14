package org.ecommerce.notification.notification;

import jakarta.persistence.*;
import lombok.*;
import org.ecommerce.notification.kafka.order.OrderConfirmation;
import org.ecommerce.notification.kafka.payment.PaymentConfirmation;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    @Transient
    private OrderConfirmation orderConfirmation;
    @Transient
    private PaymentConfirmation paymentConfirmation;
}
