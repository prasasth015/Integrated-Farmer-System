package com.cg.farmersystem.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farmersystem.model.AdminLogin;
import com.cg.farmersystem.model.Complaint;
import com.cg.farmersystem.repository.AdminLoginJpaRepository;
import com.cg.farmersystem.repository.ComplaintJpaRepository;



@Service
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService{
	
	@Autowired
	private AdminLoginJpaRepository adminLoginJpaRepository;
	@Autowired
	private ComplaintJpaRepository complaintJpaRepository;


	//Method to find Admin Username and Password
	@Override
	public AdminLogin findByAdminUserNameAndAdminPassword(String adminUserName, String adminPassword) {
		
		return adminLoginJpaRepository.findByAdminUserNameAndAdminPassword(adminUserName, adminPassword);
	}


	//Method for View Compliant
	@Override
	public List<Complaint> getAllComplaint() {
		
		return complaintJpaRepository.findAll();
	}
	
	
	

	
}
