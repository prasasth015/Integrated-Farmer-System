package com.cg.farmersystem.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.farmersystem.model.SupplierQuote;
import com.cg.farmersystem.service.ProductService;
import com.cg.farmersystem.service.SupplierQuoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SupplierQuoteController.class)
class SupplierQuoteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@MockBean
	private SupplierQuoteService supplierQuoteService;

	// Test method to check if the quote is inserted in database with productId
	@Test
	void testInsertQuote() throws Exception {
		String URI = "/api/v1/addQuote/{productId}";
		SupplierQuote quote = new SupplierQuote();
		quote.setQuoteId(3);
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);
		String jsonInput = this.converttoJson(quote);

		Mockito.when(supplierQuoteService.saveQuote(Mockito.any(SupplierQuote.class))).thenReturn(quote);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI, 1)
				.accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertNotEquals(jsonInput, jsonOutput);
		Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	void testNegativeInsertQuote() throws Exception {
		String URI = "/api/v1/addQuote/{productId}";
		SupplierQuote quote = new SupplierQuote();
		quote.setQuoteId(3);
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);
		String jsonInput = this.converttoJson(quote);

		Mockito.when(supplierQuoteService.saveQuote(Mockito.any(SupplierQuote.class))).thenReturn(quote);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI, 1)
				.accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertEquals(jsonInput, jsonOutput);
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	// Test method to check if the quote can be fetched through quoteId
	@Test
	void testGetQuoteById() throws Exception {
		String URI = "/api/v1/getQuoteById/{quoteId}";
		SupplierQuote quote = new SupplierQuote();
		quote.setQuoteId(1);
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);
		String jsonInput = this.converttoJson(quote);
		Optional<SupplierQuote> quote1 = Optional.of(quote);

		Mockito.when(supplierQuoteService.getQuoteById(1)).thenReturn(quote1);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, 1).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);

	}

	// Negative Test method to check if the quote can be fetched through wrong
	// quoteId
	@Test
	void testNegativeGetQuoteById() throws Exception {
		String URI = "/api/v1/getQuoteById/{quoteId}";
		SupplierQuote quote = new SupplierQuote();
		quote.setQuoteId(1);
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);
		String jsonInput = this.converttoJson(quote);
		Optional<SupplierQuote> quote1 = Optional.of(quote);

		Mockito.when(supplierQuoteService.getQuoteById(1)).thenReturn(quote1);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, 2).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);

	}

	// Method to check if all quotes can be fetched from database
	@Test
	void testGetAllQuote() throws Exception {
		String URI = "/api/v1/getQuote";
		SupplierQuote quote = new SupplierQuote();
		quote.setQuoteId(1);
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);

		SupplierQuote quote1 = new SupplierQuote();
		quote.setQuoteId(2);
		quote1.setUserName("hari01");
		quote1.setProductName("rice");
		quote1.setQuantity(20);
		quote1.setQuotePrice(500);
		quote.setProduct(null);

		List<SupplierQuote> supplierList = new ArrayList<>();
		supplierList.add(quote);
		supplierList.add(quote1);

		String jsonInput = this.converttoJson(supplierList);

		Mockito.when(supplierQuoteService.getAllQuote()).thenReturn(supplierList);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	// Test method to delete quote using quote ID
	@Test
	void testDeleteQuote() throws Exception {
		String URI = "/deleteQuote/{quoteId}";
		SupplierQuote quote = new SupplierQuote();
		quote.setQuoteId(1);
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);

		String jsonInput = this.converttoJson(quote);

		Optional<SupplierQuote> quote1 = Optional.of(quote);
		Mockito.when(supplierQuoteService.getQuoteById(Mockito.any())).thenReturn(quote1);
		Mockito.when(supplierQuoteService.deleteQuote(Mockito.any())).thenReturn(quote);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.delete(URI, 1).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertNotEquals(jsonInput, jsonOutput);

	}

	// Test method to update the quote price
	@Test
	void testUpdatePrice() throws Exception {

		String URI = "/updatePrice/{quoteId}";
		SupplierQuote quote = new SupplierQuote();
		quote.setQuoteId(1);
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);

		String jsonInput = this.converttoJson(quote);

		Mockito.when(supplierQuoteService.updateQuote(Mockito.any())).thenReturn(quote);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 2000, 1)
				.accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertNotEquals(jsonInput, jsonOutput);
	}

	private String converttoJson(Object supplierQuote) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(supplierQuote);
	}

}
