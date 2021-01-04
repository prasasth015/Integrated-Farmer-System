package com.cg.farmersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.farmersystem.model.PurchaseHistory;

@Repository
public interface PurchaseJpaDao extends JpaRepository<PurchaseHistory,Integer>{
		

}
