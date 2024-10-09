package com.moad.ecomerce.product;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        Integer id,
        String name,
        String description ,
        double availablequantity,
        BigDecimal price,
        Integer categoryId,
         String categoryName,
         String categoryDescription
) {
}
