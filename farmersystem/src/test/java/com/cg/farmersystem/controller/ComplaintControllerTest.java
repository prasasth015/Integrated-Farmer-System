package com.cg.farmersystem.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

import com.cg.farmersystem.model.Complaint;
import com.cg.farmersystem.model.Farmer;
import com.cg.farmersystem.model.Supplier;
import com.cg.farmersystem.service.ComplaintServiceImp;
import com.cg.farmersystem.service.FarmerServiceImp;
import com.cg.farmersystem.service.SupplierServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebMvcTest(value = ComplaintController.class)
class ComplaintControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComplaintServiceImp complaintServiceImp;
    
    @MockBean
    private SupplierServiceImpl supplierServiceImp;
    
    @MockBean
    private FarmerServiceImp farmerServiceImp;
    
    
    
	private Complaint getComplaint() {
		Complaint complaint=new Complaint();
		complaint.setComplaintText("Payment not done");
		complaint.setComplaintId(1);
		complaint.setFarmer(new Farmer());
		complaint.setSupplier(new Supplier("Neha","neha1","Thane",963258741,"12345","12345"));
		return complaint;
		
	}
	
	@Test
	 void testInsertComplaint()throws Exception {
		String URI="/api/v1/complaint/{farmerUserName}/{supplierUserName}";
		Complaint complaint=getComplaint();
		String jsonInput = this.converttoJson(complaint);
		Mockito.when(complaintServiceImp.insertComplaint(Mockito.any(Complaint.class))).thenReturn(complaint);
		MvcResult mvcResult=this.mockMvc.perform(MockMvcRequestBuilders.post(URI,"pratik1","neha1").accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
		          .andReturn();
		 MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        
	       Assert.assertNotEquals(jsonInput,jsonOutput);
	       
	       Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	
	}
	@Test
	 void testGetComplaintById() throws Exception
	{
		String URI="/api/v1/viewcomplaint/{complaintId}";
		Complaint complaint=getComplaint();
		Optional<Complaint> opComplaint=Optional.of(complaint);
		String jsonInput = this.converttoJson(complaint);
		Mockito.when(complaintServiceImp.getComplaintById(complaint.getComplaintId())).thenReturn(opComplaint);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 1).accept(MediaType.APPLICATION_JSON))
	             .andReturn();
	     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	     String jsonOutput = mockHttpServletResponse.getContentAsString();

	     assertThat(jsonInput).isEqualTo(jsonOutput);
		}
	@Test
	 void testGetComplaintByIdNegative() throws Exception
	{
		String URI="/api/v1/viewcomplaint/{complaintId}";
		Complaint complaint=getComplaint();
		Optional<Complaint> opComplaint=Optional.of(complaint);
		String jsonInput = this.converttoJson(complaint);
		Mockito.when(complaintServiceImp.getComplaintById(complaint.getComplaintId())).thenReturn(opComplaint);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 1).accept(MediaType.APPLICATION_JSON))
	             .andReturn();
	     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	     String jsonOutput = mockHttpServletResponse.getContentAsString();

	     Assert.assertSame("Enter valid complaint id",jsonInput,jsonOutput);
		}
	
	@Test
	 void testDeleteComplaintById() throws Exception {
		String URI = "/api/v1/deletecomplaint/{complaintId}";
		Complaint complaint = getComplaint();
		Optional<Complaint> opComplaint = Optional.of(complaint);
		Mockito.when(complaintServiceImp.getComplaintById(Mockito.anyInt())).thenReturn(opComplaint);
		complaintServiceImp.deleteComplaintById(complaint.getComplaintId());
		MvcResult mvcResult =this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 1).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}
	 

	 /**
     * Convert Object into Json String by using Jackson ObjectMapper
     * @param ticket
     * @return
     * @throws JsonProcessingException
     */
    private String converttoJson(Object complaint) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(complaint);
    }
}
