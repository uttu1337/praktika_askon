package com.example.praktika_askon.service;

import com.example.praktika_askon.dto.organization.OrganizationCreateDto;
import com.example.praktika_askon.dto.organization.OrganizationResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizationService {
    List<OrganizationResponseDto> getAllOrganizations();
    Optional<OrganizationResponseDto> getOrganizationById(UUID id);
    OrganizationResponseDto createOrganization(OrganizationCreateDto dto);
    Optional<OrganizationResponseDto> updateOrganization(UUID id, OrganizationCreateDto dto);
    void deleteOrganization(UUID id);
}