package com.moad.ecomerce.product;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
          Integer id,

         @NotNull(message = "product name required")
         String name,
          @NotNull(message = "product description required")
         String description ,
          @NotNull(message = "product avaiblequantity required")
         double availablequantity,
          @NotNull(message = "product price required")
         BigDecimal price,

          @NotNull(message = "product category required")
         Integer categoryId
) {
}
