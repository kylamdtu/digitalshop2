package com.dtucdio3.digitalshop2.service;
import com.dtucdio3.digitalshop2.entity.Product;
import com.dtucdio3.digitalshop2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> listAll(){
		return productRepo.findAll();
	}

	public Page<Product> findAll(Pageable pageable) {
		return productRepo.findAll(pageable);
	}
	
	public void save(Product product) {
		productRepo.save(product);
	}
	
	public Product get(int id) {
		return productRepo.getOne(id);
	}
	
	public void delete(int id) {
		productRepo.deleteById(id);
	}
	
	public List<Product> listByCategory(String categoryName){
		return productRepo.findProductByCategoryName(categoryName);
	}
}
