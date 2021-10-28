package com.practice.test.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.practice.model.Department;
import com.practice.repo.DepartmentRepo;
import com.practice.service.DepartmentServ;

@ExtendWith(MockitoExtension.class)
class DepartmentServTest {
	
	@Mock
	private DepartmentRepo departmentRepo;
	
	@Captor
	private ArgumentCaptor<Department> captor;
	
	private DepartmentServ departmentServ = null;
	
	@BeforeEach
	public void beforeEachTest() {
		departmentServ = new DepartmentServ(departmentRepo);
	}

	@Test @DisplayName(value = "Get all Departments")
	void testGetAll() {
		when(departmentRepo.findAll()).thenReturn(Arrays.asList(new Department("E001","SD","Software development"), new Department("E002","QA","Software testing")));
		
		List<Department> depts = (List<Department>) departmentServ.getAllDepartments();
		assertIterableEquals(depts, departmentServ.getAllDepartments());
	}
	
	@Test @DisplayName(value = "Get Department by ID")
	void testGetById() {
		when(departmentRepo.findById("E001")).thenReturn(Optional.of(new Department("E001","SD","Software development")));
		
		Optional<Department> dept = departmentServ.getDepartment("E001");
		assertEquals(dept, departmentServ.getDepartment("E001"));
	}
	
	@Test @DisplayName(value = "Should save Department")
	void testAddDepartment() {
		departmentServ.addDepartment(new Department("E001","SD","Software dev."));
		verify(departmentRepo, times(1)).save(captor.capture());
		
		assertAll(
				() ->	assertEquals("E001", captor.getValue().getId()),
				() ->	assertEquals("SD", captor.getValue().getName()),
				() ->	assertEquals("Software dev.", captor.getValue().getDescription())
				);
	}
}