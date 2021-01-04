package com.cg.farmersystem.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import com.cg.farmersystem.model.SoldProduct;
import com.cg.farmersystem.model.SupplierQuote;

@RunWith(SpringRunner.class)
@DataJpaTest
class SoldProductJpaRepositoryTest {
	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private SoldProductJpaRepository soldProductJpaRepository;
	
	private SoldProduct getSoldProduct() {
		SoldProduct soldproduct=new SoldProduct();
		
		
		soldproduct.setUserName("neha08");
		soldproduct.setProductName("wheat");
		soldproduct.setQuantity(20);
		soldproduct.setQuotePrice(244);
		soldproduct.setSupplierQuote(new SupplierQuote());
		return soldproduct;
		
	}
	
	//test case for GetSoldProductById
	@Test
	 void testGetSoldProductById()
	{
		SoldProduct soldproduct=getSoldProduct();
		SoldProduct saveInDb=testEntityManager.persist(soldproduct);
		SoldProduct count=soldProductJpaRepository.findById(soldproduct.getInvoiceId()).get();
		assertThat(saveInDb).isEqualTo(count);
		
		
	}
	
	//test case for InsertSoldProduct
	@Test
	 void testInsertSoldProduct() throws Exception {
		SoldProduct soldproduct=getSoldProduct();
		SoldProduct saveInDb=testEntityManager.persist(soldproduct);
		System.out.println(saveInDb);
		System.out.println("product inserted");
		SoldProduct getFromDb=soldProductJpaRepository.findById(saveInDb.getInvoiceId()).get();
		assertThat(getFromDb).isEqualTo(saveInDb);
		
	}
	
	//test case for DeleteSoldProductById
	
	@Test
	 void testDeleteSoldProducttById()
	{
		SoldProduct soldproduct=getSoldProduct();
		SoldProduct saveInDb=testEntityManager.persist(soldproduct);
		SoldProduct count=soldProductJpaRepository.findById(soldproduct.getInvoiceId()).get();
		assertThat(saveInDb).isEqualTo(count);
		
		
	}



}
