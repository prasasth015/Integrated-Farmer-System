package com.cg.farmersystem.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import com.cg.farmersystem.model.Farmer;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
class FarmerJpaRepositoryTest {
	
	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private FarmerJpaRepository farmerJpaRepository;

	@Test
	 void testInsertFarmer() {
		Farmer farmer=getFarmer();
		Farmer saveInDb=testEntityManager.persist(farmer);
		
		Farmer getFromDb=farmerJpaRepository.save(farmer);
		assertThat(getFromDb.equals(saveInDb));
	}
	
	@Test
	 void testGetFarmerByFarmerUserName() {
		
		Farmer farmer=getFarmer();
		Farmer saveInDb=testEntityManager.persist(farmer);
		Farmer getFromDb=farmerJpaRepository.findByFarmerUserName(farmer.getFarmerUserName()).get();
		Assert.assertEquals(saveInDb.getFarmerUserName(),getFromDb.getFarmerUserName() );
	}
	
	@Test
	 void testGetFarmerByFarmerUserNameAndPassword() {
		Farmer farmer=getFarmer();
		Farmer check=farmerJpaRepository.findByFarmerUserNameAndFarmerPassword("preeti12", "12345");
		Farmer saveInDb=testEntityManager.persist(farmer);
		Farmer getFromDb=farmerJpaRepository.findByFarmerUserNameAndFarmerPassword(farmer.getFarmerUserName(), farmer.getFarmerPassword());
		assertThat(getFromDb.equals(check));
		
	}
	
	@Test
	 void testGetAllFarmer()
	{
		Farmer farmer=getFarmer();
		testEntityManager.persist(farmer);
		
		List<Farmer> lFarmer=farmerJpaRepository.findAll();
		Assert.assertEquals(1, lFarmer.size());
		
		
		
	}
	

	private Farmer getFarmer() {
		Farmer farmer=new Farmer();
		farmer.setFarmerAddress("Thane");
		
		farmer.setFarmerName("Preeti Shingate");
		farmer.setFarmerPassword("12345");
		farmer.setFarmerUserName("preeti12");
		
		return farmer;
	}

}
