package com.dtucdio3.digitalshop2.repository;
import com.dtucdio3.digitalshop2.entity.Category;
import com.dtucdio3.digitalshop2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
	
	List<Product> findByCategory(Category category);
	
}
