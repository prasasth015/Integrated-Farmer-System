package com.cg.farmersystem.model;


import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name="farmer")
public class Farmer  {
	
	private String farmerName;
	@Id
	@NotNull(message="Value cannot be Null")
	@Column(nullable=false)
	private String farmerUserName;
	
	@NotBlank
	@NotNull(message="Password cannot be Null")
	@Column(nullable=false)
	@Size(min=8,max=10,message="Mobile number must be 10 digits")
	private String farmerPassword;
	
	@NotNull(message="Value cannot be Null")
	@Column(nullable=false)
	private String farmerAddress;
	
	@NotNull(message="Value cannot be Null")
	
	@Pattern(regexp="(^$|[0-9]{10})",message="Mobile number must be 10 digits")
	@Size(min=10,max=10,message="Mobile number must be 10 digits")
	private BigInteger farmerContactNo;
	public Farmer() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Farmer [farmerName=" + farmerName + ", farmerUserName=" + farmerUserName + ", farmerPassword="
				+ farmerPassword + ", farmerAddress=" + farmerAddress + ", farmerContactNo=" + farmerContactNo + "]";
	}


	public Farmer(String farmerName, String farmerUserName, String farmerPassword, String farmerAddress,
			BigInteger farmerContactNo) {
		super();
		this.farmerName = farmerName;
		this.farmerUserName = farmerUserName;
		this.farmerPassword = farmerPassword;
		this.farmerAddress = farmerAddress;
		this.farmerContactNo = farmerContactNo;
	}
	
	
	
	public String getFarmerName() {
		return farmerName;
	}
	
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}
	public String getFarmerUserName() {
		return farmerUserName;
	}
	public void setFarmerUserName(String farmerUserName) {
		this.farmerUserName = farmerUserName;
	}
	public String getFarmerPassword() {
		return farmerPassword;
	}
	public void setFarmerPassword(String farmerPassword) {
		this.farmerPassword = farmerPassword;
	}
	public String getFarmerAddress() {
		return farmerAddress;
	}
	public void setFarmerAddress(String farmerAddress) {
		this.farmerAddress = farmerAddress;
	}
	public BigInteger getFarmerContactNo() {
		return farmerContactNo;
	}
	public void setFarmerContactNo(BigInteger farmerContactNo) {
		this.farmerContactNo = farmerContactNo;
	}
	

}
