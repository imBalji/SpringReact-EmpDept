package com.practice.test.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.practice.controller.DepartmentCtrl;
import com.practice.model.Department;
import com.practice.service.DepartmentServ;

@WebMvcTest(controllers = DepartmentCtrl.class)
class DepartmentCtrlTest {
	
	@MockBean
	private DepartmentServ departmentServ;
	@Autowired
	private MockMvc mockMvc;	// a web server like

	@Test @DisplayName(value = "Should return all Departments;  endpoint: /departments")
	void test() throws Exception {
		
		when(departmentServ.getAllDepartments()).thenReturn(Arrays.asList(new Department("D001","SD","Software Developer"),new Department("D002","QA","Software Testing")));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/departments")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("D001")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("SD")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("Software Developer")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is("D002")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("QA")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.is("Software Testing")));
	}

}