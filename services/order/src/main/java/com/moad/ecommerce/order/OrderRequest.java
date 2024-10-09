package com.moad.ecommerce.order;

import com.moad.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        @NotNull
        @NotBlank
                @NotEmpty
        String customerId,
        String reference,
        @Positive
        @NotNull(message = "amount must be positive")
        BigDecimal amount,
        @NotNull

        double quatity,
        @NotNull

        PaimentMethode paimentMethode ,
        @NotEmpty
        List<PurchaseRequest> products
) {
}
