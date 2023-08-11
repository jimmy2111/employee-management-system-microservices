package com.eic.springbootmicroservices.departmentservice.service;

import java.util.List;

import com.eic.springbootmicroservices.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
	DepartmentDto saveDepartment(DepartmentDto departmentDto);
	
	DepartmentDto getDepartmentByCode(String departmentCode);
	
	List<DepartmentDto> getAllDepartments();
	
	DepartmentDto updateDepartment(String departmentCode, DepartmentDto departmentDto);
	
	void deleteDepartment(String departmentCode);

}
