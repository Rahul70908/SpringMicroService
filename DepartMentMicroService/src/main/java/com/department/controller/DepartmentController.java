package com.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.department.entity.Department;
import com.department.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/departments")
@Slf4j
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@PostMapping(path = "/saveDepartment")
	public Department saveDepartment(@RequestBody Department department) {
		log.info("Enter in DepartmentController.saveDepartment");
		Department saveDepartment = departmentService.saveDepartment(department);
		if (saveDepartment != null) {
			return saveDepartment;
		}
		return null;
	}

	@GetMapping(path = "/getDepartment")
	public Department findDepartmentByDepartmentId(@RequestParam("id") Integer id) {
		log.info("Enter in DepartmentController.findDepartmentByDepartmentId");
		Department department = departmentService.findDepartmentByDepartmentId(id);
		if (department != null) {
			return department;
		}
		return null;
	}
}