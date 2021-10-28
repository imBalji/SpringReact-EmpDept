package com.practice.test.repo;

import org.testcontainers.containers.PostgreSQLContainer;

public class BaseTest {
	
	static PostgreSQLContainer container = new PostgreSQLContainer("postgres:alpine")
			.withDatabaseName("test_data")
			.withUsername("postgres")
			.withPassword("root");
	
//	public void startContainer() {
//		container.start();
//	}
	
	static {
		container.start();
	}
}