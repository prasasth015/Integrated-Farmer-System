package com.cg.farmersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.farmersystem.model.Complaint;

public interface ComplaintJpaRepository extends JpaRepository<Complaint,Integer> {

}
