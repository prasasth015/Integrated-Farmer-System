package com.cg.farmersystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.farmersystem.model.Supplier;

@Repository
public interface SupplierJpaRepository extends JpaRepository<Supplier, String> {

	Supplier findBySupplierUserNameAndPassword(String supplierUserName, String password);

	Optional<Supplier> findBySupplierUserName(String supplierUserName);
}
