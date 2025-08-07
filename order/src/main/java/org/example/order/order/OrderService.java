package org.example.order.order;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.order.customer.CustomerClient;
import org.example.order.exception.BusinessException;
import org.example.order.kafka.OrderConfirmation;
import org.example.order.kafka.OrderProducer;
import org.example.order.orderline.OrderLineRequest;
import org.example.order.orderline.OrderLineService;
import org.example.order.payment.PaymentClient;
import org.example.order.payment.PaymentRequest;
import org.example.order.product.ProductClient;
import org.example.order.product.PurchaseRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) {

        //Checking the customer
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("cannot create order: No customer found with id: " + request.customerId()));

        //purchasing products
        var purchasedProducts = this.productClient.purchaseProducts(request.products());


        //persisting order
        var order = this.repository.save(mapper.toOrder(request));

        //persisting order lines
        for (PurchaseRequest purchaseRequest :request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //starting payment process
        var paymentRequest = new PaymentRequest(
            request.amount(),
            request.paymentMethod(),
            order.getId(),
            order.getReference(),
            customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        //sending confirmation email to notification microservice (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }



    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .toList();
    }


    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id: %d not found", orderId)));
    }
}
