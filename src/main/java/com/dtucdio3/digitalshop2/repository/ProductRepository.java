package com.dtucdio3.digitalshop2.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dtucdio3.digitalshop2.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query(value = "select * from product inner join category on product.catId = category.id where category.name = :categoryName", nativeQuery = true)
	List<Product> findProductByCategoryName(@Param("categoryName") String categoryName);
	
}
