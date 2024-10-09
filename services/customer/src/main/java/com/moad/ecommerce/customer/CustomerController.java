package com.moad.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService _customerservice;

    @PostMapping()
    public ResponseEntity<String>  createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
            ){

        return ResponseEntity.ok(_customerservice.createCustomer(customerRequest));
    }

    @PutMapping()
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
       _customerservice.updateCustomer( customerRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> findAllCustomer(){

                return ResponseEntity.ok(_customerservice.findAllCustomer());
    }

    @GetMapping("/exits/{customer-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok(_customerservice.existById(customerId));
    }
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findCustomerById(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok(_customerservice.findCustomerById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> deleteCustomerById(
            @PathVariable("customer-id") String customerId
    ){
       _customerservice.deleteCutomerById(customerId);
        return ResponseEntity.accepted().build();
    }


}
