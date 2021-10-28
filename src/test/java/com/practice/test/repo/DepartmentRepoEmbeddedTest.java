package com.practice.test.repo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.practice.model.Department;
import com.practice.repo.DepartmentRepo;

@DataJpaTest @ActiveProfiles(value = "test")
class DepartmentRepoEmbeddedTest {
	
	@Autowired
	private DepartmentRepo departmentRepo;

	@Test
	void saveDepartment() {
		Department dept = new Department("D001","SD","Software Developer");
		Department deptSaved = departmentRepo.save(dept);
		assertAll(
				() -> assertEquals(dept.getId(), deptSaved.getId()),
				() -> assertEquals(dept.getName(), deptSaved.getName()),
				() -> assertEquals(dept.getDescription(), deptSaved.getDescription())
				);
	}
}