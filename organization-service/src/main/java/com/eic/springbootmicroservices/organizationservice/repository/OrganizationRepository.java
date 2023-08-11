package com.eic.springbootmicroservices.organizationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eic.springbootmicroservices.organizationservice.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	Optional<Organization> findByOrganizationCode(String organizationCode);
}
