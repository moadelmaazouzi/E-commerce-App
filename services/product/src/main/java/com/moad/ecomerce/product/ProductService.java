package com.moad.ecomerce.product;

import com.moad.ecomerce.exceptions.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final  ProductMapper mapper;
    private  final CategoryRepository categoryRepository;


    public Integer createNewProduct( ProductRequest productRequest){
        var product =mapper.toProduct(productRequest);
        categoryRepository.save( Category.
                builder().id(productRequest.id()).build());
         return productRepository.save(product).getId();
    }

    public ProductResponse findById(Integer id){
        var product= productRepository.findById(id).map(
                mapper::fromProduct
        ).orElseThrow(
                () ->new EntityNotFoundException(String.format(
                        "product with id %s not exist ",id
                ))
        );
        return product;
    }


    public List<ProductResponse> getAllProducts(){

        var products=productRepository.findAll().stream().map(
                mapper::fromProduct
        ).collect(Collectors.toList());
        return products;
    }
    public List<ProductPurchaseResponse>  purchaseProducts(
        List<ProductPurchaseRequest>  requests
    ){
        var  products=requests.stream().map(
                ProductPurchaseRequest::productId
        ).toList();
        var storedProducts=productRepository.findByIdInOrderByIdAsc(
                products
        );
        if(products.size()!=storedProducts.size()){
            throw new ProductPurchaseException("one or more product does not exist");
        }

        var storedRequest=requests.stream().sorted(
                Comparator.comparing(
                        ProductPurchaseRequest::productId
                )
        ).toList();
        var purcaseProducts= new ArrayList<ProductPurchaseResponse>();
        for (int i =0 ; i<storedRequest.size();i++){
            var product=storedProducts.get(i);
            var productRequest=storedRequest.get(i);
            if(product.getAvailablequantity()<=productRequest.quantity()){
                throw new ProductPurchaseException("available quantity not supported");
            }
            product.setAvailablequantity(product.getAvailablequantity()-productRequest.quantity());
            productRepository.save(product);
            purcaseProducts.add(mapper.toProductPurchaseResponse(product,productRequest.quantity()));

        }
        return purcaseProducts;
    }

}
