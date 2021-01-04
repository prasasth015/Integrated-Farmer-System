package com.cg.farmersystem.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.farmersystem.model.Supplier;
import com.cg.farmersystem.repository.SupplierJpaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class SupplierServiceTest {

	@MockBean
	private SupplierJpaRepository supplierJpaRepository;

	@Autowired
	private SupplierService supplierService;

	@Test
	void testCreateSupplier() {
		Supplier supplier = new Supplier();
		supplier.setSupplierName("surya");
		supplier.setSupplierUserName("surya01");
		supplier.setSupplierAddress("trichy");
		supplier.setSupplierContactNumber(988886769);
		supplier.setPassword("surya!");
		supplier.setConfirmPassword("surya!");

		Mockito.when(supplierJpaRepository.save(supplier)).thenReturn(supplier);
		assertThat(supplierService.createSupplier(supplier)).isEqualTo(supplier);
	}

	@Test
	void testGetAllSupplier() throws Exception {
		Supplier supplier1 = new Supplier();
		supplier1.setSupplierName("kumar");
		supplier1.setSupplierUserName("kumar01");
		supplier1.setSupplierAddress("Chennai");
		supplier1.setSupplierContactNumber(987776769);
		supplier1.setPassword("kumar!");
		supplier1.setConfirmPassword("kumar!");

		Supplier supplier2 = new Supplier();
		supplier2.setSupplierName("John");
		supplier2.setSupplierUserName("john987");
		supplier2.setSupplierAddress("Chennai");
		supplier2.setSupplierContactNumber(987776889);
		supplier2.setPassword("john!");
		supplier2.setConfirmPassword("john!");

		List<Supplier> supplierList = new ArrayList<>();
		supplierList.add(supplier1);
		supplierList.add(supplier2);

		Mockito.when(supplierJpaRepository.findAll()).thenReturn(supplierList);
		assertThat(supplierService.getAllSupplier()).isEqualTo(supplierList);
	}

	@Test
	void testGetSupplierById() {
		Supplier supplier = getSupplier();
		Optional<Supplier> supplier1 = Optional.of(supplier);
		Mockito.when(supplierJpaRepository.findById(supplier.getSupplierUserName())).thenReturn(supplier1);
		Assert.assertEquals(supplier1, supplierService.getSupplierById("kumar01"));

	}

	private Supplier getSupplier() {
		Supplier supplier1 = new Supplier();
		supplier1.setSupplierName("kumar");
		supplier1.setSupplierUserName("kumar01");
		supplier1.setSupplierAddress("Chennai");
		supplier1.setSupplierContactNumber(987776769);
		supplier1.setPassword("kumar!");
		supplier1.setConfirmPassword("kumar!");
		return supplier1;
	}

}
