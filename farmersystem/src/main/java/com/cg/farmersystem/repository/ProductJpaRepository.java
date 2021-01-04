package com.cg.farmersystem.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.farmersystem.model.Product;



@Repository
public interface ProductJpaRepository extends JpaRepository<Product,Integer> {
	

}
