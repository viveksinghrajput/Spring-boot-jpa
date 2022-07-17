package com.vivek.Springboot.tutorial.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.Springboot.tutorial.entity.Department;
import com.vivek.Springboot.tutorial.error.DepartmentNotFoundException;
import com.vivek.Springboot.tutorial.service.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@PostMapping("/departments")
	public ResponseEntity<@Valid Department> saveDepartment(@Valid @RequestBody Department department) {
		LOGGER.info("Inside saveDepartment of DepartmentController");
		HttpHeaders headers = new HttpHeaders();
		headers.add("details", "Passing One Student Details");
		Department depat = departmentService.saveDepartment(department);

		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(depat);
	}

	@GetMapping("/departments")
	public ResponseEntity<List<Department>> fetchDepartmentList() {
		LOGGER.info("Inside fetchDepartmentList of DepartmentController");
		HttpHeaders headers = new HttpHeaders();
		headers.add("details", "Fetch Department List");
		List<Department> departList = departmentService.fetchDepartmentList();
		return ResponseEntity.ok().headers(headers).body(departList);
	}

	@GetMapping("/departments/{id}")
	public ResponseEntity<Department> fetchDepartmentById(@PathVariable("id") Long departmentId)
			throws DepartmentNotFoundException {
		LOGGER.info("inside fetchDepartmentById of DepartmentController");
		HttpHeaders headers = new HttpHeaders();
		headers.add("details", "Fetch Department by Id");
		Department departById = departmentService.fetchDepartmentById(departmentId);
		return ResponseEntity.ok().headers(headers).body(departById);

	}

	@DeleteMapping("/departments/{id}")
	public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") Long departmentId) {
		LOGGER.info("inside deleteDepartmentById of DepartmentController");
		HttpHeaders headers = new HttpHeaders();
		headers.add("details", "Delete Department by Id");
		departmentService.deleteDepartmentById(departmentId);
		return ResponseEntity.ok().headers(headers).body("Department deleted Successfully!!");
	}

	@PutMapping("/departments/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long departmentId,
			@RequestBody Department department) {
		LOGGER.info("inside updateDepartment of DepartmentController");
		HttpHeaders headers = new HttpHeaders();
		headers.add("details", "Update Department by Id");
		Department updateDepart = departmentService.updateDepartment(departmentId, department);
		return ResponseEntity.ok().headers(headers).body(updateDepart);
	}

	@GetMapping("/departments/name/{name}")
	public ResponseEntity<Department> fetchDepartmentByName(@PathVariable("name") String departmentName) {
		LOGGER.info("inside fetchDepartmentByName of DepartmentController");
		HttpHeaders headers = new HttpHeaders();
		headers.add("details", "Fetch Department by Name");
		Department departByName = departmentService.fetchDepartmentByName(departmentName);
		return ResponseEntity.ok().headers(headers).body(departByName);
	}
}
