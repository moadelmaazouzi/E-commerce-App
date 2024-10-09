package com.moad.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

        @NotNull(message = "customer id is required")
        @NotBlank(message = "customer id not blank")
        String id ,
        @NotNull(message = "customer firstname is required")
        String firstName ,
        @NotNull(message = "customer firstname is required")
        String lastName ,
        @NotNull(message = "customer firstname is required")
        @Email(message = " customer email is not a valid email")
        String email ,
        Address address
) {}