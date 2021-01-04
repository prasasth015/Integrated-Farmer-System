package com.cg.farmersystem.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.farmersystem.model.SupplierQuote;

@RunWith(SpringRunner.class)
@DataJpaTest
class SupplierQuoteJpaRepositoryTest {

	@Autowired
	private SupplierQuoteJpaRepository supplierQuoteJpaRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void testInsertQuote() {
		SupplierQuote quote = new SupplierQuote();
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);
		SupplierQuote saveInDb = testEntityManager.persist(quote);
		SupplierQuote getFromDb = supplierQuoteJpaRepository.save(quote);
		assertThat(getFromDb).isEqualTo(saveInDb);
	}

	@Test
	void testGetAllQuote() throws Exception {
		SupplierQuote quote = new SupplierQuote();
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);

		SupplierQuote quote1 = new SupplierQuote();
		quote1.setUserName("kumar");
		quote1.setProductName("wheat");
		quote1.setQuantity(50);
		quote1.setQuotePrice(1000);
		quote1.setProduct(null);

		// Save into in memory database
		testEntityManager.persist(quote);
		testEntityManager.persist(quote1);

		// Retrieve all quotes
		List<SupplierQuote> supplierList = (List<SupplierQuote>) supplierQuoteJpaRepository.findAll();
		Assert.assertEquals(2, supplierList.size());
	}

	@Test
	void testGetQuoteById() throws Exception {
		SupplierQuote quote = new SupplierQuote();
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);

		// Insert Data into in memory database
		SupplierQuote saveInDb = testEntityManager.persist(quote);
		// Get Data from DB
		SupplierQuote getInDb = supplierQuoteJpaRepository.findById(quote.getQuoteId()).get();
		assertThat(getInDb).isEqualTo(saveInDb);
	}

	@Test
	void testUpdatePrice() {
		SupplierQuote quote2 = new SupplierQuote();
		quote2.setUserName("kumar");
		quote2.setProductName("wheat");
		quote2.setQuantity(50);
		quote2.setQuotePrice(1000);
		quote2.setProduct(null);
		testEntityManager.persist(quote2);
		SupplierQuote getFromDb = supplierQuoteJpaRepository.save(quote2);
		getFromDb.setQuotePrice(12000);
		testEntityManager.persist(getFromDb);
		assertNotEquals(quote2,getFromDb.getQuotePrice());
	}

	@Test
	void testDeleteQuote() throws Exception {
		SupplierQuote quote = new SupplierQuote();
		quote.setUserName("sivan");
		quote.setProductName("wheat");
		quote.setQuantity(80);
		quote.setQuotePrice(2000);
		quote.setProduct(null);

		SupplierQuote quote1 = new SupplierQuote();
		quote1.setUserName("vishnu");
		quote1.setProductName("rice");
		quote1.setQuantity(90);
		quote1.setQuotePrice(3000);
		quote1.setProduct(null);

		SupplierQuote supplierQuote = testEntityManager.persist(quote);
		testEntityManager.persist(quote1);
		// delete one supplier in DB
		testEntityManager.remove(supplierQuote);
		List<SupplierQuote> quotes = (List<SupplierQuote>) supplierQuoteJpaRepository.findAll();
		Assert.assertEquals(1, quotes.size());

	}

	private SupplierQuote getSupplierQuote() {
		SupplierQuote quote = new SupplierQuote();
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);

		return quote;
	}

}
