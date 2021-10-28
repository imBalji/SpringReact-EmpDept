package com.practice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.model.Department;
import com.practice.service.DepartmentServ;

@Controller @ResponseBody @CrossOrigin
public class DepartmentCtrl {
	
	@Autowired
	private DepartmentServ departmentServ;
	
	@RequestMapping(path = "/departments", method = RequestMethod.GET)
	public Iterable<Department> getDepartments() {
		return departmentServ.getAllDepartments();
	}
	
	@RequestMapping(path = "/departments/{id}", method = RequestMethod.GET)
	public Optional<Department> getDepartment(@PathVariable(name = "id") String id) {
		return departmentServ.getDepartment(id);
	}
	
	@RequestMapping(path = "/departments", method = RequestMethod.POST)
	public void setEmployee(@RequestBody Department dept) {
		departmentServ.addDepartment(dept);
	}
	
	@RequestMapping(path = "/departments/{id}", method = RequestMethod.PUT)
	public Department updateDepartment(@PathVariable(name = "id") String id, @RequestBody Department dept) {
		return departmentServ.updateDepartment(id, dept);
	}
	
	@RequestMapping(path = "/departments/{id}", method = RequestMethod.DELETE)
	public void deleteDepartment(@PathVariable(name = "id") String id) {
		departmentServ.deleteDepartment(id);
		return ;
	}
}