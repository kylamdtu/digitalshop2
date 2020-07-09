package com.dtucdio3.digitalshop2.repository;

import com.dtucdio3.digitalshop2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
