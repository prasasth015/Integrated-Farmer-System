package com.cg.farmersystem.service;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.fail;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.farmersystem.controller.PurchaseController;
//import com.cg.farmersystem.model.Product;
import com.cg.farmersystem.model.PurchaseHistory;
import com.cg.farmersystem.repository.PurchaseJpaDao;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PurchaseServiceTest {
	//logger variable
	private static final Logger logger = LogManager.getLogger(PurchaseController.class);
	
	@MockBean
    private PurchaseJpaDao purchaseJpaDao;
	
	@Autowired
    private PurchaseService purchaseService;
	
	//function to get purchase record
	private PurchaseHistory getPurchase() {
		PurchaseHistory purchase=new PurchaseHistory();
		purchase.setPurchaseId(100);
			
		return purchase;
		
	}
	
	//function to test all purchase 
	@Test
	public void testGetAllPurchase() throws Exception {
		PurchaseHistory purchase= new PurchaseHistory();
		purchase.setPurchaseId(200);
	
		PurchaseHistory purchase1 = new PurchaseHistory();
		purchase1.setPurchaseId(100);
		
		List<PurchaseHistory> purchaselist = new ArrayList<>();
		purchaselist.add(purchase);
		purchaselist.add(purchase1);

		Mockito.when(purchaseJpaDao.findAll()).thenReturn(purchaselist);
		assertThat(purchaseService.getAllPurchase()).isEqualTo(purchaselist);

	}

	
	//function to test purchase by ID
	@Test
	public void testGetPurchaseById()
	{
		PurchaseHistory purchase=getPurchase();
		Optional<PurchaseHistory> opProduct=Optional.of(purchase);
		Mockito.when(purchaseJpaDao.findById(purchase.getPurchaseId())).thenReturn(opProduct);
		assertThat(purchaseService.findPurchaseById(100)).isEqualTo(opProduct);
	}
	
	
	//function to test by giving wrong ID
	@Test
	public void testGetPurchaseByIdNegative()
	{
		PurchaseHistory purchase=getPurchase();
		Optional<PurchaseHistory> opProduct=Optional.of(purchase);
		Mockito.when(purchaseJpaDao.findById(purchase.getPurchaseId())).thenReturn(opProduct);
		assertThat(purchaseService.findPurchaseById(10)).isEqualTo(opProduct);
	}
		
	
	//function to test with fail keyword
	@Test
	public void testFindAllPurchase()
	{
		logger.debug("test All Purchase");
		fail("Not yet implemented");	
	}
	
	
	//function to test with fail keyword
	@Test
	public void testFindPurchaseById() {
		logger.debug("test purchase by ID");
		fail("getting Purchase from id is not implemented");
		
	}
	
	
}
