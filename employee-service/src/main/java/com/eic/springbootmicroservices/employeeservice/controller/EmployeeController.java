package com.eic.springbootmicroservices.employeeservice.controller;

import java.util.List;

import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eic.springbootmicroservices.employeeservice.dto.ApiResponseDto;
import com.eic.springbootmicroservices.employeeservice.dto.EmployeeDto;
import com.eic.springbootmicroservices.employeeservice.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(savedEmployeeDto, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ApiResponseDto> getEmployeeById(@PathVariable("id") Long employeeId){
		ApiResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Page<ApiResponseDto>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "4") Integer pageSize,
			@RequestParam(defaultValue = "firstName") String sortField, @RequestParam(defaultValue = "asc") String sortOrder){
		Page<ApiResponseDto> pagedResult = employeeService.getAllEmployeesByPagingAndSorting(pageNo, pageSize, sortField, sortOrder);
		return new ResponseEntity<Page<ApiResponseDto>>(pagedResult, HttpStatus.OK); 
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<String>("Employee Deleted Successfully", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employee){
		employee.setId(id);
		EmployeeDto updatedEmployee = employeeService.updateEmployee(employee);
		return new ResponseEntity<EmployeeDto>(updatedEmployee, HttpStatus.OK);
	}
}
