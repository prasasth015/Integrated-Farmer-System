package com.cg.farmersystem.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farmersystem.exception.PurchaseNotFoundException;
import com.cg.farmersystem.model.PurchaseHistory;

import com.cg.farmersystem.service.PurchaseService;



@RestController
@RequestMapping("/api/v1")
public class PurchaseController {
	
	//local variable for logger
	private static final Logger logger = LogManager.getLogger(PurchaseController.class);
	//constructor
	public PurchaseController() 
	{
		logger.info("in controller....");
		logger.info("Purchase History details displayed");
		logger.info("Purchase history details with particular purchase will displayed");
		
	}
	
	@Autowired
	private PurchaseService purchaseService;
	
	//function to show all purchase
	@GetMapping("/getAllPurchase")
    public List<PurchaseHistory> getAllPurchase(){
		logger.error("Error Message: error in displaying purchase details....");			
        return (List<PurchaseHistory>) purchaseService.getAllPurchase();      
    }
	
	//function to show purchase by purchaseId
	@GetMapping("/getid/{purchaseId}")
	public ResponseEntity<PurchaseHistory> findPurchaseById(@PathVariable(value = "purchaseId") int purchaseId)
			throws PurchaseNotFoundException {
		PurchaseHistory purchase = purchaseService.findPurchaseById(purchaseId)
			.orElseThrow(() -> new PurchaseNotFoundException("No purchase found with this Id :" + purchaseId));
		return ResponseEntity.ok().body(purchase);		
	}

}


