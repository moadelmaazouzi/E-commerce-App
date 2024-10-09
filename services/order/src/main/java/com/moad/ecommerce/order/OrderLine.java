package com.moad.ecommerce.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderLines")
@Builder
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;

    private double quantity;
    private Integer productId;
    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order order;
}
