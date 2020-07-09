package com.dtucdio3.digitalshop2.repository;

import com.dtucdio3.digitalshop2.entity.OrderDetail;
import com.dtucdio3.digitalshop2.entity.OrderDetailIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailIdentity> {
}
