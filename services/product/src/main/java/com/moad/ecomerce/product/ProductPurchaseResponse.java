package com.moad.ecomerce.product;

import lombok.Builder;

@Builder
public record ProductPurchaseResponse(
        Integer productId ,
        String productName,
        double quantity   ,
        Integer categoryId
) {
}
