package com.cg.farmersystem.service;

import java.util.List;
import java.util.Optional;

import com.cg.farmersystem.model.Farmer;





public interface FarmerService {
	
	
	Farmer insertFarmer(Farmer farmer);
	List<Farmer> getAllFarmer();
	Farmer findByFarmerUserNameAndFarmerPassword(String farmerUserName,String farmerPassword);
	public Optional<Farmer> getFarmerByUserName(String farmerUserName);
	

}
