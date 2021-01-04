package com.cg.farmersystem.service;

import java.util.List;
import java.util.Optional;

import com.cg.farmersystem.model.Supplier;

public interface SupplierService {
	
	List<Supplier> getAllSupplier();

	public Supplier createSupplier(Supplier supplier);

	public Optional<Supplier> getSupplierById(String supplierUserName);

	public Supplier findBySupplierUserNameAndPassword(String supplierUserName, String password);
	
	
}
