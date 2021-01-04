package com.cg.farmersystem.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import com.cg.farmersystem.model.Product;
import com.cg.farmersystem.repository.ProductJpaRepository;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@MockBean
	private ProductJpaRepository productJpaRepository;
	
	private Product getProduct() {
		Product product=new Product();
		product.setProductId(2);
		product.setProductName("wheat");
		product.setProductDescription("fresh");
		return product;
		
	}
	
	@Test
	 void testGetProductById()
	{
		Product product=getProduct();
		Optional<Product> opProduct=Optional.of(product);
		Mockito.when(productJpaRepository.findById(product.getProductId())).thenReturn(opProduct);
		assertThat(productServiceImpl.getProductById(2)).isEqualTo(opProduct);
	}
	
	@Test
	 void testDeleteProduct()
	{
		Product product=new Product();
		product.setProductId(2);
		product.setProductName("wheat");
		product.setProductDescription("fresh");
		
		Optional<Product> com=productJpaRepository.findById(2);
		productJpaRepository.deleteById(2);
		Assert.assertEquals(true, com.isEmpty());
		
	}
	
	@Test
	 void testGetAllProduct() throws Exception {
		Product product1= new Product();
		product1.setProductId(2);
		product1.setProductName("wheat");
		product1.setProductDescription("fresh");
		
		Product product2 = new Product();
		product2.setProductId(3);
		product2.setProductName("rice");
		product2.setProductDescription("good");
		
		List<Product> productlist = new ArrayList<>();
		productlist.add(product1);
		productlist.add(product2);

		Mockito.when(productJpaRepository.findAll()).thenReturn(productlist);
		assertThat(productServiceImpl.getAllProduct()).isEqualTo(productlist);
	}
	
	
	 @Test 
	 void testSaveProduct() {
	 Product product=getProduct();
	 
	 List<Product> productlist = new ArrayList<>();
		productlist.add(product);

		Mockito.when(productJpaRepository.findAll()).thenReturn(productlist);
		assertThat(productServiceImpl.saveProduct(product)).isEqualTo(productlist);

	 }
	 
}
