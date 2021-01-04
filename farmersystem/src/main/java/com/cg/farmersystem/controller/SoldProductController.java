package com.cg.farmersystem.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.cg.farmersystem.exception.ResourceNotFoundException;
import com.cg.farmersystem.exception.SoldProductNotFoundException;

import com.cg.farmersystem.model.SoldProduct;
import com.cg.farmersystem.service.SoldProductService;
import com.cg.farmersystem.service.SupplierQuoteService;


@RequestMapping("api/v1")
@RestController
public class SoldProductController {
	private static final Logger logger = LogManager.getLogger(SoldProductController.class);
	

	public SoldProductController() 
	{
		
		logger.info("sold products displayed");
		logger.info("new sold product detail inserted");
		logger.info("sold product detail deleted");
		
	}
	
	@Autowired
	private SoldProductService soldProductService;
	
	@Autowired
	private SupplierQuoteService supplierQuoteService;
	
	//method to get Sold Product invoice by ID
		@GetMapping("/soldproduct/{invoiceId}")
		public ResponseEntity<SoldProduct> getSoldProductById(@PathVariable(value = "invoiceId") Integer invoiceId)
				throws SoldProductNotFoundException {
			logger.info("sold products displayed");
			SoldProduct soldproduct = soldProductService.getSoldProductById(invoiceId)
					.orElseThrow(() -> new SoldProductNotFoundException("No product found with this invoiceId :" + invoiceId));
			return ResponseEntity.ok().body(soldproduct);

		}
		
	//Inserting invoice for sold product
	@PostMapping("/soldproduct/{quoteId}")
	public SoldProduct insertSoldProduct(@PathVariable(value = "quoteId") int quoteId,@Valid @RequestBody SoldProduct soldProduct)throws ResourceNotFoundException
	{
		logger.info("new sold product detail inserted");
		return supplierQuoteService.getQuoteById(quoteId).map(product -> {
			soldProduct.setSupplierQuote(product);
		
			

		
		return soldProductService.insertSoldProduct(soldProduct);
		}).orElseThrow(() -> new ResourceNotFoundException("quoteId:"+ quoteId + " not found"));
		
	}
	
	
	// Method to delete sold product using invoice ID
		@DeleteMapping("/soldproduct/{invoiceId}")
		public String deleteSoldProductById(@PathVariable(value = "invoiceId") int invoiceId) throws SoldProductNotFoundException {
			logger.info("sold product detail deleted");
			soldProductService.getSoldProductById(invoiceId)
					.orElseThrow(() -> new SoldProductNotFoundException("No sell found with this Id :" + invoiceId));
			soldProductService.deleteSoldProductById(invoiceId);
			return "invoice Deleted";
		}
		
		
}
