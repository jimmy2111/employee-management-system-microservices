package com.eic.springbootmicroservices.departmentservice.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.eic.springbootmicroservices.departmentservice.dto.DepartmentDto;
import com.eic.springbootmicroservices.departmentservice.entity.Department;
import com.eic.springbootmicroservices.departmentservice.exception.DepartmentAlreadyExistsException;
import com.eic.springbootmicroservices.departmentservice.exception.ResourceNotFoundException;
import com.eic.springbootmicroservices.departmentservice.repository.DepartmentRepository;
import com.eic.springbootmicroservices.departmentservice.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode());
		if (optionalDepartment.isPresent()) {
			throw new DepartmentAlreadyExistsException("Department Already Exists with Department Code: "+departmentDto.getDepartmentCode());
		}
		Department department = modelMapper.map(departmentDto, Department.class);
		Department savedDepartment = departmentRepository.save(department);
		DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);
		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {
		Department department = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(
				()->new ResourceNotFoundException("Department","Code", departmentCode)
				);
		DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
		return departmentDto;
	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<Department> departmentList = departmentRepository.findAll();
		List<DepartmentDto> departments = departmentList.stream()
				.map(department -> modelMapper.map(department, DepartmentDto.class))
				.collect(Collectors.toList());
		return departments;
	}

	@Override
	public DepartmentDto updateDepartment(String departmentCode, DepartmentDto departmentDto) {
		Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(departmentCode);
		if (!optionalDepartment.isPresent()) {
			throw new ResourceNotFoundException("Department", "Department Code", departmentCode);
			
		}
		Department department = optionalDepartment.get();
		department.setDepartmentName(departmentDto.getDepartmentName());
		department.setDepartmentDescription(departmentDto.getDepartmentDescription());
		department.setDepartmentCode(departmentDto.getDepartmentCode());
		Department updatedDepartment = departmentRepository.save(department);
		DepartmentDto updatedDepartmentDto = modelMapper.map(updatedDepartment, DepartmentDto.class);
		return updatedDepartmentDto;
	}

	@Override
	public void deleteDepartment(String departmentCode) {
		Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(departmentCode);
		if (!optionalDepartment.isPresent()) {
			throw new ResourceNotFoundException("Department", "Department Code", departmentCode);
			
		}
		departmentRepository.deleteById(optionalDepartment.get().getId());
	}

}
