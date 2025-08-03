package org.example.order.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.order.customer.CustomerClient;
import org.example.order.exception.BusinessException;
import org.example.order.orderline.OrderLineRequest;
import org.example.order.orderline.OrderLineService;
import org.example.order.product.ProductClient;
import org.example.order.product.PurchaseRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(@Valid OrderRequest request) {

        //Checking the customer
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("cannot create order: No customer found with id: " + request.customerId()));

        //purchasing products
        this.productClient.purchaseProducts(request.products());


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

        //sending confirmation email to notification microservice (kafka)

        return null;
    }
}
