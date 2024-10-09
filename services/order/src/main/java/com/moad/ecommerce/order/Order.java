package com.moad.ecommerce.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_orders")
@Builder
public class Order {
    @Id
    @GeneratedValue
    private Integer id;

    private String reference;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private PaimentMethode paimentMethode;


    private String customerId;
    @OneToMany(mappedBy = "order" )
    private List<OrderLine> orderLines;
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

}
