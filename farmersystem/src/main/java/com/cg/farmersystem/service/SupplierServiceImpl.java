package com.cg.farmersystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farmersystem.model.Supplier;
import com.cg.farmersystem.repository.SupplierJpaRepository;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
	private static final Logger logger = LogManager.getLogger(SupplierServiceImpl.class);

	@Autowired
	private SupplierJpaRepository supplierJpaRepository;
	
	//for supplier registration
	@Override
	public Supplier createSupplier(Supplier supplier) {
		logger.info("in supplier service createSupplier");
		return supplierJpaRepository.save(supplier);
	}

	//to get all supplier details from the database
	@Override
	public List<Supplier> getAllSupplier() {
		logger.info("in supplier service getAllSupplier");
		return supplierJpaRepository.findAll();
	}

	//to get supplier by ID from the database
	@Override
	public Optional<Supplier> getSupplierById(String supplierUserName) {
		logger.info("in supplier service getSupplierById");
		return supplierJpaRepository.findById(supplierUserName);
	}

	//for supplier login
	@Override
	public Supplier findBySupplierUserNameAndPassword(String supplierUserName, String password) {
		logger.info("in supplier service findBySupplierUserNameAndPassword");
		return supplierJpaRepository.findBySupplierUserNameAndPassword(supplierUserName, password);
	}

}
