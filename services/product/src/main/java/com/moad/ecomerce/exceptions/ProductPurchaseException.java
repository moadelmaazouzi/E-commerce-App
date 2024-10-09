package com.moad.ecomerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseException  extends RuntimeException{
    private String msg;
}
