
package com.cg.farmersystem.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farmersystem.exception.ProductNotFoundException;
import com.cg.farmersystem.exception.QuoteNotFoundException;
import com.cg.farmersystem.exception.ResourceNotFoundException;
import com.cg.farmersystem.model.Product;
import com.cg.farmersystem.model.SupplierQuote;
import com.cg.farmersystem.service.ProductService;
import com.cg.farmersystem.service.SupplierQuoteService;

@RestController

@RequestMapping(path = "/api/v1")
public class SupplierQuoteController {

	private static final Logger logger = LogManager.getLogger(SupplierQuoteController.class);

	@Autowired
	private SupplierQuoteService supplierQuoteService;

	@Autowired
	private ProductService productService;

	// method to save supplier quote in database
	@PostMapping("/addQuote/{productId}")
	public SupplierQuote insertQuote(@PathVariable(value = "productId") int productId,
			@Valid @RequestBody SupplierQuote supplierQuote) throws ResourceNotFoundException {
		logger.info("in add supplier quote");
		return productService.getProductById(productId).map(product -> {
			supplierQuote.setProduct(product);

			return supplierQuoteService.saveQuote(supplierQuote);
		}).orElseThrow(() -> new ResourceNotFoundException("Product " + productId + " not found"));

	}

	// Method to fetch the supplier quote from the database
	@GetMapping("/getQuote")
	public List<SupplierQuote> getAllQuote() {
		logger.info("in getAllQuote");
		return supplierQuoteService.getAllQuote();
	}

	// Method to fetch the quote using quoteId
	@GetMapping("getQuoteById/{quoteId}")
	public ResponseEntity<SupplierQuote> getQuoteById(@PathVariable(value = "quoteId") int quoteId)
			throws QuoteNotFoundException {
		logger.info("in getQuoteById");
		SupplierQuote quote = supplierQuoteService.getQuoteById(quoteId)
				.orElseThrow(() -> new QuoteNotFoundException("No Quote found with this Id :" + quoteId));

		return ResponseEntity.ok().body(quote);
	}

	// Method to update the price if quoteId matches with the database
	@PutMapping("/updatePrice/{quoteId}")
	public ResponseEntity<SupplierQuote> updatePrice(@PathVariable(value = "quoteId") int quoteId,
			@RequestBody SupplierQuote quote) throws QuoteNotFoundException {
		logger.info("in updateQuote");
		SupplierQuote sq = supplierQuoteService.getQuoteById(quoteId)
				.orElseThrow(() -> new QuoteNotFoundException("No Quote found with this Id :" + quoteId));
		sq.setQuotePrice(quote.getQuotePrice());
		SupplierQuote updatedPrice = supplierQuoteService.updateQuote(sq);

		return ResponseEntity.ok(updatedPrice);
	}

	// Method to delete quote using quoteId
	@DeleteMapping("/deleteQuote/{quoteId}")
	public String deleteQuote(@PathVariable(value = "quoteId") int quoteId) throws QuoteNotFoundException {
		logger.info("in deleteQuote");
		SupplierQuote quote = supplierQuoteService.getQuoteById(quoteId)
				.orElseThrow(() -> new QuoteNotFoundException("No quote found with this Id :" + quoteId));
		supplierQuoteService.deleteQuote(quote);

		return "Quote Deleted";
	}

	// method to get product by ID
	@GetMapping("/getProducts/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable(value = "productId") Integer productId)
			throws ProductNotFoundException {
		logger.info("in supplier Quote controller");
		Product product = productService.getProductById(productId)
				.orElseThrow(() -> new ProductNotFoundException("No product found with this Id :" + productId));

		return ResponseEntity.ok().body(product);
	}

	// method to get all product
	@GetMapping("/getAllProduct")
	public List<Product> getAllProduct() {
		logger.info("in product controller");
		return productService.getAllProduct();
	}
}
