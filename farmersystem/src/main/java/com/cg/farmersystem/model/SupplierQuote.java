package com.cg.farmersystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quote")
public class SupplierQuote {
	@Id
	@NotNull(message = "Value cannot be Null")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull(message = "Value cannot be Null")
	private int quoteId;

	@NotNull(message = "Value cannot be Null")
	private String userName;

	@NotNull(message = "Value cannot be Null")
	private String productName;

	@NotNull(message = "Value cannot be Null")
	private int quantity;

	@NotNull(message = "Value cannot be Null")
	private int quotePrice;

	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;

	public SupplierQuote() {
		super();
	}

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(int quotePrice) {
		this.quotePrice = quotePrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SupplierQuote(int quoteId, String userName, String productName, int quantity, int quotePrice,
			Product product) {
		super();
		this.quoteId = quoteId;
		this.userName = userName;
		this.productName = productName;
		this.quantity = quantity;
		this.quotePrice = quotePrice;
		this.product = product;
	}

}
