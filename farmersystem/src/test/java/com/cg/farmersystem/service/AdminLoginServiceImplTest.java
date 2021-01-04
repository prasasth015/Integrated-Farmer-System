package com.cg.farmersystem.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.farmersystem.model.AdminLogin;
import com.cg.farmersystem.repository.AdminLoginJpaRepository;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
class AdminLoginServiceImplTest {

	@Autowired
	private AdminLoginServiceImpl adminLoginServiceImpl;
	
	@MockBean
	private AdminLoginJpaRepository adminLoginJpaRepository;
	
	private AdminLogin getAdminLogin() {
		AdminLogin adminlogin=new AdminLogin();
		adminlogin.setAdminUserName("neha");
		adminlogin.setAdminPassword("neha123");
		
		return adminlogin;
	}
	
	@Test
	 void findByAdminUserNameAndAdminPassword()
	{
		AdminLogin adminlogin=getAdminLogin();
		Mockito.when(adminLoginJpaRepository.findByAdminUserNameAndAdminPassword(adminlogin.getAdminUserName(),adminlogin.getAdminPassword())).thenReturn(adminlogin);
		assertThat(adminLoginServiceImpl.findByAdminUserNameAndAdminPassword("neha", "neha123")).isEqualTo(adminlogin);
	}

	
	
}
