package com.cg.farmersystem.model;

  
  


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity

public class Complaint  {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int complaintId;
	private String complaintText;
	
	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	

	@ManyToOne //( optional = false)
	@JoinColumn(name = "farmerUserName")
	private Farmer farmer;

	@ManyToOne// (optional = false)
	@JoinColumn(name = "supplierUserName" )
	private Supplier supplier;

	public String getComplaintText() {
		return complaintText;
	}

	public void setComplaintText(String complaint) {
		this.complaintText = complaint;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Complaint() {
		super();

	}

	public Complaint(int complaintId, String complaintText, Farmer farmer, Supplier supplier) {
		super();
		this.complaintId = complaintId;
		this.complaintText = complaintText;
		this.farmer = farmer;
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Complaint [complaintId=" + complaintId + ", complaint=" + complaintText + ", farmer=" + farmer
				+ ", supplier=" + supplier + "]";
	}

	
	
		
	

}
