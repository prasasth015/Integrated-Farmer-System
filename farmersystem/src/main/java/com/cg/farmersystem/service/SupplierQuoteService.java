package com.cg.farmersystem.service;

import java.util.List;
import java.util.Optional;

import com.cg.farmersystem.model.SupplierQuote;

public interface SupplierQuoteService {
	
	List<SupplierQuote> getAllQuote();

	public SupplierQuote saveQuote(SupplierQuote quote);

	public SupplierQuote deleteQuote(SupplierQuote quote);

	public Optional<SupplierQuote> getQuoteById(Integer quoteId);

	public SupplierQuote updateQuote(SupplierQuote quote);

}
























