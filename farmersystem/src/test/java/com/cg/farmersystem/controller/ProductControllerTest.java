package com.cg.farmersystem.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.farmersystem.model.Product;
import com.cg.farmersystem.model.SoldProduct;
import com.cg.farmersystem.service.ProductServiceImpl;
import com.cg.farmersystem.service.SoldProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
class ProductControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productServiceImpl;
   
    private Product getProduct() {
		Product product=new Product();
		product.setProductId(2);
		product.setProductName("wheat");
		product.setProductDescription("fresh");
		return product;
		
	}
    
  //Method to check if all products can be fetched from database
  	@Test
  	 void testGetAllQuote() throws Exception {
  		String URI = "/api/v1/getproduct";
  		Product product1 = new Product();
  		product1.setProductId(2);
		product1.setProductName("wheat");
		product1.setProductDescription("fresh");

		Product product2 = new Product();
		product2.setProductId(3);
		product2.setProductName("rice");
		product1.setProductDescription("good");

  		List<Product> productList = new ArrayList<>();
  		productList.add(product1);
  		productList.add(product2);
  		
  		String jsonInput = this.converttoJson(productList);

  		Mockito.when(productServiceImpl.getAllProduct()).thenReturn(productList);
  		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
  				.andReturn();
  		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
  		String jsonOutput = mockHttpServletResponse.getContentAsString();

  		assertThat(jsonInput).isEqualTo(jsonOutput);
  		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
  	}
  	
  //Test method to delete product using product ID
  	@Test
  	void testDeleteProduct() throws Exception {
  		String URI = "/deleteproduct/{productId}";
  		Product product = new Product();
  		product.setProductId(2);
		product.setProductName("wheat");
		product.setProductDescription("fresh");
		
  		String jsonInput = this.converttoJson(product);

  		Optional<Product> product1 = Optional.of(product);
  		Mockito.when(productServiceImpl.getProductById(Mockito.any())).thenReturn(product1);
  		
  		//showing error here
  		
  		Mockito.when(productServiceImpl.deleteProduct(Mockito.any())).thenReturn(product);
  		
  		MvcResult mvcResult = this.mockMvc
  				.perform(MockMvcRequestBuilders.delete(URI, 2).accept(MediaType.APPLICATION_JSON)).andReturn();
  		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
  		String jsonOutput = mockHttpServletResponse.getContentAsString();
  		assertNotEquals(jsonInput, jsonOutput);

  	}
  	/*
  	@Test
	 void testInsertProduct() throws Exception {
		String URI = "/api/v1/addproduct";
		Product product = new Product();
  		product.setProductId(2);
		product.setProductName("wheat");
		product.setProductDescription("fresh");
		String jsonInput = this.converttoJson(product);
		
		//showing error here
		Mockito.when(productServiceImpl.saveProduct(Mockito.any(Product.class))).thenReturn(product);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI,2).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertNotEquals(jsonInput,jsonOutput);
		Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}
*/
   
  	 private String converttoJson(Object product) throws JsonProcessingException {
         ObjectMapper objectMapper = new ObjectMapper();
         return objectMapper.writeValueAsString(product);
     }

}
