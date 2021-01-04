package com.cg.farmersystem.repository;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import com.cg.farmersystem.model.AdminLogin;

@RunWith(SpringRunner.class)
@DataJpaTest
class AdminLoginJpaRepositoryTest {

	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private AdminLoginJpaRepository adminLoginJpaRepository;
	
	
	private AdminLogin getAdminLogin() {
		AdminLogin adminlogin=new AdminLogin();
		adminlogin.setAdminUserName("neha");
		adminlogin.setAdminPassword("neha123");
		
		return adminlogin;
	}
	
	@Test
	 void testGetAdminLoginByAdminUserNameAndAdminPassword() {
		AdminLogin adminlogin=getAdminLogin();
		AdminLogin check=adminLoginJpaRepository.findByAdminUserNameAndAdminPassword("neha", "neha123");
		AdminLogin saveInDb=testEntityManager.persist(adminlogin);
		AdminLogin getFromDb=adminLoginJpaRepository.findByAdminUserNameAndAdminPassword(adminlogin.getAdminUserName(),adminlogin.getAdminPassword());
		assertThat(getFromDb.equals(check));
		
	}

}
