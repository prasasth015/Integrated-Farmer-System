package com.cg.farmersystem.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.cg.farmersystem.model.Supplier;
import com.cg.farmersystem.service.SupplierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SupplierController.class)
class SupplierControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SupplierService supplierService;

	// Test method to check supplier registration
	@Test
	void testcreateSupplier() throws Exception {
		String URI = "/api/v1/createSupplier";
		Supplier supplier = new Supplier();
		supplier.setSupplierName("John");
		supplier.setSupplierUserName("john987");
		supplier.setSupplierAddress("Chennai");
		supplier.setSupplierContactNumber(987776889);
		supplier.setPassword("john!");
		supplier.setConfirmPassword("john!");
		String jsonInput = this.converttoJson(supplier);

		Mockito.when(supplierService.createSupplier(Mockito.any(Supplier.class))).thenReturn(supplier);

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	// Test method to check supplier details can be fetched using supplier username
	@Test
	void testGetSupplierById() throws Exception {
		String URI = "/api/v1/getSupplierById/{supplierUserName}";
		Supplier supplier = new Supplier();
		supplier.setSupplierName("kumar");
		supplier.setSupplierUserName("kumar01");
		supplier.setSupplierAddress("Chennai");
		supplier.setSupplierContactNumber(987776769);
		supplier.setPassword("kumar!");
		supplier.setConfirmPassword("kumar!");
		String jsonInput = this.converttoJson(supplier);
		Optional<Supplier> supplier1 = Optional.of(supplier);

		Mockito.when(supplierService.getSupplierById(supplier.getSupplierUserName())).thenReturn(supplier1);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, "kumar01").accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);

	}

	// Test method to check if all supplier details can be fetched from database
	@Test
	void testGetAllSupplier() throws Exception {
		String URI = "/api/v1/supplier";
		Supplier supplier1 = new Supplier();
		supplier1.setSupplierName("kumar");
		supplier1.setSupplierUserName("kumar01");
		supplier1.setSupplierAddress("Chennai");
		supplier1.setSupplierContactNumber(987776769);
		supplier1.setPassword("kumar!");
		supplier1.setConfirmPassword("kumar!");

		Supplier supplier2 = new Supplier();
		supplier2.setSupplierName("John");
		supplier2.setSupplierUserName("john987");
		supplier2.setSupplierAddress("Chennai");
		supplier2.setSupplierContactNumber(987776889);
		supplier2.setPassword("john!");
		supplier2.setConfirmPassword("john!");

		List<Supplier> supplierList = new ArrayList<>();
		supplierList.add(supplier1);
		supplierList.add(supplier2);
		String jsonInput = this.converttoJson(supplierList);

		Mockito.when(supplierService.getAllSupplier()).thenReturn(supplierList);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	// Test method to check supplier login
	@Test
	void testSupplierLogin() throws Exception {
		String URI = "/api/v1//supplierLogin/{supplierUserName}/{password}";
		Supplier supplier1 = new Supplier();
		supplier1.setSupplierName("kumar");
		supplier1.setSupplierUserName("kumar01");
		supplier1.setSupplierAddress("Chennai");
		supplier1.setSupplierContactNumber(987776769);
		supplier1.setPassword("kumar!");
		supplier1.setConfirmPassword("kumar!");

		Mockito.when(supplierService.findBySupplierUserNameAndPassword(supplier1.getSupplierUserName(),
				supplier1.getPassword())).thenReturn(supplier1);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, "kumar01", "kumar!").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}

	// Negative Test method to check supplier login
	@Test
	void testSupplierLoginNegative() throws Exception {
		String URI = "/api/v1//supplierLogin/{supplierUserName}/{password}";
		Supplier supplier1 = new Supplier();
		supplier1.setSupplierName("kumar");
		supplier1.setSupplierUserName("kumar01");
		supplier1.setSupplierAddress("Chennai");
		supplier1.setSupplierContactNumber(987776769);
		supplier1.setPassword("kumar!");
		supplier1.setConfirmPassword("kumar!");

		Mockito.when(supplierService.findBySupplierUserNameAndPassword(supplier1.getSupplierUserName(),
				supplier1.getPassword())).thenReturn(supplier1);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, "kumar", "kumar!").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}

	private String converttoJson(Object supplier) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(supplier);
	}
}
