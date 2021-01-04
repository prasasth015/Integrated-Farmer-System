package com.cg.farmersystem.service;


import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.farmersystem.model.Complaint;

import com.cg.farmersystem.repository.ComplaintJpaRepository;

@Service
public class ComplaintServiceImp implements ComplaintService {
	
	private static final Logger logger = LogManager.getLogger(ComplaintServiceImp.class);
	
	@Autowired
	private ComplaintJpaRepository complaintJpaRepository;

	
	//Method for save complaint
	@Override
	public Complaint insertComplaint(Complaint complaint) {
		logger.info("Inside service insert complaint ");
		
		return complaintJpaRepository.save(complaint);
	}

	//Method for getcomplaint by id
	@Override
	public Optional<Complaint> getComplaintById(int complaintId) {
		logger.info("Inside service getComplaintById  ");
		return complaintJpaRepository.findById(complaintId);
	}

	//Method for delete complaint by id
	@Override
	public  void  deleteComplaintById(int complaintId) {
		logger.info("Inside service delete complaint ");
	  complaintJpaRepository.deleteById(complaintId);
	  
	  
	 
	}

	

}
