package com.eic.springbootmicroservices.employeeservice.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.eic.springbootmicroservices.employeeservice.dto.ApiResponseDto;
import com.eic.springbootmicroservices.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	ApiResponseDto getEmployeeById(Long employeeId);
	
	List<ApiResponseDto> getallEmployees();
	
	Page<ApiResponseDto> getAllEmployeesByPagingAndSorting(Integer pageNo, Integer pageSize,String sortField, String sortOrder);
	
	void deleteEmployee(Long employeeId);
	
	EmployeeDto updateEmployee(EmployeeDto employeeDto);
}
