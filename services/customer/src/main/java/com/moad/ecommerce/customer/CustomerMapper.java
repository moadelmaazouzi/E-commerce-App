package com.moad.ecommerce.customer;

import org.springframework.stereotype.Service;

@Service

public class CustomerMapper {
    public  Customer toCustomer( CustomerRequest request){
        Customer customer=
                Customer.builder()
                    .id(request.id())
                    .email(request.email())
                    .firstName(request.firstName())
                    .lastName(request.lastName())
                    .adress(
                        request.address()
                ).build();
        return customer;
    };

    public static  CustomerResponse fromCustomer(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAdress()
        );
    }
}
