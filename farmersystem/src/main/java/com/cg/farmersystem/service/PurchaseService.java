package com.cg.farmersystem.service;

import java.util.List;
import java.util.Optional;
import com.cg.farmersystem.model.PurchaseHistory;

/**
 * @author Neelam
 *
 */
public interface PurchaseService {
	
	/**
	 * @return
	 */
	//function for getting all purchase
	List<PurchaseHistory> getAllPurchase();
	
	Optional<PurchaseHistory> findPurchaseById(Integer purchaseId);

}
