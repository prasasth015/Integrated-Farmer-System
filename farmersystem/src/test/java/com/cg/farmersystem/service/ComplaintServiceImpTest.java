package com.cg.farmersystem.service;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import com.cg.farmersystem.model.Complaint;
import com.cg.farmersystem.model.Farmer;
import com.cg.farmersystem.model.Supplier;
import com.cg.farmersystem.repository.ComplaintJpaRepository;
import com.cg.farmersystem.repository.FarmerJpaRepository;

import junit.framework.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest
class ComplaintServiceImpTest {

	@Autowired
	private ComplaintServiceImp complaintServiceImp;
	
	@MockBean
	private ComplaintJpaRepository complaintJpaRepository;
	
	private Complaint getComplaint() {
		Complaint complaint=new Complaint();
		complaint.setComplaintText("Payment not done");
		complaint.setComplaintId(1);
		complaint.setFarmer(new Farmer());
		complaint.setSupplier(new Supplier());
		return complaint;
		
	}
	
	@Test
	 void testInsertComplaint()
	{
		Complaint complaint=getComplaint();
		Mockito.when(complaintJpaRepository.save(complaint)).thenReturn(complaint);
		assertThat(complaintServiceImp.insertComplaint(complaint)).isEqualTo(complaint);
	}
	
	@Test
	 void testGetComplaintById()
	{
		Complaint complaint=getComplaint();
		Optional<Complaint> opComplaint=Optional.of(complaint);
		Mockito.when(complaintJpaRepository.findById(2)).thenReturn(opComplaint);
		Assert.assertSame("Enter Valid Complaint id",complaintServiceImp.getComplaintById(1),opComplaint);
		
	}
	
	@Test
	 void testGetComplaintByIdNegative()
	{
		Complaint complaint=getComplaint();
		Optional<Complaint> opComplaint=Optional.of(complaint);
		Mockito.when(complaintJpaRepository.findById(complaint.getComplaintId())).thenReturn(opComplaint);
		assertThat(complaintServiceImp.getComplaintById(1)).isEqualTo(opComplaint);
	}
	
	@Test 
	void deleteComplaintById() { 
		Complaint complaint=getComplaint();
		
		complaintServiceImp.deleteComplaintById(complaint.getComplaintId());
		Optional<Complaint> deleteCom=complaintServiceImp.getComplaintById(complaint.getComplaintId());
		
		Assert.assertEquals(true,deleteCom.isEmpty() ); 
	
		
		
	  
	  
	  }
	 
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
