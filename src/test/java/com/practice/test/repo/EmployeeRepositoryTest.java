package com.practice.test.repo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.practice.model.Employee;
import com.practice.repo.EmployeeRepo;

@DataJpaTest	// will start spring context containing DB and jpa related functionalities
@Testcontainers	//	will enable test container in test class
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)	// explicitly specifying that spring context should not replace default data source
class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Container	// j-unit starts container
	PostgreSQLContainer container = new PostgreSQLContainer("postgres:alpine").withDatabaseName("test_data").withUsername("postgres").withPassword("root");
	
	@Test
	void saveEmployee() {
		Employee emp = new Employee("E001","Rachel Green","D001");
		Employee empSaved =  employeeRepo.save(emp);
		assertAll(
				() -> assertEquals(emp.getId(), empSaved.getId()),
				() -> assertEquals(emp.getName(), empSaved.getName()),
				() -> assertEquals(emp.getDepartment().getId(), empSaved.getDepartment().getId())
				);
	}
}