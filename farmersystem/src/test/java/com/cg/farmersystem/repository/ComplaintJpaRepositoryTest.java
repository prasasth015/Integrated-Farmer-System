package com.cg.farmersystem.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.farmersystem.model.Complaint;

@RunWith(SpringRunner.class)
@DataJpaTest
class ComplaintJpaRepositoryTest{
	
	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private ComplaintJpaRepository complaintJpaRepository;

	
	private Complaint getComplaint() {
		Complaint complaint=new Complaint();
		
		
		complaint.setComplaintText("Payment not done");
		complaint.setFarmer(null);
		complaint.setSupplier(null);
		return complaint;
		
	}
	@Test
	 void testInsertComplaint() throws Exception {
		Complaint complaint=getComplaint();
		Complaint saveInDb=testEntityManager.persist(complaint);
		System.out.println(saveInDb);
		System.out.println("hello");
		Complaint getFromDb=complaintJpaRepository.findById(saveInDb.getComplaintId()).get();
		assertThat(getFromDb).isEqualTo(saveInDb);
		
	}
	
	
	
	  @Test 
	  void testDeleteComplaint()throws Exception {
	  
			Complaint complaint = getComplaint();
			Complaint saveInDb = testEntityManager.persist(complaint);
			complaintJpaRepository.deleteById(complaint.getComplaintId());
			List<Complaint> deleteCom = complaintJpaRepository.findAll();

			
			assertThat(deleteCom).isEmpty();

		}
	 
	@Test
	 void testGetComplaintById()
	{
		Complaint complaint=getComplaint();
		Complaint saveInDb=testEntityManager.persist(complaint);
		Complaint count=complaintJpaRepository.findById(complaint.getComplaintId()).get();
		assertThat(saveInDb).isEqualTo(count);
		
		
	}


	
}
