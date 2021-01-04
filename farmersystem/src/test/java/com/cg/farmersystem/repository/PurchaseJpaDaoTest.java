package com.cg.farmersystem.repository;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;



import com.cg.farmersystem.model.PurchaseHistory;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest

public class PurchaseJpaDaoTest {
	private static final Logger logger = LogManager.getLogger(PurchaseJpaDaoTest.class);
	
	@Autowired
    private PurchaseJpaDao purchaseJpaDao;
	
	@Autowired
    private TestEntityManager testEntityManager;
	
	//function to test for all purchase
	@Test
	public void testGetAllPurchase() {
		logger.info("testing PurchaseJpaDao test classs using functions");
		PurchaseHistory purchase=getPurchase();
		testEntityManager.persist(purchase);
		List<PurchaseHistory> lPurchase=purchaseJpaDao.findAll();
		Assert.assertEquals(1, lPurchase.size());
	}
	
	//function to test for purchaseId
	@Test
	public void testGetPurchaseById() throws Exception{
		PurchaseHistory purchase=new PurchaseHistory();
		purchase.setPurchaseId(100);
		PurchaseHistory saveInDb=testEntityManager.persist(purchase);
		PurchaseHistory getInDb = purchaseJpaDao.findById(purchase.getPurchaseId()).get();
        assertThat(getInDb).isEqualTo(saveInDb);
   
	}
	
	//function to get record 
	private PurchaseHistory getPurchase() {
		PurchaseHistory purchase=new PurchaseHistory();
		purchase.setPurchaseId(100);
		return purchase;
	}

	
}
