package com.cg.farmersystem.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "supplier_registration")
public class Supplier {

	@NotNull(message = "Value cannot be Null")
	@Size(max = 30)
	private String supplierName;

	@Id
	@NotNull(message = "Value cannot be Null")
	@Size(max = 30)
	@Column(unique = true)
	private String supplierUserName;

	@NotNull(message = "Value cannot be Null")
	@Size(max = 100)
	private String supplierAddress;

	@NotNull(message = "Value cannot be Null")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private long supplierContactNumber;

	@NotNull(message = "Value cannot be Null")
	@Size(max = 30)
	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "password must contain atleast one digit(0-9),atleast one lower case letter,atleast one uppercase letter,one special character (!,@,#), and password length must be minimum 8 and maximum 20 characters")
	private String password;

	@NotNull(message = "Value cannot be Null")
	@Size(max = 30)
	private String confirmPassword;

	// Getters and Setters for private variables
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierUserName() {
		return supplierUserName;
	}

	public void setSupplierUserName(String supplierUserName) {
		this.supplierUserName = supplierUserName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public long getSupplierContactNumber() {
		return supplierContactNumber;
	}

	public void setSupplierContactNumber(long supplierContactNumber) {
		this.supplierContactNumber = supplierContactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	// default constructor
	public Supplier() {
		super();
	}

	// parameterized constructor
	public Supplier(String supplierName, String supplierUserName, String supplierAddress, long supplierContactNumber,
			String password, String confirmPassword) {
		super();
		this.supplierName = supplierName;
		this.supplierUserName = supplierUserName;
		this.supplierAddress = supplierAddress;
		this.supplierContactNumber = supplierContactNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
}
