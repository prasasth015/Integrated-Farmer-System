package com.cg.farmersystem.controller;

import static org.junit.jupiter.api.Assertions.*;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;

import com.cg.farmersystem.model.AdminLogin;
import com.cg.farmersystem.service.AdminLoginServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminController.class)
class AdminControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminLoginServiceImpl adminLoginServiceImpl;
    
    private AdminLogin getAdminLogin() {
		AdminLogin adminlogin=new AdminLogin();
		adminlogin.setAdminUserName("neha");
		adminlogin.setAdminPassword("neha123");
		
		return adminlogin;
	}
    
    
    @Test
    void testAdminLogin()throws Exception
	{
		String URI = "/api/v1/adminlogin/{adminUserName}/{adminPassword}";
		AdminLogin adminlogin=getAdminLogin();
		String jsonInput = this.converttoJson(adminlogin);
		Mockito.when(adminLoginServiceImpl.findByAdminUserNameAndAdminPassword("neha", "neha123")).thenReturn(adminlogin);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, "neha","neha123").accept(MediaType.APPLICATION_JSON))
	             .andReturn();
	     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	     String jsonOutput = mockHttpServletResponse.getContentAsString();
	     Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
		
	}
    
    private String converttoJson(Object adminlogin) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(adminlogin);
    }

    
    

}
