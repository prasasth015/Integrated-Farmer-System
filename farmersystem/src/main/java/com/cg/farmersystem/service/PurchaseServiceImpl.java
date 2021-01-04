package com.cg.farmersystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.farmersystem.model.PurchaseHistory;
import com.cg.farmersystem.repository.PurchaseJpaDao;

@Service
@Transactional

public class PurchaseServiceImpl implements PurchaseService{
	
	//logger variable
	public static final Logger logger = LogManager.getLogger(PurchaseServiceImpl.class);
	
	@Autowired
	private PurchaseJpaDao purchaseJpaDao;
	
	//function to get all purchase
	@Override
	public List<PurchaseHistory> getAllPurchase() {
		logger.info("PurchaseService Implementation class running");
		
		return purchaseJpaDao.findAll();
	}

	//function to get purchase by Id
	@Override
	public Optional<PurchaseHistory> findPurchaseById(Integer purchaseId) {
		logger.info("Purchase Service class return purchase Id ");
		
		return purchaseJpaDao.findById(purchaseId);
	}

}
