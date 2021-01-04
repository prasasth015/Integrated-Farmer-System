package com.cg.farmersystem.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.cg.farmersystem.model.Farmer;


@Repository
public interface FarmerJpaRepository extends JpaRepository<Farmer,String> {
	 Farmer findByFarmerUserNameAndFarmerPassword(String farmerUserName, String farmerPassword);
	 Optional<Farmer> findByFarmerUserName(String farmerUserName);
	
}
