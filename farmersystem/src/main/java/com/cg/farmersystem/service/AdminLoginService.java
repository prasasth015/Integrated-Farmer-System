package com.cg.farmersystem.service;

import java.util.List;


import com.cg.farmersystem.model.AdminLogin;
import com.cg.farmersystem.model.Complaint;

public interface AdminLoginService {

	
	AdminLogin findByAdminUserNameAndAdminPassword(String adminUserName,String adminPassword);
	 List<Complaint> getAllComplaint();
	
	
	


}
