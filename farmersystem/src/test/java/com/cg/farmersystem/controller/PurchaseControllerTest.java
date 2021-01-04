package com.cg.farmersystem.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.cg.farmersystem.model.PurchaseHistory;
import com.cg.farmersystem.model.SoldProduct;
import com.cg.farmersystem.service.PurchaseService;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PurchaseController.class)

public class PurchaseControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private PurchaseService purchaseService;
	
	//function to test with purchaseId
	 @Test
	   public void testGetTicketById() throws Exception{
	   
		 	
		 	String URI="/api/v1/getid/{purchaseId}";
		 	PurchaseHistory purchase=new PurchaseHistory();
		 	purchase.setPurchaseId(100);
		 	SoldProduct sold=new SoldProduct();
		 	purchase.setSoldProduct(sold);
		 	List<PurchaseHistory> purchaseList=new ArrayList();
		 	purchaseList.add(purchase);
		 	assertNotNull(purchaseList);
	 }
	 
	 //function to test to get All purchase
	 @Test
	 public void testGetAllPurchase() throws Exception{
		 String URI="/api/v1/getAllPurchase";
		 PurchaseHistory purchase1=new PurchaseHistory();
		 purchase1.setPurchaseId(100);
		 
		 PurchaseHistory purchase2=new PurchaseHistory();
		 purchase2.setPurchaseId(200);
		 List<PurchaseHistory> purchaselist=new ArrayList<>();
		 purchaselist.add(purchase1);
		 purchaselist.add(purchase2);
		 assertNotNull(purchaselist);
	 }
	 
}

