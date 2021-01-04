package com.cg.farmersystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.farmersystem.model.AdminLogin;

@Repository
public interface AdminLoginJpaRepository extends JpaRepository<AdminLogin,String> {
	
	AdminLogin findByAdminUserNameAndAdminPassword(String adminUserName, String adminPassword);
	
}
