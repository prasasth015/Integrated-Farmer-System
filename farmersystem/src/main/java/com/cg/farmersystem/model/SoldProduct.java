package com.cg.farmersystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "soldproduct")
public class SoldProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int invoiceId;
	private String userName;
	private String productName;
	private int quotePrice;
	private int quantity;
	

	@ManyToOne// (optional = false)
	@JoinColumn(name = "quoteId" )
	private SupplierQuote supplierQuote;
	
	
	public SoldProduct(int invoiceId, String userName, String productName, int quotePrice, int quantity,
			SupplierQuote supplierQuote) {
		super();
		this.invoiceId = invoiceId;
		this.userName = userName;
		this.productName = productName;
		this.quotePrice = quotePrice;
		this.quantity = quantity;
		this.supplierQuote = supplierQuote;
	}

	public SoldProduct() {
		super();

	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(int quotePrice) {
		this.quotePrice = quotePrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public SupplierQuote getSupplierQuote() {
		return supplierQuote;
	}

	public void setSupplierQuote(SupplierQuote supplierQuote) {
		this.supplierQuote = supplierQuote;
	}

	
	
	
	
	
	
}
