package com.moad.ecommerce.order;

import com.moad.ecommerce.customer.CustomerResponse;
import com.moad.ecommerce.product.PurchaseReponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    private final WebClient.Builder WebClientBuilder;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(
            @RequestBody @Valid OrderRequest orderRequest
    ){

        return ResponseEntity.ok(this.orderService.createOrder(orderRequest));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>>getaalorders(){
        return  ResponseEntity.ok(orderService.getAllOrders());
    }






/*
    @GetMapping("/{id}")
    public Mono<String> getClientName(@PathVariable("id") String id) {
        return WebClientBuilder
                .baseUrl("http://localhost:8090")
                .build()
                .get()
                .uri("/api/v1/customer/{id}", id)
                .retrieve()
                .bodyToMono(CustomerResponse.class)
                .map(CustomerResponse::firstName); // Extract the customer's name
    }

*/

}
