package com.cg.farmersystem.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


import com.cg.farmersystem.model.Product;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProductJpaRepositoryTest {

	  @Autowired
	    private ProductJpaRepository productJpaRepository;

	    @Autowired
	    private TestEntityManager testEntityManager;
	   
	    
	    private Product getProduct() {
			Product product=new Product();
			product.setProductName("wheat");
			product.setProductDescription("fresh");
			return product;
			
		}
	    
	    @Test
		 void testInsertProduct() throws Exception {
			Product product=getProduct();
			Product saveInDb=testEntityManager.persist(product);
			System.out.println(saveInDb);
			System.out.println("product inserted");
			Product getFromDb=productJpaRepository.findById(saveInDb.getProductId()).get();
			assertThat(getFromDb).isEqualTo(saveInDb);
			
		}
	    
	   
	    @Test
		 void testDeleteProduct()throws Exception
		{

	    	Product product=getProduct();
	    	Product saveInDb=testEntityManager.persist(product);
	    	productJpaRepository.deleteById(product.getProductId());
			List<Product> deleteProduct=productJpaRepository.findAll();
			assertThat(deleteProduct).isEmpty();
			
		}
	    
	    @Test
		void testGetAllProduct()
		{
	    	Product product=getProduct();
	    	Product saveInDb=testEntityManager.persist(product);
			Product count=productJpaRepository.findById(product.getProductId()).get();
			assertThat(saveInDb).isEqualTo(count);
			
			
		}
}
