package org.example.order.orderline;

import org.example.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .order(Order.builder()
                        .id(request.orderId())
                        .build())
                .productId(request.productId())
                .quantity(request.quantity())
                .productId(request.productId())
                .build();
    }
}
