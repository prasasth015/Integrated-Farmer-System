package com.cg.farmersystem.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.cg.farmersystem.model.Farmer;
import com.cg.farmersystem.service.FarmerServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(value = FarmerController.class)
class FarmerControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private FarmerServiceImp farmerServiceImp;


	private Farmer getFarmer() {
		Farmer farmer=new Farmer();
		farmer.setFarmerAddress("Thane");
		
		farmer.setFarmerName("Preeti Shingate");
		farmer.setFarmerPassword("12345");
		farmer.setFarmerUserName("preeti12");
		
		return farmer;
	}
	@Test
	 void testRegister() throws Exception {
		String URI = "/api/v1/farmer";
		Farmer farmer=getFarmer();
		String jsonInput = this.converttoJson(farmer);
		Mockito.when(farmerServiceImp.insertFarmer(Mockito.any(Farmer.class))).thenReturn(farmer);
		MvcResult mvcResult=this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
		          .andReturn();
		 MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        System.out.println(jsonOutput);
	        Assert.assertEquals(jsonInput,jsonOutput);
	      
	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
		
		}
	
	
	@Test
	 void testGetAllFarmer() throws Exception {
		String URI = "/api/v1/farmer";
		Farmer farmer1=getFarmer();
		Farmer farmer=new Farmer();
		farmer.setFarmerAddress("Mulund");
		
		farmer.setFarmerName("Pratiksha Kangane");
		farmer.setFarmerPassword("12345");
		farmer.setFarmerUserName("pratiksha12");
		List<Farmer> farmerList = new ArrayList<>();
		farmerList.add(farmer);
		farmerList.add(farmer1);
		String jsonInput = this.converttoJson(farmerList);
		Mockito.when(farmerServiceImp.getAllFarmer()).thenReturn(farmerList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
		
		
		
	}
	
	@Test
	 void testLogin()throws Exception
	{
		String URI = "/api/v1/farmerlogin/{farmerUserName}/{farmerPassword}";
		Farmer farmer=getFarmer();
		
		Mockito.when(farmerServiceImp.findByFarmerUserNameAndFarmerPassword("preeti12","12345")).thenReturn(farmer);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, "preeti12","12345").accept(MediaType.APPLICATION_JSON))
	             .andReturn();
	     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	     
	     Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
		
	}
	@Test
	 void testLoginNegative()throws Exception
	{
		String URI = "/api/v1/farmerlogin/{farmerUserName}/{farmerPassword}";
		Farmer farmer=getFarmer();
		
		Mockito.when(farmerServiceImp.findByFarmerUserNameAndFarmerPassword("preeti","12345")).thenReturn(farmer);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, "preeti12","12345").accept(MediaType.APPLICATION_JSON))
	             .andReturn();
	     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	    
	     Assert.assertEquals("Enter valid usernam and password",HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
		
	}
	
	 
	
	 /**
     * Convert Object into Json String by using Jackson ObjectMapper
     * @param ticket
     * @return
     * @throws JsonProcessingException
     */
    private String converttoJson(Object farmer) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(farmer);
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
