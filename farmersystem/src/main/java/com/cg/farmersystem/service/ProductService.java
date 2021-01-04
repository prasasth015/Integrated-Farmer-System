package com.cg.farmersystem.service;

import java.util.List;
import java.util.Optional;


import com.cg.farmersystem.model.Product;



public interface ProductService {
List<Product> getAllProduct();
	
	public Product deleteProduct(Product product);
	
	public List<Product> saveProduct(Product product);

	
	
	
		
	public Optional<Product> getProductById(Integer productId);

}
