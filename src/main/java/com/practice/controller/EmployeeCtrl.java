package com.practice.controller;

import java.util.List;
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
import com.practice.model.Employee;
import com.practice.service.EmployeeServ;

@Controller @ResponseBody @CrossOrigin
public class EmployeeCtrl {
	
	@Autowired
	private EmployeeServ employeeServ;
	
	@RequestMapping(path = "/departments/{deptid}/employees", method = RequestMethod.GET)
	public List<Employee> getEmployees(@PathVariable(name = "deptid") String deptid) {
		return employeeServ.getAllEmployees(deptid);
	}
	
	@RequestMapping(path = "/departments/{deptid}/employees/{emplid}", method = RequestMethod.GET)
	public Optional<Employee> getEmployee(@PathVariable(name = "emplid") String emplid) {
		return employeeServ.getEmployee(emplid);
	}
	
	@RequestMapping(path = "/departments/{deptid}/employees", method = RequestMethod.POST)
	public Employee setEmployee(@PathVariable(name = "deptid") String deptid, @RequestBody Employee empl) {
		empl.setDepartment(new Department(deptid, "", ""));
		return employeeServ.addEmployee(empl);
	}
	
	@RequestMapping(path = "/departments/{deptid}/employees/{emplid}", method = RequestMethod.PUT)
	public Employee updateEmployee(@PathVariable(name = "deptid") String deptid, @PathVariable(name = "emplid") String emplid, @RequestBody Employee empl) {
		empl.setDepartment(new Department(deptid, "", ""));
		return employeeServ.updateEmployee(empl);
	}
	
	@RequestMapping(path = "/departments/{deptid}/employees/{emplid}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable(name = "emplid") String emplid) {
		employeeServ.deleteEmployee(emplid);
	}
}