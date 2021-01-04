package com.cg.farmersystem.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.farmersystem.model.SoldProduct;
import com.cg.farmersystem.model.SupplierQuote;
import com.cg.farmersystem.service.ProductServiceImpl;
import com.cg.farmersystem.service.SoldProductServiceImpl;
import com.cg.farmersystem.service.SupplierQuoteServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SoldProductController.class)
class SoldProductControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private SoldProductServiceImpl soldProductServiceImpl;
    
    
    @MockBean
    private SupplierQuoteServiceImpl supplierQuoteServiceImpl;
   
    @MockBean
    private ProductServiceImpl productServiceImpl;
    
    private SoldProduct getSoldProduct() {
		SoldProduct soldproduct=new SoldProduct();
		soldproduct.setInvoiceId(12);
		soldproduct.setUserName("neha08");
		soldproduct.setProductName("rice");
		soldproduct.setQuantity(20);
		soldproduct.setQuotePrice(233);
		soldproduct.setSupplierQuote(new SupplierQuote());
		return soldproduct;
		
	}
    
   
    @Test
	 void testGetSoldProductById() throws Exception
	{
		String URI="/api/v1/soldproduct/{invoiceId}";
		SoldProduct soldproduct=getSoldProduct();
		Optional<SoldProduct> opSoldProduct=Optional.of(soldproduct);
		String jsonInput = this.converttoJson(soldproduct);
		Mockito.when(soldProductServiceImpl.getSoldProductById(soldproduct.getInvoiceId())).thenReturn(opSoldProduct);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 12).accept(MediaType.APPLICATION_JSON))
	             .andReturn();
	     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	     String jsonOutput = mockHttpServletResponse.getContentAsString();

	     assertThat(jsonInput).isEqualTo(jsonOutput);
		}
    
   
    
    @Test
	 void testGetSoldProductByIdNegative() throws Exception
	{
		String URI="/api/v1/soldproduct/{invoiceId}";
		SoldProduct soldproduct=getSoldProduct();
		Optional<SoldProduct> opSoldProduct=Optional.of(soldproduct);
		String jsonInput = this.converttoJson(soldproduct);
		Mockito.when(soldProductServiceImpl.getSoldProductById(soldproduct.getInvoiceId())).thenReturn(opSoldProduct);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 12).accept(MediaType.APPLICATION_JSON))
	             .andReturn();
	     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	     String jsonOutput = mockHttpServletResponse.getContentAsString();

	     Assert.assertSame("Enter valid complaint id",jsonInput,jsonOutput);
		}
   
    @Test
	 void testInsertSoldProduct()throws Exception {
		String URI="/api/v1/soldproduct/{quoteId}";
		SoldProduct soldproduct=getSoldProduct();
		String jsonInput = this.converttoJson(soldproduct);
		Mockito.when(soldProductServiceImpl.insertSoldProduct(Mockito.any(SoldProduct.class))).thenReturn(soldproduct);
		MvcResult mvcResult=this.mockMvc.perform(MockMvcRequestBuilders.post(URI,8).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
		          .andReturn();
		 MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        
	       Assert.assertNotEquals(jsonInput,jsonOutput);
	       
	       Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	
	}
    
    @Test
	 void testDeleteSoldProductById() throws Exception {
		String URI = "/api/v1/soldproduct/{invoiceId}";
		SoldProduct soldproduct = getSoldProduct();
		Optional<SoldProduct> opSoldProduct = Optional.of(soldproduct);
		Mockito.when(soldProductServiceImpl.getSoldProductById(Mockito.anyInt())).thenReturn(opSoldProduct);
		soldProductServiceImpl.deleteSoldProductById(soldproduct.getInvoiceId());
		MvcResult mvcResult =this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 12).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}
    private String converttoJson(Object soldproduct) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(soldproduct);
    }

}
