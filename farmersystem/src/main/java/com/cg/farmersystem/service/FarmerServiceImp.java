package com.cg.farmersystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farmersystem.model.Farmer;
import com.cg.farmersystem.repository.FarmerJpaRepository;

@Service
@Transactional
public class FarmerServiceImp implements FarmerService {
	private static final Logger logger = LogManager.getLogger(FarmerServiceImp.class);
	
	@Autowired
	private FarmerJpaRepository farmerJpaRepository;

	//Insert Farmer
	@Override
	public Farmer insertFarmer(Farmer farmer) {
		logger.info("Inside service insert farmer ");
		return farmerJpaRepository.save(farmer);
	}

	//Get All Farmers
	@Override
	public List<Farmer> getAllFarmer() {
		logger.info("Inside service get all method ");
		return farmerJpaRepository.findAll();
	}


	//Find by username and password for login
	@Override
	public Farmer findByFarmerUserNameAndFarmerPassword(String farmerUserName, String farmerPassword)  {
		logger.info("Inside service login method");
		return farmerJpaRepository.findByFarmerUserNameAndFarmerPassword(farmerUserName, farmerPassword);
	}

	//Get farmer By Username
	@Override
	public Optional<Farmer> getFarmerByUserName(String farmerUserName) {
		logger.info("Inside service getFarmerByUserName method ");
		return farmerJpaRepository.findByFarmerUserName(farmerUserName);
	}

	
	
}
