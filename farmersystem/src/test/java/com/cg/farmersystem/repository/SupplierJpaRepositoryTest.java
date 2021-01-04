package com.cg.farmersystem.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.farmersystem.model.Supplier;

@RunWith(SpringRunner.class)
@DataJpaTest
class SupplierJpaRepositoryTest {

	@Autowired
	private SupplierJpaRepository supplierJpaRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void testCreateSupplier() {
		Supplier supplier = getSupplier();
		Supplier saveInDb = testEntityManager.persist(supplier);
		Supplier getFromDb = supplierJpaRepository.save(supplier);
		assertThat(getFromDb).isEqualTo(saveInDb);
	}

	@Test
	void testGetSupplierById() throws Exception {
		Supplier supplier1 = new Supplier();
		supplier1.setSupplierName("kumar");
		supplier1.setSupplierUserName("kumar01");
		supplier1.setSupplierAddress("Chennai");
		supplier1.setSupplierContactNumber(987776769);
		supplier1.setPassword("kumar!");
		supplier1.setConfirmPassword("kumar!");

		// Insert Data into in memory database
		Supplier saveInDb = testEntityManager.persist(supplier1);
		// Get Data from DB
		Supplier getInDb = supplierJpaRepository.findById(supplier1.getSupplierUserName()).get();
		assertThat(getInDb).isEqualTo(saveInDb);
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

		// Save into in memory database
		testEntityManager.persist(supplier1);
		testEntityManager.persist(supplier2);

		// Retrieve all supplier
		List<Supplier> supplierList = (List<Supplier>) supplierJpaRepository.findAll();
		Assert.assertEquals(2, supplierList.size());
	}

	@Test
	void testFindBySupplierUserNameAndPassword() {
		Supplier supplier = getSupplier();
		Supplier check = supplierJpaRepository.findBySupplierUserNameAndPassword("kumar01", "kumar!");
		Supplier getFromDb = supplierJpaRepository.findBySupplierUserNameAndPassword(supplier.getSupplierUserName(),
				supplier.getPassword());
		assertThat(getFromDb).isEqualTo(check);

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
