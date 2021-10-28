package com.practice.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.practice.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, String> {
	public List<Employee> findByName(String name);
	public List<Employee> findByDepartmentId(String name);
	public List<Employee> findByDesignation(String designation);
}