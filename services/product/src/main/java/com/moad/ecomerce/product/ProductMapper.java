package com.moad.ecomerce.product;

import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public  Product toProduct(ProductRequest request){
        return Product.builder().
                id(request.id()).
                price(request.price()).
                availablequantity(request.availablequantity()).
                description(request.description()).
                category(
                        Category.builder().id(request.id()).build()
                ).
                 build();
    }
    public ProductResponse fromProduct(Product product){

        return  ProductResponse.builder().
                id(product.getId()).
                name(product.getName()).
                description(product.getDescription()).
                price(product.getPrice()).
                availablequantity(product.getAvailablequantity()).
                categoryId(product.getCategory().getId()).
                categoryName(product.getCategory().getName()).
                categoryDescription(product.getCategory().getDescription()).build();

    }
    public ProductPurchaseResponse toProductPurchaseResponse(Product product ,double quantity){
        return  ProductPurchaseResponse.
                builder().
                productId(product.getId()).
                productName(product.getName()).
                quantity(quantity).
                categoryId(product.getCategory().getId()).
                build();
    }
}
