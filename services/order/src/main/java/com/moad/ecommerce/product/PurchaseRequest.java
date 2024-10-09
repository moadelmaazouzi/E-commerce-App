package com.moad.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
      @NotNull Integer productId,
      @NotNull
      @Positive
      double quantity
) {
}
