package com.cg.farmersystem.service;


import java.util.Optional;

import com.cg.farmersystem.model.Complaint;


public interface ComplaintService {
	
	Complaint insertComplaint(Complaint complaint);
	
	Optional<Complaint> getComplaintById(int complaintId);
	  void deleteComplaintById(int complaintId);

}
