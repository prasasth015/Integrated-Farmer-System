package com.cg.farmersystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farmersystem.exception.SupplierExistException;
import com.cg.farmersystem.exception.SupplierNotFoundException;
import com.cg.farmersystem.model.Supplier;
import com.cg.farmersystem.service.SupplierService;

@RestController
@RequestMapping(path = "/api/v1")
public class SupplierController {

	private static final Logger logger = LogManager.getLogger(SupplierController.class);

	@Autowired
	private SupplierService supplierService;

	@PostMapping("/createSupplier")
	public Supplier createSupplier(@Valid @RequestBody Supplier supplier) throws SupplierExistException {
		logger.info("in supplier controller");
		boolean ifExist = supplierService.getSupplierById(supplier.getSupplierUserName()).isPresent();
		if (ifExist) {
			throw new SupplierExistException("Supplier Already present with this :" + supplier.getSupplierUserName());
		}

		return supplierService.createSupplier(supplier);
	}

	// method to fetch supplier details using supplier username
	@GetMapping("getSupplierById/{supplierUserName}")
	public ResponseEntity<Supplier> getSupplierById(@PathVariable(value = "supplierUserName") String supplierUserName)
			throws SupplierNotFoundException {
		logger.info("in getSupplierByID");
		Supplier supplier = supplierService.getSupplierById(supplierUserName).orElseThrow(
				() -> new SupplierNotFoundException("No Supplier found with this Id :" + supplierUserName));
		return ResponseEntity.ok().body(supplier);
	}

	// Method to fetch the list of suppliers
	@GetMapping("/supplier")
	public List<Supplier> getAllSupplier() {
		logger.info("in getAllSupplier");
		return supplierService.getAllSupplier();
	}

	// method for supplier login
	@GetMapping("/supplierLogin/{supplierUserName}/{password}")
	public ResponseEntity<String> supplierLogin(@PathVariable(value = "supplierUserName") String supplierUserName,
			@PathVariable(value = "password") String password) throws SupplierExistException {
		logger.info("in supplier Login");
		Supplier supplier = supplierService.findBySupplierUserNameAndPassword(supplierUserName, password);

		if (supplier == null) {
			throw new SupplierExistException(
					"No Supplier found with this UserName: " + supplierUserName + " and Password: " + password);
		}

		return ResponseEntity.ok().body("Login Successful");
	}
}
