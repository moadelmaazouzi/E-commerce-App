package com.moad.ecomerce.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    private Integer id;
    private String name;
    private String description ;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.REMOVE)
     private List<Product> products;

}
