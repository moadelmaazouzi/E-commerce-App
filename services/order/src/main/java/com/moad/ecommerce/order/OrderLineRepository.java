package com.moad.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {
}
