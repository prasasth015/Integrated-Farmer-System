package com.cg.farmersystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farmersystem.model.Product;
import com.cg.farmersystem.repository.ProductJpaRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductJpaRepository productJpaRepository;

	//Method for fetching all product
	@Override
	public List<Product> getAllProduct() {

		return  productJpaRepository.findAll();
	}

	
	//Method for fetching Product By ID 
	@Override
	public Optional<Product> getProductById(Integer productId) {
	
		return  productJpaRepository.findById(productId);
	}

		//Method for Deleting Product
	@Override
	public Product deleteProduct(Product productId) {
		
		productJpaRepository.delete(productId);
		return productId;
	}
	
	//Method for inserting a new Product
	@Override
	public List<Product> saveProduct(Product product) {

		productJpaRepository.save(product);

		return productJpaRepository.findAll();
	}
	
	
}
