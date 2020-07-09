package com.dtucdio3.digitalshop2.service;

import com.dtucdio3.digitalshop2.entity.OrderDetail;
import com.dtucdio3.digitalshop2.entity.OrderDetailIdentity;
import com.dtucdio3.digitalshop2.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    OrderDetailRepository orderDetailRepository;

    @Autowired
    public void setOrderDetailRepository(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail findById(OrderDetailIdentity orderDetailIdentity) {
        return orderDetailRepository.findById(orderDetailIdentity).orElse(null);
    }

    public void deleteById(OrderDetailIdentity orderDetailIdentity) {
        orderDetailRepository.deleteById(orderDetailIdentity);
    }
}
