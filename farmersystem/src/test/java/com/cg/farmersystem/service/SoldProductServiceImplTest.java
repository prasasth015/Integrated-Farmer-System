package com.cg.farmersystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.farmersystem.model.Product;
import com.cg.farmersystem.model.SoldProduct;
import com.cg.farmersystem.model.SupplierQuote;
import com.cg.farmersystem.repository.SoldProductJpaRepository;

import junit.framework.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest
class SoldProductServiceImplTest {
	
	@Autowired
	private SoldProductServiceImpl soldProductServiceImpl;
	
	@MockBean
	private SoldProductJpaRepository soldProductJpaRepository;
	
	private SoldProduct getSoldProduct() {
		SoldProduct soldproduct=new SoldProduct();
		soldproduct.setInvoiceId(11);
		soldproduct.setUserName("neha08");
		soldproduct.setProductName("wheat");
		soldproduct.setQuantity(20);
		soldproduct.setQuotePrice(244);
		soldproduct.setSupplierQuote(new SupplierQuote());
		return soldproduct;
		
	}
	

	@Test
	 void testGetSoldProductById()
	{
		SoldProduct soldproduct=getSoldProduct();
		Optional<SoldProduct> opSoldProduct=Optional.of(soldproduct);
		Mockito.when(soldProductJpaRepository.findById(soldproduct.getInvoiceId())).thenReturn(opSoldProduct);
		assertThat(soldProductServiceImpl.getSoldProductById(11)).isEqualTo(opSoldProduct);
	

}
	
	@Test
	 void testInsertSoldProduct() {
		 
		SoldProduct soldproduct=getSoldProduct();
		Mockito.when(soldProductJpaRepository.save(soldproduct)).thenReturn(soldproduct);
		assertThat(soldProductServiceImpl.insertSoldProduct(soldproduct));
	}
	
	
	@Test
	 void testDeleteSoldProduct()
	{
		
		SoldProduct soldproduct=getSoldProduct();
		Optional<SoldProduct> opSoldProduct=(soldProductJpaRepository.findById(soldproduct.getInvoiceId()));
		soldProductServiceImpl.deleteSoldProductById(11);
		Assert.assertEquals(true, opSoldProduct.isEmpty());
		
		
	}

}
