package com.cg.farmersystem.service;




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

import com.cg.farmersystem.model.Farmer;
import com.cg.farmersystem.repository.FarmerJpaRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
class FarmerServiceImpTest {
	
	@Autowired
	private FarmerServiceImp farmerServiceImp;
	
	@MockBean
	private FarmerJpaRepository farmerJpaRepository;

	private Farmer getFarmer() {
		Farmer farmer=new Farmer();
		farmer.setFarmerAddress("Thane");
		
		farmer.setFarmerName("Preeti Shingate");
		farmer.setFarmerPassword("12345");
		farmer.setFarmerUserName("preeti12");
		
		return farmer;
	}

	@Test
	 void testInsertFarmer() {
		Farmer farmer=getFarmer();
		Mockito.when(farmerJpaRepository.save(farmer)).thenReturn(farmer);
		assertThat(farmerServiceImp.insertFarmer(farmer)).isEqualTo(farmer);
	}
	
	
	@Test
	 void testGetAllFarmer()
	{
		Farmer farmer=getFarmer();
		Farmer farmer1=new Farmer();
		farmer.setFarmerAddress("Thane");
		
		farmer.setFarmerName("Pratiksha Kangane");
		farmer.setFarmerPassword("12345");
		farmer.setFarmerUserName("pratiksha1");
		
		List<Farmer> alList=new ArrayList();
		alList.add(farmer1);
		alList.add(farmer);
		
		Mockito.when(farmerJpaRepository.findAll()).thenReturn(alList);
		assertThat(farmerServiceImp.getAllFarmer()).isEqualTo(alList);
	}
	
	@Test
	 void findByFarmerUserNameAndFarmerPassword()
	{
		Farmer farmer=getFarmer();
		Mockito.when(farmerJpaRepository.findByFarmerUserNameAndFarmerPassword(farmer.getFarmerUserName(),farmer.getFarmerPassword())).thenReturn(farmer);
		assertThat(farmerServiceImp.findByFarmerUserNameAndFarmerPassword("preeti12", "12345")).isEqualTo(farmer);
	}
	
	@Test
	 void findByFarmerUserName()
	{
		Farmer farmer=getFarmer();
		 Optional<Farmer> fo=Optional.of(farmer);
		
		Mockito.when(farmerJpaRepository.findByFarmerUserName(farmer.getFarmerUserName())).thenReturn(fo);
		assertThat(farmerServiceImp.getFarmerByUserName("preeti12")).isEqualTo(fo);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
