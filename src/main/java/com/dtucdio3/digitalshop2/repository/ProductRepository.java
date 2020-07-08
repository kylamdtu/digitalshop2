package com.dtucdio3.digitalshop2.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dtucdio3.digitalshop2.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
