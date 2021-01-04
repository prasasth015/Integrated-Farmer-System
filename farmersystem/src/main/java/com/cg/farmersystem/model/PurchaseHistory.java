package com.cg.farmersystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchasehistory1")

public class PurchaseHistory {
	
	@Id
	private Integer purchaseId;	
	
	@ManyToOne//, optional = false)
	@JoinColumn(name = "invoiceId" ) 
	  private SoldProduct soldProduct;

	public Integer getPurchaseId() {		
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public SoldProduct getSoldProduct() {
		return soldProduct;
	}

	public void setSoldProduct(SoldProduct soldProduct) {
		this.soldProduct = soldProduct;
	}

	
	public PurchaseHistory() {
		super();
	}

	public PurchaseHistory(Integer purchaseId, SoldProduct soldProduct) {
		super();
		this.purchaseId = purchaseId;
		this.soldProduct = soldProduct;
	}		
}


