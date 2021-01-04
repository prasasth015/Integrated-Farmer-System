package com.cg.farmersystem.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.farmersystem.exception.AdminExistException;
import com.cg.farmersystem.model.AdminLogin;
import com.cg.farmersystem.model.Complaint;

import com.cg.farmersystem.service.AdminLoginService;

@RestController
@RequestMapping("/api/v1")

public class AdminController {
	
	private static final Logger logger = LogManager.getLogger(AdminController.class);
	
	public AdminController() 
	{
		
		logger.info("Login details displayed");
		
	}
	
	@Autowired
	private AdminLoginService adminLoginService;
	
	
	//Admin Login
	@GetMapping("/adminlogin/{adminUserName}/{adminPassword}")
	public ResponseEntity<String> login(@PathVariable(value="adminUserName") String adminUserName,  @PathVariable (value = "adminPassword")String adminPassword) throws AdminExistException
	{
		logger.info("Login details displayed");
		AdminLogin login=adminLoginService.findByAdminUserNameAndAdminPassword(adminUserName, adminPassword);
		
		
		if(login==null)
			{
				throw new AdminExistException("No Admin found with this UserName: " +adminUserName +" and Password: " +adminPassword);
			}
		
				
		return ResponseEntity.ok().body("Login Sucessful");
		
		}
	//List of All the complaint 
		@GetMapping("/viewallcomplaint")
		public List<Complaint> getAllFarmer() {
			logger.info("Get all farmer ");
			return adminLoginService.getAllComplaint();
			
		}


}
