package com.cg.farmersystem.controller;




import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farmersystem.exception.ComplaintNotFoundException;


import com.cg.farmersystem.exception.ResourceNotFoundException;
import com.cg.farmersystem.model.Complaint;



import com.cg.farmersystem.service.ComplaintService;
import com.cg.farmersystem.service.FarmerService;
import com.cg.farmersystem.service.SupplierService;


@RequestMapping("api/v1")
@RestController
public class ComplaintController {
	
	private static final Logger logger = LogManager.getLogger(ComplaintController.class);

	@Autowired
	private ComplaintService complaintService;

	@Autowired
	private FarmerService farmerService;
	@Autowired
	private SupplierService supplierService;

	//Farmer raising complaint against supplier
	@PostMapping("/addcomplaint/{farmerUserName}/{supplierUserName}")
	public Complaint saveComplaint(@PathVariable(value = "farmerUserName") String farmerUserName,
			@PathVariable(value = "supplierUserName") String supplierUserName,
			@Valid @RequestBody Complaint complaint) throws ResourceNotFoundException {
		logger.info("Complaint Added");
		return supplierService.getSupplierById(supplierUserName).map(supplier -> {
			complaint.setSupplier(supplier);

			return farmerService.getFarmerByUserName(farmerUserName).map(farmer -> {
				complaint.setFarmer(farmer);

				return complaintService.insertComplaint(complaint);
			}).orElseThrow(() -> new ResourceNotFoundException("Farmer: " + farmerUserName + " not found"));
		}).orElseThrow(() -> new ResourceNotFoundException("Supplier: " + supplierUserName + " not found"));
				
	}
	
	//View complaint by id
	@GetMapping("/viewcomplaint/{complaintId}")
	public ResponseEntity<Complaint> getComplaintById(@PathVariable int complaintId) throws ComplaintNotFoundException {
		
		logger.info("View Complaint");
		Complaint complaint = complaintService.getComplaintById(complaintId).orElseThrow(
				() -> new ComplaintNotFoundException("Complaints not found with this complaint id " + complaintId));
		

		return ResponseEntity.ok().body(complaint);
	}

	//deleting complaint
	@DeleteMapping("/deletecomplaint/{complaintId}")
	public String deleteComplaintById(@PathVariable int complaintId) throws ComplaintNotFoundException {

		logger.info(" Complaint Deleted");
		 complaintService.getComplaintById(complaintId).orElseThrow(
				() -> new ComplaintNotFoundException("Complaints not found with this complaint id " + complaintId));
		complaintService.deleteComplaintById(complaintId);

		return " Complaint deleted succesfully";
	}

	
}
