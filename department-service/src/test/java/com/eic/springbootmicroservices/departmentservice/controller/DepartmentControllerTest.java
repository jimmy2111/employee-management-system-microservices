package com.eic.springbootmicroservices.departmentservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eic.springbootmicroservices.departmentservice.dto.DepartmentDto;
import com.eic.springbootmicroservices.departmentservice.service.DepartmentService;

class DepartmentControllerTest {

	@InjectMocks
	private DepartmentController departmentController;
	
	@Mock
	private DepartmentService departmentService;
	
	private DepartmentDto departmentDto;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		departmentController = new DepartmentController(departmentService);
		departmentDto =new DepartmentDto(1L, "IT", "Information Technology", "IT001");
	}
	
	@Test
	void testSaveDepartment() {
		doReturn(departmentDto).when(departmentService).saveDepartment(any(DepartmentDto.class));
		
		DepartmentDto result = departmentController.saveDepartment(departmentDto).getBody();
		
		assertEquals(departmentDto.getDepartmentCode(), result.getDepartmentCode());
		
	}

	@Test
	void testGetDepartment() {
		doReturn(departmentDto).when(departmentService).getDepartmentByCode(any());
		
		DepartmentDto result = departmentController.getDepartment("IT001").getBody();
		assertEquals(departmentDto.getDepartmentCode(), result.getDepartmentCode());
	}


}
