package com.cg.farmersystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import com.cg.farmersystem.model.SupplierQuote;
import com.cg.farmersystem.repository.SupplierQuoteJpaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class SupplierQuoteServiceTest {

	@MockBean
	private SupplierQuoteJpaRepository supplierQuoteJpaRepository;

	@Autowired
	private SupplierQuoteService supplierQuoteService;

	@Test
	void testAddQuote() {
		SupplierQuote quote = new SupplierQuote();
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);

		Mockito.when(supplierQuoteJpaRepository.save(quote)).thenReturn(quote);
		assertThat(supplierQuoteService.saveQuote(quote)).isEqualTo(quote);
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

		List<SupplierQuote> quoteList = new ArrayList<>();
		quoteList.add(quote);
		quoteList.add(quote1);

		Mockito.when(supplierQuoteJpaRepository.findAll()).thenReturn(quoteList);
		assertThat(supplierQuoteService.getAllQuote()).isEqualTo(quoteList);
	}

	@Test
	void testGetQuoteById() {
		SupplierQuote quote = new SupplierQuote();
		quote.setQuoteId(1);
		quote.setUserName("kumar");
		quote.setProductName("wheat");
		quote.setQuantity(50);
		quote.setQuotePrice(1000);
		quote.setProduct(null);
		Optional<SupplierQuote> supplierQuote = Optional.of(quote);
		Mockito.when(supplierQuoteJpaRepository.findById(quote.getQuoteId())).thenReturn(supplierQuote);
		Assert.assertEquals(supplierQuote, supplierQuoteService.getQuoteById(1));

	}

	@Test
	void testDeleteQuote() throws Exception {
		SupplierQuote quote = new SupplierQuote();
		quote.setQuoteId(1);
		quote.setUserName("sivan");
		quote.setProductName("wheat");
		quote.setQuantity(80);
		quote.setQuotePrice(2000);
		quote.setProduct(null);

		Optional<SupplierQuote> li = supplierQuoteService.getQuoteById(1);
		supplierQuoteService.deleteQuote(quote);
		assertNotEquals(li, quote);

	}

	@Test
	void testUpdatePrice() {
		SupplierQuote quote = new SupplierQuote();
		quote.setQuoteId(100);
		quote.setUserName("sivan");
		quote.setProductName("wheat");
		quote.setQuantity(80);
		quote.setQuotePrice(2000);
		quote.setProduct(null);
		SupplierQuote quote1 = new SupplierQuote();
		quote1.setQuotePrice(3000);
		quote = supplierQuoteService.updateQuote(quote);
		assertNotEquals(quote, quote1);

	}

}
