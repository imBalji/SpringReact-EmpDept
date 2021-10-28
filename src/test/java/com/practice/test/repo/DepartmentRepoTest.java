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

import com.practice.model.Department;
import com.practice.repo.DepartmentRepo;

@DataJpaTest	// will start spring context containing DB and jpa related functionalities
@Testcontainers	//	will enable test container in test class
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)	// explicitly specifying that spring context should not replace default data source
class DepartmentRepoTest{
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Container	// j-unit starts container
	PostgreSQLContainer container = new PostgreSQLContainer("postgres:alpine").withDatabaseName("test_data").withUsername("postgres").withPassword("root");
	
	@Test
	void saveEmployee() {
		Department dept = new Department("D001","SD","Software Developers");
		Department deptSaved =  departmentRepo.save(dept);
		assertAll(
				() -> assertEquals(dept.getId(), deptSaved.getId()),
				() -> assertEquals(dept.getName(), deptSaved.getName()),
				() -> assertEquals(dept.getDescription(), deptSaved.getDescription())
				);
	}
}