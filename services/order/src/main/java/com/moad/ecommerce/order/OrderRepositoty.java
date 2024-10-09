package com.moad.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoty extends JpaRepository<Order,Integer> {
}
