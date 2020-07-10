package com.dtucdio3.digitalshop2.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Product, Integer> products;
    private int totalQuantity;
    private long totalPrice;

    public Cart() {
        this.products = new HashMap<>();
        this.totalPrice = 0;
        this.totalQuantity = 0;
    }

    private long calTotalPrice() {
        totalPrice = 0;
        totalQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
            totalQuantity += entry.getValue();
        }
        return totalPrice;
    }

    public void updateQuantity(Product product, Integer quantity) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (product.getId() == entry.getKey().getId()) {
                products.put(entry.getKey(), quantity);
                return;
            }
        }
        calTotalPrice();
    }

    public void addToCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (product.getId() == entry.getKey().getId()) {
                products.put(entry.getKey(), products.get(entry.getKey()) + 1);
                return;
            }
        }
        products.put(product, 1);
        calTotalPrice();
    }
    public void addToCart(Product product, Integer quantity) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (product.getId() == entry.getKey().getId()) {
                products.put(entry.getKey(), quantity);
                return;
            }
        }
        products.put(product, quantity);
        calTotalPrice();
    }

    public void removeFromCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (product.getId() == entry.getKey().getId()) {
                products.remove(entry.getKey());
                return;
            }
        }
        calTotalPrice();
    }

    public int getTotalQuantity() {
        calTotalPrice();
        return totalQuantity;
    }

    public long getTotalPrice() {
        calTotalPrice();
        return totalPrice;
    }

    public Set<Product> getProducts() {
        return products.keySet();
    }

    public int getProductQuantity(Product product) {
        return products.get(product);
    }
}
