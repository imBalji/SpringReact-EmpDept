package com.practice.repo;

import org.springframework.data.repository.CrudRepository;

import com.practice.model.Department;

public interface DepartmentRepo extends CrudRepository<Department, String> {}