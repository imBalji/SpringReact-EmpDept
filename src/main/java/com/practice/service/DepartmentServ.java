package com.practice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.model.Department;
import com.practice.repo.DepartmentRepo;

@Service
public class DepartmentServ {
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	public DepartmentServ(DepartmentRepo departmentRepo) {
		super();
		this.departmentRepo = departmentRepo;
	}

	public Iterable<Department> getAllDepartments() {
		return departmentRepo.findAll();
	}
	
	public Optional<Department> getDepartment(String id) {
		return departmentRepo.findById(id);
	}

	public Department addDepartment(Department dept) {
		return departmentRepo.save(dept);
	}

	public Department updateDepartment(String id, Department dept) {
		return departmentRepo.save(dept);
	}

	public void deleteDepartment(String id) {
		departmentRepo.deleteById(id);
		return ;
	}
}