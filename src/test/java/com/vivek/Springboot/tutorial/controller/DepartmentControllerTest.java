package com.vivek.Springboot.tutorial.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.vivek.Springboot.tutorial.entity.Department;
import com.vivek.Springboot.tutorial.service.DepartmentService;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;

	private Department department;
	

	@BeforeEach
	void setUp() {
		department = Department.builder().departmentAddress("Ahmedabad").departmentCode("IT-06").departmentName("IT")
				.departmentId(1L).build();
	}

	@Test
	void saveDepartment() throws Exception {
		Department inputDepartment = Department.builder().departmentAddress("Ahmedabad").departmentCode("IT-06")
				.departmentName("IT").build();

		Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

		mockMvc.perform(post("/departments").contentType(MediaType.APPLICATION_JSON)
				.content("{\n" + "\t\"departmentName\":\"IT\",\n" + "\t\"departmentAddress\":\"Ahmedabad\",\n"
						+ "\t\"departmentCode\":\"IT-06\"\n" + "}"))
				.andExpect(status().isCreated());

	}

	@Test
	void fetchDepartmentList() throws Exception {

		Department inputDepartment = Department.builder().departmentAddress("Bangalore").departmentId(2L)
				.departmentCode("CS-07").departmentName("CS").build();

		List<Department> listDepartment = new ArrayList<>();
		listDepartment.add(department);
		listDepartment.add(inputDepartment);

		Mockito.when(departmentService.fetchDepartmentList()).thenReturn(listDepartment);
		mockMvc.perform(get("/departments").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void fetchDepartmentById() throws Exception {
		Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

		mockMvc.perform(get("/departments/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
	}

	@Test
	void deleteDepartmentById() throws Exception {
		department = Department.builder().departmentAddress("Pune").departmentCode("EC-06").departmentName("EC")
				.departmentId(3L).build();
		mockMvc.perform(MockMvcRequestBuilders.delete("/departments/{id}", department.getDepartmentId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void updateDepartment() throws Exception {
		
		 Department savedDepartment = Department.builder().departmentAddress("Pune").departmentCode("EC-06").departmentName("EC")
					.departmentId(3L).build();
		 
		 Department updatedDepartment =Department.builder().departmentAddress("Pune").departmentCode("EC-07").departmentName("EC")
					.departmentId(3L).build();
		 Mockito.when(departmentService.updateDepartment(savedDepartment.getDepartmentId(), savedDepartment)).thenReturn(updatedDepartment);
		 
		 mockMvc.perform(put("/departments/3").contentType(MediaType.APPLICATION_JSON)
					.content("{\n" + "\t\"departmentName\":\"EC\",\n" + "\t\"departmentAddress\":\"Pune\",\n" + "\t\"departmentId\":\"3\",\n"
							+ "\t\"departmentCode\":\"EC-06\"\n" + "}"))
					.andExpect(status().isOk());

	       
	}

}