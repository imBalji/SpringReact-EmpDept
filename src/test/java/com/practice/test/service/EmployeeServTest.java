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

import com.practice.model.Employee;
import com.practice.repo.EmployeeRepo;
import com.practice.service.EmployeeServ;

@ExtendWith(MockitoExtension.class)
class EmployeeServTest {
	
	@Mock
	private EmployeeRepo employeeRepo;
	
	@Captor
	private ArgumentCaptor<Employee> captor;
	
	private EmployeeServ employeeServ = null;
	
	@BeforeEach
	public void beforeEachTest() {
		employeeServ = new EmployeeServ(employeeRepo);
	}

	@Test @DisplayName(value = "Get all Employees")
	void testGetAll() {
		when(employeeRepo.findByDepartmentId("D001")).thenReturn(Arrays.asList(new Employee("E001","SD","D001"), new Employee("E003","SD","D001")));
		when(employeeRepo.findByDepartmentId("D002")).thenReturn(Arrays.asList(new Employee("E002","QA","D002"), new Employee("E004","QA","D002")));
		
		List<Employee> D1emps = (List<Employee>) employeeServ.getAllEmployees("D001");
		List<Employee> D2emps = (List<Employee>) employeeServ.getAllEmployees("D002");
		assertIterableEquals(D1emps, employeeServ.getAllEmployees("D001"));
		assertIterableEquals(D2emps, employeeServ.getAllEmployees("D002"));
	}
	
	@Test @DisplayName(value = "Get Employee by ID")
	void testGetById() {
		when(employeeRepo.findById("E001")).thenReturn(Optional.of(new Employee("E001","SD","D001")));
		
		Optional<Employee> dept = employeeServ.getEmployee("E001");
		assertEquals(dept, employeeServ.getEmployee("E001"));
	}
	
	@Test @DisplayName(value = "Should save Employee")
	void testAddDepartment() {
		employeeServ.addEmployee(new Employee("E001","SD","D001"));
		verify(employeeRepo, times(1)).save(captor.capture());
		
		assertAll(
				() ->	assertEquals("E001", captor.getValue().getId()),
				() ->	assertEquals("SD", captor.getValue().getName()),
				() ->	assertEquals("D001", captor.getValue().getDepartment().getId())
				);
	}
}