package com.moad.ecomerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull
        Integer productId,
        @NotNull
        @Positive
        double quantity
) {
}
