package com.practice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.model.Employee;
import com.practice.repo.EmployeeRepo;

@Service
public class EmployeeServ {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public EmployeeServ(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}

	public List<Employee> getAllEmployees(String deptid){
		return employeeRepo.findByDepartmentId(deptid);
	}
	
	public Optional<Employee> getEmployee(String emplid) {
		return employeeRepo.findById(emplid);
	}

	public Employee addEmployee(Employee empl) {
		return employeeRepo.save(empl);
	}

	public Employee updateEmployee(Employee empl) {
		return employeeRepo.save(empl);
	}

	public void deleteEmployee(String emplid) {
		employeeRepo.deleteById(emplid);
		return ;
	}
}