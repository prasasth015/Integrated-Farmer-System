package com.cg.farmersystem.model;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "product")


public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer productId;
	@Column(length=150)
	private String productName;
	@Column(length=150)
	private String productDescription;
	
	public Product() {
		super();
		
	}
	
	
	public Product(Integer productId, String productName, String productDescription) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
	}


	
	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	@Override
	public String toString() {
		return "ProductEntity [productId=" + productId + ", productName=" + productName + "]";
	}
	

}
