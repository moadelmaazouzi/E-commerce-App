package com.moad.ecomerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> saveProduct(
            @RequestBody @Valid ProductRequest request
    ){

        return ResponseEntity.ok(productService.createNewProduct(request));
    }

    @PostMapping("/purchasace")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody @Valid List<ProductPurchaseRequest> requests
    ){

        return  ResponseEntity.ok(productService.purchaseProducts(requests));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
