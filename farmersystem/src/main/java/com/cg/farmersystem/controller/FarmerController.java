package com.cg.farmersystem.controller;



import java.util.List;



import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.farmersystem.exception.FarmerExistException;



import com.cg.farmersystem.model.Farmer;

import com.cg.farmersystem.service.FarmerService;


@RestController
@RequestMapping("/api/v1")
public class FarmerController {

	private static final Logger logger = LogManager.getLogger(FarmerController.class);
	
	@Autowired
	private FarmerService farmerService;
	
	
	//Farmer Registration
	@PostMapping("/farmer")
	public Farmer register(@Valid @RequestBody Farmer farmer) throws FarmerExistException {
		logger.info("View Complaint");
		boolean ifExist=farmerService.getFarmerByUserName(farmer.getFarmerUserName()).isPresent();
		if(ifExist)
		{
			throw new FarmerExistException ("Farmer Already present with this username:"+farmer.getFarmerUserName());
		}
		
		return farmerService.insertFarmer(farmer);
	}
	//List of All the farmers 
	@GetMapping("/farmer")
	public List<Farmer> getAllFarmer() {
		logger.info("Get all farmer ");
		return farmerService.getAllFarmer();
		
	}
	
	
	
	//Login method
	@GetMapping("/farmerlogin/{farmerUserName}/{farmerPassword}")
	public ResponseEntity<String> login(@PathVariable(value="farmerUserName") String farmerUserName,  @PathVariable (value = "farmerPassword")String farmerPassword) throws FarmerExistException
	{
		logger.info("login Sucessful ");
		Farmer farmer=farmerService.findByFarmerUserNameAndFarmerPassword(farmerUserName, farmerPassword);
		
		
		if(farmer==null)
			{
				throw new FarmerExistException("No Farmer found with this UserName: " +farmerUserName +" and Password: " +farmerPassword);
			}
		
				
		return ResponseEntity.ok().body("Login Sucessful");
		
		
	}
	

}
