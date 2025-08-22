package org.example.order.product;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;

@FeignClient(name = "product", url = "${application.config.product-url}")
public interface ProductClient {

    @PostMapping("/purchase")
    Optional<List<PurchaseResponse>> purchaseProducts(@RequestBody @NotEmpty(message = "you should at least purchase one product") List<PurchaseRequest> request);
}
