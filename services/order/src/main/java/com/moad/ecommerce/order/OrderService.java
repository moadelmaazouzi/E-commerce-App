package com.moad.ecommerce.order;

import com.moad.ecommerce.customer.CustomerResponse;
import com.moad.ecommerce.product.PurchaseReponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final WebClient.Builder webClient;
    private final OrderRepositoty orderRepositoty;
    private final OrderLineRepository orderLineRepositoty;


    private final String customerUrl="http://localhost:8091";
    private final String productUrl="http://localhost:8092";
    @Transactional
    public String createOrder(OrderRequest request){

        // check custumer
         Mono<CustomerResponse> customerRes=webClient.baseUrl(customerUrl).build().
                get().uri("/api/v1/customer/{id}",request.customerId())
                .retrieve()
                .bodyToMono(CustomerResponse.class);
        customerRes.block();
        //purchase product
        Mono<List<PurchaseReponse>> productPurcaseRes=webClient.baseUrl(productUrl).build().
                post().uri("/api/v1/product/purchasace").
                bodyValue(request.products()).
                retrieve()

                .bodyToFlux(PurchaseReponse.class).
                collectList();
        productPurcaseRes.block();

        //persist order

        var order=persistOrder(request);

        persistOrderLine(request ,order);



        //purchase product

        //persist order

        //persist orderline



        return "order created "+ order.getId();

    }


    public Order persistOrder(OrderRequest request ){
        return orderRepositoty.save(
                Order.builder().
                        customerId(request.customerId()).
                        paimentMethode(request.paimentMethode()).
                        reference(request.reference()).
                        totalAmount(request.amount()).
                        build()

        );
    }
    public void persistOrderLine(OrderRequest request ,Order order ){
         request.products().stream().forEach(
                 purchaseProduct->{
                     orderLineRepositoty.save(

                             OrderLine.builder().
                                     productId(purchaseProduct.productId()).
                                     quantity(purchaseProduct.quantity()).
                                     order(order).
                                     build()
                     );
                 }
         );
    }

    public List<OrderResponse> getAllOrders(){





        return orderRepositoty.findAll().stream().map(
                order-> OrderResponse.builder().
                        id(order.getId()).
                        createdAt(order.getCreatedAt()).
                        reference(order.getReference()).build()
        ).collect(Collectors.toList());
    }

}
