package com.moad.ecommerce.order;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record OrderResponse(
        Integer id,

        String reference,
        BigDecimal totalAmount,
        String customerId,
         LocalDateTime createdAt
) {
}
