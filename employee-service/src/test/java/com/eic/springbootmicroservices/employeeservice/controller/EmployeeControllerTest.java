package com.eic.springbootmicroservices.employeeservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import com.eic.springbootmicroservices.employeeservice.dto.ApiResponseDto;
import com.eic.springbootmicroservices.employeeservice.dto.DepartmentDto;
import com.eic.springbootmicroservices.employeeservice.dto.EmployeeDto;
import com.eic.springbootmicroservices.employeeservice.dto.OrganizationDto;
import com.eic.springbootmicroservices.employeeservice.service.EmployeeService;

class EmployeeControllerTest {
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeService employeeService;
	
	private EmployeeDto employeeDto;
	
	private DepartmentDto departmentDto;
	
	private OrganizationDto organizationDto;
	
	private ApiResponseDto apiResponseDto;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		employeeController=new EmployeeController(employeeService);
		employeeDto = new EmployeeDto(1L, "Jimmy", "Patel", "ji@ji.com", "IT", "ABC");
		departmentDto = new DepartmentDto(1L, "Information Technology","IT" , "IT");
		organizationDto = new OrganizationDto(1L, "ABC Org.", "ABC ORG.", "ABC", LocalDateTime.now());
		apiResponseDto = new ApiResponseDto(employeeDto, departmentDto, organizationDto);
		
	}

	@Test
	void testSaveEmployee() {
		doReturn(employeeDto).when(employeeService).saveEmployee(any());
		EmployeeDto result = employeeController.saveEmployee(employeeDto).getBody();
		assertEquals(employeeDto.getEmail(), result.getEmail());
	}

	@Test
	void testGetEmployeeById() {
		doReturn(apiResponseDto).when(employeeService).getEmployeeById(any());
		ApiResponseDto result  = employeeController.getEmployeeById(1L).getBody();
		assertEquals(apiResponseDto.getDepartment(), result.getDepartment());
	}

	@Test
	void testGetAllEmployees() {
		doReturn(new PageImpl<>(List.of(apiResponseDto))).when(employeeService).getAllEmployeesByPagingAndSorting(any(), any(), any(), any());
		Page<ApiResponseDto> result = employeeController.getAllEmployees(0, 3, "firstName", "asc").getBody();
		assertEquals(1, result.getSize());
	}

	@Test
	void testDeleteEmployee() {
		
		ResponseEntity<String> result = employeeController.deleteEmployee(1L);
		verify(employeeService).deleteEmployee(any());
		assertEquals("Employee Deleted Successfully", result.getBody());
		
	}

	@Test
	void testUpdateEmployee() {
		doReturn(employeeDto).when(employeeService).updateEmployee(any());
		EmployeeDto result = employeeController.updateEmployee(1L, employeeDto).getBody();
		assertEquals(employeeDto.getDepartmentCode(), result.getDepartmentCode());
	}

}
