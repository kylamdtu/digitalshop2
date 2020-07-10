package com.dtucdio3.digitalshop2.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "OrderDetail")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailIdentity id;
    private int quantity;


    @Column(name = "quantity")
    @Range(message = "Số lượng phải lớn hơn 0.")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetailIdentity getId() {
        return id;
    }

    public void setId(Order order, Product product) {
        this.id = new OrderDetailIdentity(order, product);
    }
}
