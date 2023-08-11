package com.eic.springbootmicroservices.organizationservice.service;

import java.util.List;

import com.eic.springbootmicroservices.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
	
	OrganizationDto saveOrganization(OrganizationDto organizationDto);
	
	OrganizationDto getOrganizationByCode(String organizationCode);
	
	List<OrganizationDto> getAllOrganizations();
	
	OrganizationDto updateOrganization(String organizationCode, OrganizationDto organizationDto);
	
	void deleteOrganization(String organizationCode);

}
