package com.eic.springbootmicroservices.departmentservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eic.springbootmicroservices.departmentservice.dto.DepartmentDto;
import com.eic.springbootmicroservices.departmentservice.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(
		name = "Department Controller",
		description = "Department Controller REST API's"
		)
@RestController
@AllArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {
	
	private DepartmentService departmentService;
	@Operation(
			summary = "Save Department REST API",
			description = "Save Department REST API is used to save department to the database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 Created"
			
			)
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<DepartmentDto> saveDepartment(@Valid @RequestBody DepartmentDto departmentDto){
		DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
		return new ResponseEntity<>(savedDepartmentDto,HttpStatus.CREATED);
	}
	
	@Operation(
			summary = "Get Department By Code REST API",
			description = "Get Department By Code REST API is used to get department using department code from the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
			)
	@GetMapping("{department-code}")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode){
		DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
		return new ResponseEntity<DepartmentDto>(departmentDto, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Get All Departments REST API",
			description = "Get All Departments REST API is used to view all departments from the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
			
			)
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
		List<DepartmentDto> departments = departmentService.getAllDepartments();
		return new ResponseEntity<List<DepartmentDto>>(departments,HttpStatus.OK);
	}

	@Operation(
			summary = "Update Department REST API",
			description = "Update Department REST API is used to update existing department to the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
			
			)
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{departmentCode}")
	public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable String departmentCode,@RequestBody DepartmentDto departmentDto){
		DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(departmentCode, departmentDto);
		return new ResponseEntity<DepartmentDto>(updatedDepartmentDto, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Delete Department REST API",
			description = "Delete Department REST API is used to delete department from the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 Created"
			
			)
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{departmentCode}")
	public ResponseEntity<String> deleteDepartment(@PathVariable String departmentCode){
		departmentService.deleteDepartment(departmentCode);
		return new ResponseEntity<String>("Department Deleted Successfully", HttpStatus.OK);
	}
}
