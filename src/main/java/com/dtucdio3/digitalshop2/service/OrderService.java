package com.dtucdio3.digitalshop2.service;

import com.dtucdio3.digitalshop2.entity.Order;
import com.dtucdio3.digitalshop2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order findById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

}
