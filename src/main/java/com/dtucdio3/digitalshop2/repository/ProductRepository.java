package com.dtucdio3.digitalshop2.repository;
import com.dtucdio3.digitalshop2.entity.Category;
import com.dtucdio3.digitalshop2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findByCategory(Category category);
	
}
