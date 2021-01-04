package com.cg.farmersystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "login")

public class AdminLogin {

	@Id
	@NotNull(message="Value cannot be Null")
	@Column(length=50)
	private String adminUserName;
	@NotNull(message="Password cannot be Null")
	@Column(length=150)
	private String adminPassword;
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	public AdminLogin() {
		super();
		
	}
	public AdminLogin(String adminUserName, String adminPassword) {
		super();
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "AdminLogin [adminUserName=" + adminUserName + ", adminPassword=" + adminPassword + "]";
	}
	

}
