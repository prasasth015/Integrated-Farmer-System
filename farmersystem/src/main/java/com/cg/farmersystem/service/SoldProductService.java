package com.cg.farmersystem.service;

import java.util.Optional;

import com.cg.farmersystem.model.SoldProduct;


public interface SoldProductService {
	public Optional<SoldProduct> getSoldProductById(int invoiceId);
	 void deleteSoldProductById(int invoiceId);
	 SoldProduct insertSoldProduct(SoldProduct soldProduct);

}
