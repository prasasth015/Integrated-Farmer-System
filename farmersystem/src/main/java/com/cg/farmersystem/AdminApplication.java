package com.cg.farmersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SpringBootApplication

public class AdminApplication {
	
	private static final Logger logger = LogManager.getLogger(AdminApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
		logger.info("Application started");
	}
	

}
