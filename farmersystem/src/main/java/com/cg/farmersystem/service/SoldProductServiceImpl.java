package com.cg.farmersystem.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farmersystem.model.SoldProduct;
import com.cg.farmersystem.repository.SoldProductJpaRepository;


@Service
public class SoldProductServiceImpl implements SoldProductService{
	
	@Autowired
	private SoldProductJpaRepository soldProductJpaRepository;

	
	//Method for deleting invoice for sold product
	@Override
	public void deleteSoldProductById(int invoiceId) {

		soldProductJpaRepository.deleteById(invoiceId);	
	}

	//Method for inserting a new invoice for Sold Product
	@Override
	public SoldProduct insertSoldProduct(SoldProduct soldProduct) {

		return soldProductJpaRepository.save(soldProduct);
	}

	//Method for fetching Sold Product invoice by ID
	@Override
	public Optional<SoldProduct> getSoldProductById(int invoiceId) {

		return soldProductJpaRepository.findById(invoiceId);
	}


	
	
	

}
