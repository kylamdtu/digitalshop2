package com.dtucdio3.digitalshop2.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtucdio3.digitalshop2.entity.Product;
import com.dtucdio3.digitalshop2.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> listAll(){
		return productRepo.findAll();
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
}
