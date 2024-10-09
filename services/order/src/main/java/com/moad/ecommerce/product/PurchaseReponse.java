package com.moad.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




public record PurchaseReponse(
        Integer productId ,
        String productName,
        double quantity   ,
        Integer categoryId
) {
}
