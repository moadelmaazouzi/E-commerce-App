package com.moad.ecommerce.customer;

import com.moad.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository _customerRepository ;
    private final CustomerMapper customerMapper;
     public String createCustomer(CustomerRequest customerRequest){
        Customer customer = _customerRepository.save(customerMapper.toCustomer(customerRequest));
         return customer.getId();
     }
    public void updateCustomer(CustomerRequest request){
         var customer =_customerRepository.findById(request.id()).orElseThrow(
                 () -> new CustomerNotFoundException(
                         String.format("cannot update cutomer ::No customer found with the provided customer ID::%s",request.id())
                 )
         );
         mergerCustomer(customer,request);
         _customerRepository.save(customer);


    }
   public List<CustomerResponse> findAllCustomer(){

         return _customerRepository
                 .findAll()
                 .stream()
                 .map(CustomerMapper::fromCustomer)
                 .collect(Collectors.toList());
   }

    public void mergerCustomer( Customer customer ,CustomerRequest request){
         if(request.address()!=null)
            customer.setAdress(request.address());
         if(StringUtils.isNotBlank(request.email()))
            customer.setEmail(request.email());
         if(StringUtils.isNotBlank(request.firstName()))
            customer.setFirstName(request.firstName());
         if(StringUtils.isNotBlank(request.lastName()))
            customer.setLastName(request.lastName());

    }
    public boolean existById(String id){

         return _customerRepository.existsById(id);
    };

    public CustomerResponse findCustomerById(String id){

        return _customerRepository.findById(id).map(
                CustomerMapper::fromCustomer
        ).orElseThrow(
                ()-> new CustomerNotFoundException(
                        String.format("cannot update cutomer ::No customer found with the provided customer ID::%s",id)
                )
        );
    }
    public void deleteCutomerById( String id){
        return ;
    };
}
