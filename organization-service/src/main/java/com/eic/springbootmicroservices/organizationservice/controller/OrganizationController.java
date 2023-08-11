package com.eic.springbootmicroservices.organizationservice.controller;

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

import com.eic.springbootmicroservices.organizationservice.dto.OrganizationDto;
import com.eic.springbootmicroservices.organizationservice.service.OrganizationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/organizations")
@AllArgsConstructor
public class OrganizationController {

	private OrganizationService organizationService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
		OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
		return new ResponseEntity<OrganizationDto>(savedOrganization, HttpStatus.CREATED);
	}
	
	@GetMapping("{code}")
	public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String organizationCode){
		OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
		return new ResponseEntity<OrganizationDto>(organizationDto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<OrganizationDto>> getAllOrganizations(){
		List<OrganizationDto> organizations = organizationService.getAllOrganizations();
		return ResponseEntity.ok(organizations);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{organizationCode}")
	public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable String organizationCode,@RequestBody OrganizationDto organizationDto){
		OrganizationDto updatedOrganizationDto = organizationService.updateOrganization(organizationCode, organizationDto);
		return new ResponseEntity<OrganizationDto>(updatedOrganizationDto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{organizationCode}")
	public ResponseEntity<String> deleteOrganization(@PathVariable String organizationCode){
		organizationService.deleteOrganization(organizationCode);
		return new ResponseEntity<String>("Organization Deleted Successfully", HttpStatus.OK);
	}
}
